package com.magirui.jdkconcurrent.threadpool;

import java.util.concurrent.*;

/**
 * 〈线程池中寻找堆栈〉
 *
 * @author magirui
 * @create 2018-06-19 下午3:14
 */
public class DivTask implements Runnable{

    int a, b;

    public DivTask(int a, int b){

        this.a = a;
        this.b = b;
    }

    public void run(){

        double re = a / b;
        System.out.println(re);
    }

    public static void main(String[] args)throws InterruptedException, ExecutionException{

        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0l, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        for(int i = 0; i < 5; i++){

            //pools.submit(new DivTask(100, i));//无任何异常堆栈信息
            //pools.execute(new DivTask(100, i));//可以打印出部分异常

            Future re = pools.submit(new DivTask(100, i));
            re.get();
        }
    }

}
