package com.magirui.jetty;

import org.eclipse.jetty.io.ArrayByteBufferPool;
import org.eclipse.jetty.util.BlockingArrayQueue;
import org.eclipse.jetty.util.MemoryUtils;

/**
 * 〈jetty blocking queue〉
 *
 * @author magirui
 * @create 2018-07-03 上午9:43
 */
public class JettyBlockingArrayQueue {

    public static void main(String[] args){

        BlockingArrayQueue blockingArrayQueue = new BlockingArrayQueue();

        final int HEAD_OFFSET = MemoryUtils.getIntegersPerCacheLine() - 1;

        System.out.println(">>>>>>>>>>>>" + HEAD_OFFSET);
        final int TAIL_OFFSET = HEAD_OFFSET + MemoryUtils.getIntegersPerCacheLine();

        System.out.println(">>>>>>>>>>>>" + TAIL_OFFSET);

        ArrayByteBufferPool arrayByteBufferPool = new ArrayByteBufferPool();
    }
}
