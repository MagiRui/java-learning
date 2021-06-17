package com.magirui.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * 〈〉
 *
 * @author magirui
 * @create 2018-06-26 下午7:24
 */
public class AIOClient {

    public static void main(String[] args)
            throws Exception
    {

        ByteBuffer buff = ByteBuffer.allocate(1024);
        Charset utf = Charset.forName("utf-8");

        AsynchronousSocketChannel clientChannel
                = AsynchronousSocketChannel.open();
        try {

            clientChannel.connect(new InetSocketAddress("127.0.0.1"
                    , 9091)).get();
            buff.clear();
            System.out.println("client limit:" + buff.limit());
            System.out.println("client position:" + buff.position());
            System.out.println("client capacity:" + buff.capacity());

            buff.put("3".getBytes());
            System.out.println("3".getBytes().length);
            clientChannel.write(buff);
            buff.flip();

            System.out.println("client limit:" + buff.limit());
            System.out.println("client position:" + buff.position());
            System.out.println("client capacity:" + buff.capacity());

            clientChannel.read(buff).get();

            // 将buff中内容转换为字符串
            String content = utf.decode(buff).toString();
            System.out.println("服务器信息：" + content);
        }catch (Exception p) {


        }
    }

}
