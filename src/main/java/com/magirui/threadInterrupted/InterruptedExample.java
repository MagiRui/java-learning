package com.magirui.threadInterrupted;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class InterruptedExample {

  private static class MyThread2 extends Thread {

    @Override
    public void run() {

      while(!interrupted()) {

      }

      System.out.println("Thread end");
    }
  }

  public static void main(String[]  args){

    Thread thread2 = new MyThread2();
    thread2.start();
    thread2.interrupt();
  }
}
