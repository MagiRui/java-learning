package com.magirui.parallelsort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈简单的奇偶排序〉
 *
 * @author magirui
 * @create 2018-07-05 下午9:27
 */
public class ParallelSortTest {

    static int exchFlag = 1;

    static synchronized void setExchFlag(int v){

        exchFlag = v;
    }

    static synchronized int getExchFlag(){

        return exchFlag;
    }

    static class OddEvenSortTask implements Runnable{

        int i ;
        int array[];
        CountDownLatch countDownLatch;

        public OddEvenSortTask(int i, CountDownLatch latch){

            this.i = i;
            this.countDownLatch = countDownLatch;
        }

        public void run(){

            if(array[i] > array[i+1]){

                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;

                setExchFlag(1);
            }

            countDownLatch.countDown();
        }

    }

    public static void pOddEventSort(int[] arr)throws InterruptedException{

        int start = 0;
        ExecutorService pool = Executors.newCachedThreadPool();
        while(getExchFlag() == 1 || start == 1){

            setExchFlag(0);
            CountDownLatch countDownLatch = new CountDownLatch(arr.length / 2 - (arr.length % 2 == 0 ? start : 0));
            for(int i = start; i < arr.length-1; i+=2){

                pool.submit(new OddEvenSortTask(i, countDownLatch));
            }

            countDownLatch.await();
            if(start == 0){

                start = 1;
            }else{

                start = 0;
            }

        }
    }
}
