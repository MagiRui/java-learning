package com.magirui.genericitytt;

import java.util.Iterator;
import java.util.List;

/**
 * @author MagiRui
 * @description
 * @date 6/16/21
 */

public class GenericityLimit {

  class A{

  }

  class B extends A{

  }


  public static void funA(A a) {


  }

  public static void funB(B b) {

    funA(b);
  }


  public static void funC(List<A> listA) {

  }

  /*public static void funD(List<B> listB) {


    funC(listB);
  }*/

  public static void funE(List<? extends A> listC) {

  }

  public static void funF(List<B> listF) {


    funE(listF);
  }

  private  <E extends Comparable<? super E>> E max(List<? extends E> e1) {

    if (e1 == null){

      return null;
    }

    //迭代器返回的元素属于 E 的某个子类型
    Iterator<? extends E> iterator = e1.iterator();
    E result = iterator.next();
    while (iterator.hasNext()){
      E next = iterator.next();
      if (next.compareTo(result) > 0){
        result = next;
      }
    }
    return result;
  }

}
