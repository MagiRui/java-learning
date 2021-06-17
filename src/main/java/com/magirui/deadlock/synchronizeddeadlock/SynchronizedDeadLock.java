package com.magirui.deadlock.synchronizeddeadlock;

/**
 * 〈〉
 *
 * @author magirui
 * @create 2018-06-27 下午1:54
 */
public class SynchronizedDeadLock {

    public static void main(String[] args){

        final Object obj1 = new Object();
        final Object obj2 = new Object();

        new Thread(){

            public void run(){

                synchronized (obj1){


                    try {

                        System.out.println("thread name. threadId:" + Thread.currentThread().getName() + " hold obj1 lock");
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                    synchronized (obj2){

                        try{

                            System.out.println("thread name. threadId:" + Thread.currentThread().getName() + " hold obj2 lock");
                            Thread.sleep(2000);

                        }catch (Exception ex){

                            ex.printStackTrace();
                        }
                    }

                }
            }
        }.start();

        new Thread(){

            public void run(){

               synchronized (obj2){
                    System.out.println("thread name. threadId:" + Thread.currentThread().getName() + " hold obj2 lock");
                   try {
                       Thread.sleep(2000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   synchronized (obj1){

                        try{

                            System.out.println("thread name. threadId:" + Thread.currentThread().getName() + " hold obj1 lock");
                            Thread.sleep(2000);

                        }catch (Exception ex){

                            ex.printStackTrace();
                        }
                    }

                }
            }
        }.start();
    }
}
