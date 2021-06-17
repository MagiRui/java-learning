package com.magirui.aio;

import java.io.IOException;
import java.io.ObjectInput;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 〈AIO Server〉
 *
 * @author magirui
 * @create 2018-06-26 下午5:48
 */
public class AIOServer {

    public final static int port = 9091;

    public final static String ip = "127.0.0.1";

    private CharsetDecoder decode = Charset.forName("UTF-8").newDecoder();

    private AsynchronousServerSocketChannel serverSocketChannel = null;

    public AIOServer(){

        try{

            serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(ip, port));
        }catch (IOException e){

            e.printStackTrace();
        }
    }

    public void start(){

       ByteBuffer buff = ByteBuffer.allocate(1024);
       while(true){

          try{

              buff.clear();

              Future<AsynchronousSocketChannel> future = serverSocketChannel.accept();
              AsynchronousSocketChannel socketChannel = future.get();

              Future<Integer> count = socketChannel.read(buff);
              System.out.println(count.get());
              String test = new String( buff.array(),0,count.get());
              System.out.println("server limit:" + buff.limit());
              System.out.println("server position:" + buff.position());
              System.out.println("server capacity:" + buff.capacity());



              System.out.println(test);
              socketChannel.write(ByteBuffer.wrap("111".getBytes())).get();
          }catch (Exception ex){


          }

       }
    }

    public static void main(String[] args){

        AIOServer aioServer = new AIOServer();
        aioServer.start();
    }
}
