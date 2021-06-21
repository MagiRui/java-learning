package com.magirui.threadlocal;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class ThreadLocalExample1 {


  public static void main(String[] args) {

    ThreadLocal threadLocal1 = new ThreadLocal();
    ThreadLocal threadLocal2 = new ThreadLocal();

    Thread thread1 = new Thread(()->{

      threadLocal1.set(1);
      threadLocal2.set(1);

      try{

        Thread.sleep(1000);
      }catch (Exception ex) {

        ex.printStackTrace();
      }

      System.out.println("thread1 threadLocal1.get()" + threadLocal1.get());
      System.out.println("thread1 threadLocal2.get()" + threadLocal2.get());

    });

    Thread thread2 = new Thread(()->{

      threadLocal1.set(2);
      threadLocal2.set(2);

      try{

        Thread.sleep(3000);
      }catch (Exception ex) {

        ex.printStackTrace();
      }

      System.out.println("thread2 threadLocal1.get()" + threadLocal1.get());
      System.out.println("thread2 threadLocal2.get()" + threadLocal2.get());

    });

    thread1.start();
    thread2.start();
  }
}
