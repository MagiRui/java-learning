package com.magirui.niocalculate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.*;

/**
 * 〈NIO Server〉
 *
 * @author magirui
 * @create 2018-06-26 下午2:25
 */
public class NIOServer {

    private int port = 9091;

    private CharsetDecoder decode = Charset.forName("UTF-8").newDecoder();

    private ByteBuffer sBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer rBuffer = ByteBuffer.allocate(1024);

    private Map<String, SocketChannel> clientsMap = new HashMap<String, SocketChannel>();

    private Selector selector;

    public NIOServer(){

        try{

            init();
            listen();
        }catch(Exception e){

            e.printStackTrace();
        }

    }

    private void init() throws IOException{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server start on port: " + port);
    }

    private void listen(){

        while(true){

            try{

                selector.select();
                Set<SelectionKey>  selectionKeys = selector.selectedKeys();
                for(SelectionKey key: selectionKeys){

                    handle(key);
                }

                selectionKeys.clear();
            }catch (Exception e){

                e.printStackTrace();
                break;
            }
        }
    }

    private void handle(SelectionKey selectionKey) throws  IOException{

        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveTest = null;
        int count = 0;
        if (selectionKey.isAcceptable()){

            server = (ServerSocketChannel) selectionKey.channel();
            client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        }else if(selectionKey.isReadable()){

            client = (SocketChannel)selectionKey.channel();
            rBuffer.clear();
            count = client.read(rBuffer);
            if(count > 0){

                rBuffer.flip();
                receiveTest = decode.decode(rBuffer.asReadOnlyBuffer()).toString();

                System.out.println(" ..receiveTest.." + receiveTest);
                if(receiveTest.contains("+") || receiveTest.contains("-") ||
                    receiveTest.contains("*") || receiveTest.contains("/")){

                    BigDecimal data = Calculator.exec(receiveTest);
                    receiveTest += " = " + data.toPlainString();
                }


                sBuffer.clear();
                sBuffer.flip();

                client.write(sBuffer);

                response(client,receiveTest);
            }
        }
    }


    private void response(SocketChannel client, String info) throws IOException{

         sBuffer.clear();
         sBuffer.put((info).getBytes());
         sBuffer.flip();
         client.write(sBuffer);
    }

    public static void main(String[] args){

        new NIOServer();
    }
}
