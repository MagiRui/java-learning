package com.magirui.jdkconcurrent.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈线程池模型〉
 *
 * @author magirui
 * @create 2018-06-14 下午5:52
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable{

        public void run(){

            System.out.println(System.currentTimeMillis() + ": Thread ID:" + Thread.currentThread().getId());

            try{

                Thread.sleep(1000);
            }catch (InterruptedException e){

                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args){

        MyTask myTask = new MyTask();
        ExecutorService es = Executors.newFixedThreadPool(5);
        ExecutorService ca = Executors.newCachedThreadPool();
        ca.submit(new Runnable() {
            public void run() {

            }
        });
        for(int i = 0; i < 10; i++){

            es.submit(myTask);
        }
    }
}
