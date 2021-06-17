package com.magirui.stampedlockdemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.StampedLock;

/**
 * 〈〉
 *
 * @author magirui
 * @create 2018-06-28 上午11:42
 */

class Point{

    private double x, y;
    private CountDownLatch innerCountDownLatch;
    private CountDownLatch mainCountDownLatch;
    private final StampedLock sl = new StampedLock();

    public Point(double x, double y, CountDownLatch innerCountDownLatch, CountDownLatch mainCountDownLatch){

        this.x = x;
        this.y = y;
        this.innerCountDownLatch = innerCountDownLatch;
        this.mainCountDownLatch = mainCountDownLatch;
    }


    void move(double deltaX, double deltaY){


        try {
            innerCountDownLatch.await();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        long stamp = sl.writeLock();
        try{

            x += deltaX;
            y += deltaY;
        }finally {

            sl.unlock(stamp);
        }

    }

    double distanceFromOrigin(){

        try {
            innerCountDownLatch.await();

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        long stamp = sl.tryOptimisticRead();
        double currentX = x;
        double currentY = y;

        if(!sl.validate(stamp)){

            stamp = sl.readLock();
            try{

                currentX = x;
                currentY = y;
            }finally {

                sl.unlock(stamp);
            }
        }
        mainCountDownLatch.countDown();
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}

public class StampedLockDemo {

    public static void main(String[] args){

        final CountDownLatch innerCountDownLatch = new CountDownLatch(2);
        final CountDownLatch mainCountDownLatch = new CountDownLatch(20);
        final Point point = new Point(0, 0, innerCountDownLatch, mainCountDownLatch);
        new Thread(){

            public void run(){

                point.move(10, 2);
            }
        }.start();

        innerCountDownLatch.countDown();
        for(int i = 0 ; i < 20; i++){

            new Thread(){

                public void run(){

                    point.distanceFromOrigin();
                }
            }.start();
        }
        innerCountDownLatch.countDown();
        try {

            System.out.println(">>>>>>>>>>>>");
            long start = System.currentTimeMillis();
            mainCountDownLatch.await();
            long end = System.currentTimeMillis();

            System.out.println(end - start);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


