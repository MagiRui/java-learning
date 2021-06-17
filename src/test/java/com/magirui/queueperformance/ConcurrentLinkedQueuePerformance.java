package com.magirui.queueperformance;

        import org.junit.Test;

        import java.util.concurrent.*;

/**
 * 〈BlockingQueue 〉
 *
 * @author magirui
 * @create 2018-06-22 上午9:47
 */
public class ConcurrentLinkedQueuePerformance {

    private ConcurrentLinkedQueue<String> concurrentLinkedQueue = new ConcurrentLinkedQueue<String>() ;
    private final int MAX_THREAD = 3;


    public long testPut() throws Exception{


        final CountDownLatch mainCountDownLatch = new CountDownLatch(MAX_THREAD);
        long start = System.currentTimeMillis();
        for(int i = 0; i < MAX_THREAD; i++){

            final String iStr = String.valueOf(i);
            new Thread("ConcurrentLinkedQueuePerformance_Thread" + i){

                public void run(){
                    try{

                        concurrentLinkedQueue.add(iStr);
                        mainCountDownLatch.countDown();
                    }catch (Exception ex){

                        ex.printStackTrace();
                    }

                }
            }.start();


        }


        mainCountDownLatch.await();
        long end = System.currentTimeMillis();
        return (end - start);
    }

    public static void main(String[] args)throws Exception{

        ConcurrentLinkedQueuePerformance concurrentLinkedQueuePerformance = new ConcurrentLinkedQueuePerformance();

        for(int j = 0; j < 3; j++){
            long sum = 0;
            for(int i = 0; i< 10; i++){

                sum += concurrentLinkedQueuePerformance.testPut();
            }

            System.out.println(sum);
        }
    }
}
