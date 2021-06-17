package com.magirui.niotest;

import java.nio.ByteBuffer;
import java.util.LinkedList;

/**
 * 〈〉
 *
 * @author magirui
 * @create 2018-07-11 下午4:34
 */
public class EchoClient {

    private LinkedList<ByteBuffer> outq;

    EchoClient(){

        outq = new LinkedList<ByteBuffer>();
    }

    public LinkedList<ByteBuffer> getOutputQueue(){

        return outq;
    }

    public void enqueue(ByteBuffer byteBuffer){

        outq.addFirst(byteBuffer);
    }
}
