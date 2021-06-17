package com.magirui.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈ThreadLocalGC〉
 *
 * @author magirui
 * @create 2018-07-04 下午4:41
 */
public class ThreadLocalDemoGc {

    static volatile ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>(){

        protected void finalize() throws Throwable {

            System.out.println(this.toString() + "is gc");

        }
    };

    static volatile CountDownLatch cd = new CountDownLatch(10000);

    public static class ParseDate implements Runnable{

        int i  = 0;
        public ParseDate(int i){

            this.i = i;
        }

       public void run(){

           try{

               if(tl.get() == null){

                   tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"){

                       protected void finalize() throws Throwable{

                           System.out.println(this.toString() + " is gc ");
                       }
                   });
               }

               Date tf = tl.get().parse("2015-03-29 19:29:" + i%60);
               System.out.println( i + ":" + tf);
           }catch (Exception ex){

               ex.printStackTrace();
           }finally {

               cd.countDown();
           }
       }

    }

    public static void main(String[] args)throws Exception{

        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i= 0; i< 10000; i++){

            es.execute(new ParseDate(i));
        }

        cd.await();
        System.out.println("mission complete!");

        tl = null;

        System.gc();

        System.out.println("first GC complete!!");
        tl = new ThreadLocal<SimpleDateFormat>();

        cd = new CountDownLatch(10000);
        for(int i= 0; i < 10000; i++){

            es.execute(new ParseDate(i));
        }

        cd.await();
        Thread.sleep(1000);
        System.gc();
        System.out.println("Second GC complete!");
    }
}
