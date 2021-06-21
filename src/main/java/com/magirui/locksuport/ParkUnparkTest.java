package com.magirui.locksuport;

import java.util.concurrent.locks.LockSupport;

/**
 * @author MagiRui
 * @description
 * @date 6/18/21
 */
class MyThread extends Thread{

  private Object object;

  public MyThread(Object object) {

    this.object = object;
  }

  public void run() {

    System.out.println("before unpark");

    try{

      Thread.sleep(1000);
    }catch (InterruptedException ex){

      ex.printStackTrace();
    }

    //获取blocker
    System.out.println("Blocker info " + LockSupport.getBlocker((Thread) object));

    //释放许可
    LockSupport.unpark((Thread)object);

    //休眠500ms,保证先执行park中的setBlocker(t, null);
    try {

      Thread.sleep(500);
    } catch (InterruptedException e) {

      e.printStackTrace();
    }

    //再次获取blocker
    System.out.println("Blocker info " + LockSupport.getBlocker((Thread)object));

    System.out.println("after unpark");
  }
}

public class ParkUnparkTest {


  /**
   * @Author MagiRui
   * @Description
   * @Date 10:03 AM 6/18/21
   * @Params
   * @return
   *
   * 说明: 本程序先执行park，然后在执行unpark，进行同步，并且在unpark的前后都调用了getBlocker，可以看到两次的结果不一样，
   * 并且第二次调用的结果为null，这是因为在调用unpark之后，
   * 执行了Lock.park(Object blocker)
   * 函数中的setBlocker(t, null)函数，
   * 所以第二次调用getBlocker时为null。
   *
   **/

  public static void main(String[] args) {

    MyThread myThread = new MyThread(Thread.currentThread());
    myThread.start();
    System.out.println("before park");
    //获取许可
    LockSupport.park("ParkUnparkTest");
    System.out.println("after park");
  }
}



