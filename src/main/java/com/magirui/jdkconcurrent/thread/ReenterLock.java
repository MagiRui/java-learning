package com.magirui.jdkconcurrent.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈Java重入锁〉
 *
 * @author magirui
 * @create 2018-06-14 下午2:27
 */
public class ReenterLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0 ;

    public void run(){

        for(int j = 0; j < 100000000; j++){

            lock.lock();
            try{
                i++;
            }catch (Exception ex){

                ex.printStackTrace();
            }finally {

                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{

        ReenterLock tl = new ReenterLock();
        Thread t1 = new Thread(tl);
        Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);

    }
}
