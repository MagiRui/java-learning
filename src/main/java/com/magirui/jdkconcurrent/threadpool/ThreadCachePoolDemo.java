package com.magirui.jdkconcurrent.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * 〈线程Cache〉
 *
 * @author magirui
 * @create 2018-06-22 下午10:20
 */
public class ThreadCachePoolDemo {

    public static void main(String[] args){

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Executors Runnable running");
            }
        });
        ExecutorService executorService1 = Executors.newFixedThreadPool(3);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
    }
}
