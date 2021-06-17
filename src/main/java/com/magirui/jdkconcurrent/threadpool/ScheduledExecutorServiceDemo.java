package com.magirui.jdkconcurrent.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 〈定时任务调度〉
 *
 * @author magirui
 * @create 2018-06-19 下午2:27
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args){

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(new Runnable() {
            public void run() {

                try{

                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis()/1000);
                }catch (InterruptedException e){

                    e.printStackTrace();
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
