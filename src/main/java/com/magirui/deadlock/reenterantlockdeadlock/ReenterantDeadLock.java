package com.magirui.deadlock.reenterantlockdeadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈〉
 *
 * @author magirui
 * @create 2018-06-28 上午11:23
 */
public class ReenterantDeadLock {


    public static void main(String[] args){

        final ReentrantLock r1 = new ReentrantLock();
        final ReentrantLock r2 = new ReentrantLock();


        new Thread(){

            public void run(){

                try{

                    r1.lockInterruptibly();
                    System.out.println("thread name. threadId:" + Thread.currentThread().getName() + " hold r1 lock");
                    Thread.sleep(1000);
                }catch (Exception ex){

                    ex.printStackTrace();

                }

                try{

                    r2.lockInterruptibly();
                    System.out.println("thread name. threadId:" + Thread.currentThread().getName() + " hold r2 lock");
                    Thread.sleep(1000);
                }catch (Exception ex){

                    ex.printStackTrace();

                }finally {

                    if(r2.isHeldByCurrentThread()){

                        r2.unlock();
                    }

                    if(r1.isHeldByCurrentThread()){

                        r1.unlock();
                    }
                }



            }
        }.start();

        new Thread(){

            public void run(){

                try{

                    r2.lockInterruptibly();
                    System.out.println("thread name. threadId:" + Thread.currentThread().getName() + " hold r2 lock");
                    Thread.sleep(1000);
                }catch (Exception ex){

                    ex.printStackTrace();

                }

                try{

                    r1.lockInterruptibly();
                    System.out.println("thread name. threadId:" + Thread.currentThread().getName() + " hold r1 lock");
                    Thread.sleep(1000);
                }catch (Exception ex){

                    ex.printStackTrace();

                }finally {

                    if(r1.isHeldByCurrentThread()){

                        r1.unlock();
                    }

                    if(r2.isHeldByCurrentThread()){

                        r2.unlock();
                    }
                }
            }
        }.start();
    }
}
