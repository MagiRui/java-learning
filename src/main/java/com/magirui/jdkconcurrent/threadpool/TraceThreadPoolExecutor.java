package com.magirui.jdkconcurrent.threadpool;

import java.util.concurrent.*;

/**
 * 〈线程池的堆栈信息〉
 *
 * @author magirui
 * @create 2018-06-19 下午3:30
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {

    public TraceThreadPoolExecutor(int corePoolSize, int maximunPoolSize,
                                   long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue){

        super(corePoolSize, maximunPoolSize, keepAliveTime, unit, workQueue);
    }

    public void execute(Runnable task){

        super.execute(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    public Future<?> submit(Runnable task){

        return super.submit(wrap(task, clientTrace(), Thread.currentThread().getName()));
    }

    private Exception clientTrace(){

        return new Exception("Client stack trace");
    }

    private Runnable wrap(final Runnable task, final Exception clientStack, String clientThreadName) {

        return new Runnable() {
            public void run() {

                try{

                    task.run();
                }catch (Exception e){

                    clientStack.printStackTrace();
                    throw  new RuntimeException(e);
                }
            }
        };

    }

    public static void main(String[] args){

        ThreadPoolExecutor pools = new TraceThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        for (int i = 0; i< 5; i++){

            pools.execute(new DivTask(100, i));
        }
    }
}
