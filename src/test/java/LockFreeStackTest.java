import com.magirui.nolock.LockFreeStack;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈无锁测试〉
 *
 * @author magirui
 * @create 2018-06-21 上午9:44
 */
public class LockFreeStackTest {

   private LockFreeStack<Integer> stack = new LockFreeStack<Integer>();
   private CountDownLatch start;
   private CountDownLatch end;

   static class Poper extends Thread{

       private LockFreeStack<Integer> stack;
       CountDownLatch start;
       CountDownLatch end;
       AtomicInteger count;

       public Poper(LockFreeStack<Integer> stack, AtomicInteger count, CountDownLatch start, CountDownLatch end){

           this.start = start;
           this.end = end;
           this.count = count;
           this.stack = stack;
       }

       public void run(){

           try{

               start.await();
           }catch (InterruptedException e){

           }

           Integer popValue = stack.pop();
           while(popValue!= null){

                System.out.println(popValue);
               count.getAndIncrement();
               popValue = stack.pop();
           }

           end.countDown();
       }
   }

    static class Pusher extends Thread{

        private LockFreeStack<Integer> stack;

        private int nProduct;

        private CountDownLatch start;

        private CountDownLatch end;


        public Pusher(LockFreeStack<Integer> stack, int n, CountDownLatch start, CountDownLatch end){

            this.stack = stack;
            this.nProduct = n;
            this.start = start;
            this.end = end;
        }

        public void run(){

            try{

                start.await();
            }catch (InterruptedException e){

            }

            for(int i=0; i < nProduct; i++){

                stack.push(i);
            }

            end.countDown();
        }
    }

    @Test
    public void testPop() throws Exception {

        AtomicInteger count = new AtomicInteger(0);
        final int stackSize = 10000;
        final int nThread = 10;

        //init the stack
        int j = stack.size();
        while (j < stackSize) {

            stack.push(j++);
        }
        start = new CountDownLatch(1);
        end = new CountDownLatch(nThread);
        count.set(0);
        for(int t = 0; t < nThread ; t ++){
            new Poper(stack, count, start, end).start();
        }
        start.countDown();
        end.await();

        System.out.println("  stackSize = " + stackSize +"  pop count " + count.get());
    }

}
