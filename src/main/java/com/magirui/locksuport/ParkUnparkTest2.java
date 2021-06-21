package com.magirui.locksuport;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MagiRui
 * @description
 * @date 6/18/21
 */

class MyThread2 extends Thread{

  private Object object;

  public MyThread2(Object object) {

    this.object = object;
  }

  public void run() {

    System.out.println("before unpark");

    //释放许可
    LockSupport.unpark((Thread) object);
    System.out.println("after unpark");
  }
}


/**
 * @Author MagiRui
 * @Description
 * @Date 10:18 AM 6/18/21
 * @Params
 * @return
 *
 *  可以看到，在先调用unpark，再调用park时，仍能够正确实现同步，不会造成由
 *  wait/notify调用顺序不当所引起的阻塞。因此park/unpark相比wait/notify更加的灵活。
 **/
public class ParkUnparkTest2 {

  public static void main(String[] args) {

    MyThread2 myThread2 = new MyThread2(Thread.currentThread());
    myThread2.start();
    try {
      //主线程休眠3s
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("before park");
    //获取许可
    LockSupport.park("ParkUnparkTest2");
    System.out.println("after park");

  }
}
