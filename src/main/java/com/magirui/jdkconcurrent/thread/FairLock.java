package com.magirui.jdkconcurrent.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈公平锁〉
 *
 * @author magirui
 * @create 2018-06-14 下午3:27
 */
public class FairLock implements Runnable{

    public static ReentrantLock fairLock = new ReentrantLock(true);

    public void run(){

        while(true){

            try{

                fairLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {

                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{

        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1, "Thrad_t1");
        Thread t2 = new Thread(r1, "Thread_t2");

        t1.start();
        t2.start();
    }
}
