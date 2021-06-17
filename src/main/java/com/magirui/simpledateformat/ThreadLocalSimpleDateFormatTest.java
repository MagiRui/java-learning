package com.magirui.simpledateformat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈线程局部变量〉
 *
 * @author magirui
 * @create 2018-07-04 下午4:23
 */
public class ThreadLocalSimpleDateFormatTest {

    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable{

        int i = 0;
        public ParseDate(int i){

            this.i = i;
        }

        public void run(){

            try{

                if(tl.get() == null){

                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }

                Date tf = tl.get().parse("2015-03-29 19:29:" + i%60);
                System.out.println( i + ":" + tf);
            }catch (Exception ex){

                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args){

        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i = 0; i<1000; i++){

            es.execute(new ParseDate(i));
        }
    }
}
