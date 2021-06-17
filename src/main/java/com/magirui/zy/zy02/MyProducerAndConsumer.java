package com.magirui.zy.zy02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 * 〈消费队列〉
 *
 * @author magirui
 * @create 2018-06-20 上午10:43
 */
public class MyProducerAndConsumer {

    public static class ProducerConsumerList<V>{

        private List<V> list = new LinkedList<V>();

        public void add(V v){

            synchronized (list){

                if(list.size() == 10 ){

                    try {

                        list.wait();
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                        Thread.currentThread().interrupt();

                    }
                }

                list.add(v);
                System.out.println(System.currentTimeMillis() + "放入元素:" + v);
                list.notifyAll();
            }

        }

        public List<V> get(){

            synchronized (list){

                if(list.size() == 0 ){

                    try {
                        list.wait();
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }


                List<V> vList = new ArrayList<V>(list);
                list.clear();
                list.notifyAll();
                return vList;
            }

        }
    }


    public static void main(String[] args){

        final ProducerConsumerList<Integer> producerConsumerList = new ProducerConsumerList<Integer>();

        Thread producer = new Thread("producer"){

            public void run(){

                for(int i = 0; i< 20; i++){

                    producerConsumerList.add(i);
                }

            }
        };

        Thread consumer = new Thread("consumer"){

            public void run(){

                while(true){

                    List<Integer>  v1 = producerConsumerList.get();
                    for(int i = 0; i < v1.size(); i++){

                        System.out.println(v1.get(i));
                    }
                }

            }
        };

        producer.start();
        consumer.start();

    }

}
