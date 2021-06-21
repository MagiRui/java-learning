package com.magirui.threadInterrupted;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class InterruptExample {

  public static class MyThread1 extends Thread{

    public void run() {

      try{

        Thread.sleep(2000);
        System.out.println("Thread run");
      }catch (InterruptedException e) {

        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {

    Thread thread1 = new MyThread1();
    thread1.start();
    thread1.interrupt();
    System.out.println("Main Run");

  }
}
