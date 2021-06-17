package com.magirui.classloander;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class ClassLoanderTT {

  public static void main(String[] args) throws ClassNotFoundException {

    ClassLoader loader = TT.class.getClassLoader();
    System.out.println(loader);
    //使用ClassLoader.loadClass()来加载类,不会执行初始化块
    loader.loadClass("com.magirui.classloander.TT");

    //使用Class.forName()来加载类,默认会执行初始化块
    Class.forName("com.magirui.classloander.TT");

    //使用Class.forName来加载类,并执行Classloader，初始化不执行静态块
    Class.forName("com.magirui.classloander.TT", false, loader);
  }
}
