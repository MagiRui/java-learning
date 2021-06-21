package com.magirui.threadlocal;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class ThreadLocalExample {

  public static void main(String[] args) {

    final ThreadLocal threadLocal1 = new ThreadLocal();
    Thread thread1 = new Thread(() -> {

      threadLocal1.set(1);
      try{

        Thread.sleep(1000);
      }catch (Exception ex) {

        ex.printStackTrace();
      }

      System.out.println(threadLocal1.get());
      threadLocal1.remove();

    });

    Thread thread2 = new Thread(()-> {

      threadLocal1.set(2);
      threadLocal1.remove();
    });

    thread1.start();
    thread2.start();
  }
}
