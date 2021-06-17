package com.magirui.jdkconcurrent.threadpool;

import java.util.concurrent.*;

/**
 * 〈线程工厂demo〉
 *
 * @author magirui
 * @create 2018-06-19 下午2:48
 */
public class ThreadFactoryDemo {

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

    public static void main(String[] args) throws InterruptedException{

       MyTask myTask = new MyTask();
       ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {

                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        System.out.println("Create " + t);
                        return t;
                    }
                });

       for (int i = 0; i< 5; i++ ){

           es.submit(myTask);
       }

        Thread.sleep(2000);

    }
}
