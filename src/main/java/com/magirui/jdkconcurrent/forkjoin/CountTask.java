package com.magirui.jdkconcurrent.forkjoin;

import akka.dispatch.forkjoin.ForkJoinPool;
import akka.dispatch.forkjoin.ForkJoinTask;
import akka.dispatch.forkjoin.RecursiveTask;
import scala.collection.parallel.ParIterableLike;

import java.util.ArrayList;

/**
 * 〈fork join线程〉
 *
 * @author magirui
 * @create 2018-06-19 下午6:46
 */
public class CountTask extends RecursiveTask<Long>{

    private static final int THRESHOLD = 10000;

    private long start;

    private long end;

    public CountTask(long start, long end){

        this.start = start;
        this.end = end;
    }

    public Long compute(){

        long sum = 0;
        boolean canComplete = (end - start) < THRESHOLD;
        if(canComplete){

            for(long i = start; i <= end; i++){

                sum += i;
            }
        }else {

            long step = (start + end) / 100;
            ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
            long pos = start;
            for (int i = 0; i < 100; i++){

                long lastOne = pos + step;
                if(lastOne > end ){

                    lastOne = end;
                }

                CountTask subTask = new CountTask(pos, lastOne);
                pos += step + 1;
                subTasks.add(subTask);
                subTask.fork();
            }

            for(CountTask t:subTasks){

                sum += t.join();
            }
        }

        return sum;
    }

    public static void main(String[] args){

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, 200000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try{

            long res = result.get();
            System.out.println("sum = " + res);
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
