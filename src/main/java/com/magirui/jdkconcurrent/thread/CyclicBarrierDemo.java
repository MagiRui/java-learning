package com.magirui.jdkconcurrent.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 〈循环栅栏〉
 *
 * @author magirui
 * @create 2018-06-14 下午4:51
 */
public class CyclicBarrierDemo {

    public static class Soldier implements Runnable{

        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        Soldier(CyclicBarrier cyclic, String soldierName){

            this.cyclicBarrier = cyclic;
            this.soldier = soldierName;
        }

        public void run(){

            try{

                //等待所有士兵到齐
                cyclicBarrier.await();
                doWork();
                //等待所有士兵完成工作
                cyclicBarrier.await();

            }catch(InterruptedException e){

                e.printStackTrace();
            }catch (BrokenBarrierException e){

                e.printStackTrace();
            }
        }

        void doWork(){

            try{

                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            }catch(InterruptedException e){

                e.printStackTrace();
            }

            System.out.println(soldier  + ":任务完成");
        }

    }

    public static class BarrierRun implements Runnable{

        boolean flag;
        int N;

        public BarrierRun(boolean flag, int N){

            this.flag = flag;
            this.N = N;
        }

        public void run(){

            if(flag){

                System.out.println("司令:[士兵" + N + "个,任务完成!]");
            }else{

                System.out.println("司令:[士兵" + N + "个,集合完毕!]");
                flag = true;
            }
        }
    }

    public static void main(String args[]) throws InterruptedException{

        final int N = 10;

        Thread[] allSolider = new Thread[N];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N, new BarrierRun(flag, N));

        System.out.println("集合队伍!");
        for(int i= 0; i < N; i++){

            System.out.println("士兵" + i+ "报道!");
            allSolider[i] = new Thread(new Soldier(cyclicBarrier, "士兵" + i));
            allSolider[i].start();
        }
    }
}
