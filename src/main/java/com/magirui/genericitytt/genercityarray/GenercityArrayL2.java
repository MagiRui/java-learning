package com.magirui.genericitytt.genercityarray;

import java.lang.reflect.Array;

/**
 * @author MagiRui
 * @description
 * @date 6/16/21
 */
public class GenercityArrayL2 {

  public static void main(String[] args) {

    Integer[] iarray = fun1(1,2,3,4,5);
    fun2(iarray);
  }

  public static <T> T[] fun1(T... arg){

    return arg;
  }

  public static <T> void fun2(T param[]) {

    System.out.println("接收泛型数组:");
    for(T t: param) {

      System.out.println(t + ";");
    }
  }


}



