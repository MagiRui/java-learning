package com.magirui.jdkconcurrent.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈倒计时器〉
 *
 * @author magirui
 * @create 2018-06-14 下午4:39
 */
public class CountDownLatchDemo implements Runnable{

    static final CountDownLatch end = new CountDownLatch(10);

    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    public void run(){

        try{

            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("Check complete");
            end.countDown();
        }catch (InterruptedException ex){

            ex.printStackTrace();
        }
    }

    public static void main(String[] args)throws InterruptedException{

        ExecutorService exec = Executors.newFixedThreadPool(10);
        for(int i = 0; i<10; i++){

            exec.submit(demo);
        }

        end.await();
        System.out.println("Fire!");
        exec.shutdown();
    }



}
