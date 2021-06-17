package com.magirui.deviationlock;

import java.util.List;
import java.util.Vector;

/**
 * 〈偏向锁〉
 *
 * @author magirui
 * @create 2018-06-27 上午10:10
 */
public class DeviationLock {

    public static List<Integer> numberList = new Vector<Integer>();

    public static void main(String[] args)throws InterruptedException{

        long begin = System.currentTimeMillis();
        int count = 0 ;
        int startnum = 0;

        while(count < 10000000){

            numberList.add(startnum);
            startnum += 2;
            count++;

        }

        long end = System.currentTimeMillis();
        System.out.println(end - begin);


    }
}
