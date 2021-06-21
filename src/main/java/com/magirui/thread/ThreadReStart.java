package com.magirui.thread;

/**
 * @author MagiRui
 * @description
 * @date 6/21/21
 */
public class ThreadReStart {

  public static void main(String[] args) {

    Thread t1 = new Thread(()->{
      System.out.println(Thread.currentThread() + "ggg");
    });

    t1.start();
    t1.start();
  }
}
