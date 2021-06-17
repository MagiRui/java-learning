package com.magirui.zy.zy02;

/**
 * 〈线程顺序执行〉
 *
 * @author magirui
 * @create 2018-06-20 上午10:34
 */
public class ThreadSequence {

    static class MyThread extends Thread{

        public MyThread(String threadName){

            super(threadName);
        }

        public void run(){

            System.out.println( this.getName() + "runing");
        }
    }

    public static void main(String[] args)throws Exception{

        MyThread myThread1 = new MyThread("myThread1");

        MyThread myThread2 = new MyThread("myThread2");

        MyThread myThread3 = new MyThread("myThread3");

        myThread1.start();
        myThread2.start();
        myThread3.start();

        myThread1.join();
        myThread2.join();
        myThread3.join();

    }
}
