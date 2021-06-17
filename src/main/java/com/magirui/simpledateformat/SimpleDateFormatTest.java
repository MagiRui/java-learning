package com.magirui.simpledateformat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈SimpleDateFormate测试类〉
 *
 * @author magirui
 * @create 2018-07-04 下午4:15
 */
public class SimpleDateFormatTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class ParseDate implements Runnable{

        int i = 0;

        public ParseDate(int i ){

            this.i = i;
        }

        public void run(){

            try{

                Date tf = sdf.parse("2015-03-29 19:29:" + i%60);
            }catch (ParseException e){

                e.printStackTrace();
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
