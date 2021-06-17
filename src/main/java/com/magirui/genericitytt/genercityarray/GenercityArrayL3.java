package com.magirui.genericitytt.genercityarray;

import java.util.ArrayList;

/**
 * @author MagiRui
 * @description
 * @date 6/16/21
 */
public class GenercityArrayL3 {

  public static void main(String[] args) {

    int i = GenercityArrayL3.add(1,2);
    Number ix = GenercityArrayL3.add(1,2.3);
    Object obj = GenercityArrayL3.add(1,"abc");
    System.out.println("i=" + i + "; ix=" + ix + "; obj=" + obj);

    int a = GenercityArrayL3.<Integer>add(1,2);
    System.out.println(a);


    ArrayList<String> list1 = new ArrayList();
    list1.add("1"); //编译通过
    //list1.add(1); //编译错误
    String str1 = list1.get(0); //返回类型就是String

    ArrayList list2 = new ArrayList<String>();
    list2.add("1"); //编译通过
    list2.add(1); //编译通过
    Object object = list2.get(0); //返回类型就是Object

    new ArrayList<String>().add("11"); //编译通过
    //new ArrayList<String>().add(22); //编译错误

    String str2 = new ArrayList<String>().get(0); //返回类型就是String

  }

  public static <T> T add(T x, T y){

    return y;
  }
}
