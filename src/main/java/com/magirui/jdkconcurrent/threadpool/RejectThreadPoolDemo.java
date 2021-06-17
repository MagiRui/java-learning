package com.magirui.jdkconcurrent.threadpool;

import java.util.concurrent.*;

/**
 * 〈自定义线程池和拒绝策略〉
 *
 * @author magirui
 * @create 2018-06-19 下午2:36
 */
public class RejectThreadPoolDemo {

    public static class MyTask implements Runnable{

        public void run(){

            System.out.println(System.currentTimeMillis() + ": Thread ID:" + Thread.currentThread().getId());

            try{

                Thread.sleep(1000);
            }catch(InterruptedException e){

                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException{

        MyTask task = new MyTask();

        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10),
                Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {

                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                        System.out.println(r.toString() + " is discard");
                    }
                });

        for (int i = 0; i< Integer.MAX_VALUE; i++) {

            es.submit(task);
            Thread.sleep(10);
        }
    }
}
