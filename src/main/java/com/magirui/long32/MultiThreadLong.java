package com.magirui.long32;

/**
 * 〈多线程操作long〉
 *
 * @author magirui
 * @create 2018-06-20 下午5:20
 */
public class MultiThreadLong {

    public static long t = 0;

    public static class ChangeT implements Runnable{

        private long to = 0;

        public ChangeT(long to){

            this.to = to;
        }

        public void run(){

            while(true){

                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable{


        public void run(){

            while(true){

                long tmp = MultiThreadLong.t;
                if(tmp != 111L && tmp != -999l && tmp != 333l && tmp != -444l){

                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args){

        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333l)).start();
        new Thread(new ChangeT(-444l)).start();
        new Thread(new ReadT()).start();
    }
}
