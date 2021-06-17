package com.magirui.nolock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 〈无锁堆栈〉
 *
 * @author magirui
 * @create 2018-06-21 上午8:45
 */
public class LockFreeStack<V> {

    class Node<T> {

        private T value;
        AtomicReference<Node<T>> next ;

        public Node(T value, Node<T> next) {
            super();
            this.value = value;
            this.next = new AtomicReference<Node<T>>(next);
        }


    }

    private AtomicReference<Node<V>> head = new AtomicReference<Node<V>>(null);
    private AtomicInteger size = new AtomicInteger(0);


    public LockFreeStack(){

    }

    public void push(V o){

       if(o == null){

           throw new NullPointerException("value is NUll");
       }

       Node<V> newNode = new Node<V>(o, null);
       Node<V> oldHead;
       do{

            oldHead = head.get();
            newNode.next.set(oldHead);
       }while(!head.compareAndSet(oldHead, newNode));

        size.getAndIncrement();

    }

    public V pop(){

        Node<V> oldHead = null;
        Node<V> next = null;

        do{

            oldHead = head.get();
            if(oldHead == null){

                return null;
            }

            next = oldHead.next.get();

        }while(!head.compareAndSet(oldHead, next));

        size.incrementAndGet();
        return oldHead.value;
    }

    public int size(){

        return size.get();
    }

    public boolean isEmpty(){

        return head.get() == null;
    }
}
