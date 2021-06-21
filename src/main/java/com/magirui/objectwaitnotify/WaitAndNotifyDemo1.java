package com.magirui.objectwaitnotify;

/**
 * @author MagiRui
 * @description
 * @date 6/18/21
 */
public class WaitAndNotifyDemo1 {

  public static void main(String[] args) throws InterruptedException {

    MyThread1 myThread1 = new MyThread1();
    synchronized (myThread1) {

      try{

        myThread1.start();
        //主线程睡3s
        Thread.sleep(3000);
        System.out.println("Before wait");
        //阻塞主线程
        myThread1.wait();
        System.out.println("After wait");

      }catch (InterruptedException ex) {

        ex.printStackTrace();
      }
    }

  }
}

class MyThread1 extends Thread{

  public void run() {

    synchronized(this) {

      System.out.println("before notify");
      notify();
      System.out.println("after notify");

    }
  }
}
