package com.magirui.finallytt;

/**
 * @author MagiRui
 * @description
 * @date 6/16/21
 */
public class FinallyReturn {

  public static int cal() {

    int ix = 9;
    try{

      System.out.println("try a + b ");
      return ++ix;
    }finally {

      System.out.println("finally a + b + 1");
      ix = 1;
    }
  }

  public static void main(String[] args) {

    System.out.println(cal());
  }
}
