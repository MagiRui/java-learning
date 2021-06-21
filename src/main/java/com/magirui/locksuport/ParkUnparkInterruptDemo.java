package com.magirui.locksuport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author MagiRui
 * @description
 * @date 6/18/21
 */
class MyThread3 extends Thread{

  private Object object;

  public MyThread3(Object object){

    this.object = object;
  }

  public void run() {

    System.out.println("before interrupted");
    try{

      Thread.sleep(3000);
    }catch (InterruptedException e) {

      e.printStackTrace();
    }

    Thread thread = (Thread) object;
    //中断线程
    thread.interrupt();

    System.out.println("after interruptted");
  }
}


/**
 * @Author MagiRui
 * @Description
 *
 * 说明: 可以看到，在主线程调用park阻塞后，在myThread线程中发出了中断信号，此时主线程会继续运行，也就是说明此时interrupt起到的作用与unpark一样。
 *
 * @Date 10:43 AM 6/18/21
 * @Params
 * @return
 **/
public class ParkUnparkInterruptDemo {

  public static void main(String[] args) {

    MyThread3 myThread3 = new MyThread3(Thread.currentThread());

    myThread3.start();
    System.out.println("before park");

    //获取许可
    LockSupport.park("ParkUnparkInterruptDemo");

    System.out.println("after park");

  }
}
