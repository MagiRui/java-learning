package com.magirui.jdkconcurrent.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈限时等地锁〉
 *
 * @author magirui
 * @create 2018-06-14 下午2:54
 */
public class TimeLock implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();


    public void run(){

        try{

            if(lock.tryLock(5, TimeUnit.SECONDS)){

                Thread.sleep(6000);
            }

        }catch (InterruptedException e){

            e.printStackTrace();

        }finally {

            if(lock.isHeldByCurrentThread()){

                lock.unlock();
            }
        }
    }

    public static void main(String[] args){

        TimeLock tl = new TimeLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);

        t1.start();
        t2.start();
    }

}
