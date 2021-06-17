package com.magirui.atomiciarray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 〈整型数组原子操作〉
 *
 * @author magirui
 * @create 2018-07-04 下午8:33
 */
public class AtomicIntegerArrayDemo {

    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable{

        public void run(){

            for(int k = 0; k < 10000; k++){

                arr.getAndIncrement(k % arr.length());
            }
        }
    }

    public static void main(String[] args) throws Exception{

        Thread[] ts = new Thread[10];
        for(int k = 0; k < 10; k++){

            ts[k] = new Thread(new AddThread());
        }

        for(int k = 0; k < 10; k++){

            ts[k].start();
        }

        for(int k = 0; k < 10; k++){

            ts[k].join();
        }

        System.out.println(arr);

    }
}
