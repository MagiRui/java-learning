package com.magirui.jdkconcurrent.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 〈线程阻塞工具类〉
 *
 * @author magirui
 * @create 2018-06-14 下午5:27
 */
public class LockSupportDemo {

    public static Object u = new Object();

    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{

        public ChangeObjectThread(String name){

            super.setName(name);
        }

        public void run(){
            synchronized (u){

                System.out.println("In " + getName());
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args)throws InterruptedException{

        t1.start();
        Thread.sleep(1000);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);

        t1.join();
        t2.join();
    }
}
