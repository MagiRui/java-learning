package com.magirui.niotest;

import com.magirui.sockettest.HeavySocketClient;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈Nio实现server〉
 *
 * @author magirui
 * @create 2018-07-11 下午4:05
 */
public class NIOServer {

    private Selector selector;

    private ExecutorService tp = Executors.newCachedThreadPool();

    public static Map<Socket, Long> timeStat = new HashMap<Socket, Long>();

    private void startServer()throws Exception{

        selector = SelectorProvider.provider().openSelector();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        InetSocketAddress isa = new InetSocketAddress("localhost", 8000);
        ssc.socket().bind(isa);

        SelectionKey acceptKey = ssc.register(selector, SelectionKey.OP_ACCEPT);
        for(;;){

            selector.select();
            Set readyKeys = selector.selectedKeys();
            Iterator i = readyKeys.iterator();
            long e = 0;
            while(i.hasNext()){

                SelectionKey sk = (SelectionKey)i.next();
                i.remove();

                if(sk.isAcceptable()){

                    doAccept(sk);
                }else if(sk.isValid() && sk.isReadable()){

                    if(timeStat.containsKey(((SocketChannel)sk.channel()).socket())){

                        timeStat.put(((SocketChannel)sk.channel()).socket(), System.currentTimeMillis());

                        doRead(sk);
                    }
                }else if(sk.isValid() && sk.isWritable()){

                    doWrite(sk);
                    e = System.currentTimeMillis();
                    long b = timeStat.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend:" + (e-b)+"ms");
                }
            }
        }
    }

    private void doAccept(SelectionKey key){

        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel;
        try{

            clientChannel = server.accept();
            clientChannel.configureBlocking(false);

            SelectionKey clientKey = clientChannel.register(selector, SelectionKey.OP_READ);

            clientKey.attach(new EchoClient());
            InetAddress clientAddress = clientChannel.socket().getInetAddress();
            System.out.println("Accepted connection from " + clientAddress.getHostAddress() + ".");
        }catch (Exception e){

            System.out.println("Failed to accept new client");
            e.printStackTrace();
        }
    }

    private void doRead(SelectionKey key){

        SocketChannel channel = (SocketChannel)key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
        int len;
        try{

            len = channel.read(byteBuffer);
            if(len < 0){


                return ;
            }


        }catch(Exception e){

            System.out.println("Failed to read from client.");
            e.printStackTrace();

            return ;
        }

        byteBuffer.flip();
        tp.execute(new HandleMsg(key, byteBuffer));
    }

    private void doWrite(SelectionKey key){

        SocketChannel channel = (SocketChannel)key.channel();
        EchoClient echoClient = (EchoClient)key.attachment();
        LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();

        ByteBuffer bb = outq.getLast();
        try{

            int len = channel.write(bb);
            if(len == 0){

                return ;
            }

            if(bb.remaining() == 0){

                outq.removeLast();
            }
        }catch(Exception e){

            System.out.println("Failed to write to client.");
            e.printStackTrace();

        }

        if(outq.size() == 0 ){

            key.interestOps(SelectionKey.OP_READ);
        }
    }

    class HandleMsg implements Runnable {

        SelectionKey sk;
        ByteBuffer bb;

        public HandleMsg(SelectionKey sk, ByteBuffer bb){

            this.sk = sk;
            this.bb = bb;
        }

        public void run(){

            EchoClient echoClient = (EchoClient)sk.attachment();
            echoClient.enqueue(bb);
            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }


    public static void main(String args[])throws Exception{

        NIOServer nioServer = new NIOServer();
        nioServer.startServer();
    }
}
