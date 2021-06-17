package com.magirui.jdkconcurrent.forkjoin;




import java.math.BigDecimal;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈反函数的面积〉
 *
 * @author magirui
 * @create 2018-06-25 下午9:40
 */
public class AreaCalc {

   public static class NodeArea extends RecursiveTask<Double> {

       public static final double step = 0.01d;
       public static final double slic_num = 10;

       public static AtomicInteger counter = new AtomicInteger(0);

       private double startLoc = 0.0f;
       private double endLoc = 0.0f;

       public NodeArea(double startLoc, double endLoc){

           this.startLoc = startLoc;
           this.endLoc = endLoc;
       }

       protected Double compute(){

           double sum = 0.0d;

           boolean canCompute = (endLoc - startLoc) <= step;
           if(canCompute){

               sum = calc(startLoc, endLoc);


           }else{

               double slice = (endLoc - startLoc) / slic_num;
               for(int i = 0; i< slic_num; i++){

                   NodeArea task =
                           new NodeArea(startLoc + i*slice, startLoc + (i+1)*slice);
                   task.fork();
                   counter.incrementAndGet();
                   sum += task.join();
               }

           }

           return sum;
       }


       private double calc(double startXLoc, double endXloc){

           BigDecimal reactArea = ((new BigDecimal(1)).divide(new BigDecimal(endLoc), 10, 5))
                   .multiply(new BigDecimal(step));
           BigDecimal trigleArea =  new BigDecimal(0.5).multiply(
                   (((new BigDecimal(1)).divide(new BigDecimal(startXLoc), 10, 5)).subtract((new BigDecimal(1)).divide(new BigDecimal(endXloc), 10, 5))));
           return reactArea.add(trigleArea).doubleValue();
       }

       public static void main(String[] args){

           NodeArea nodeArea = new NodeArea(1, 100);
           ForkJoinPool forkJoinPool = new ForkJoinPool();
           Future<Double> result = forkJoinPool.submit(nodeArea);
           long startTime = System.currentTimeMillis();
           try{

               System.out.println("Final Area:" + result.get());
               System.out.println("end time ... " + (System.currentTimeMillis() - startTime));
           }catch (Exception ex){

           }
       }
   }

}
