package com.magirui.producerandconsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 〈〉
 *
 * @author magirui
 * @create 2018-07-04 下午9:21
 */
public class Consumer implements Runnable {

    private BlockingQueue<PCData> queue;

    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<PCData> queue){

        this.queue = queue;
    }

    public void run(){

        System.out.println("start Consuer id = " + Thread.currentThread().getId());

        Random r = new Random();

        try{

            while(true){

                PCData data = queue.take();
                if(null != data){

                    int re = data.getData() * data.getData();
                    System.out.println(MessageFormat.format("{0}*{1}={2}", data.getData(), data.getData(), re));
                    Thread.sleep(r.nextInt(SLEEPTIME));
                }
            }
        }catch (Exception e){

            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}
