package com.magirui.classloander;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class MyClassLoader extends ClassLoader{

  private String root;

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {

    byte[] classData = loadClassData(name);
    if(classData == null) {

      throw  new ClassNotFoundException();
    }

    return defineClass(name, classData, 0, classData.length);
  }

  private byte[] loadClassData(String className) {

    String fileName = root + File.separatorChar
        + className.replace('.', File.separatorChar) + ".class";
    try{

      InputStream ins = new FileInputStream(fileName);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];
      int length = 0;
      while((length = ins.read(buffer)) != -1) {

        baos.write(buffer, 0, length);
      }

      return baos.toByteArray();


    }catch (IOException exception) {

      exception.printStackTrace();
    }

    return null;
  }

  public String getRoot() {
    return root;
  }

  public void setRoot(String root) {
    this.root = root;
  }

  public static void main(String[] args) {

    MyClassLoader classLoader = new MyClassLoader();
    classLoader.setRoot("/Users/magirui/test_my_class_loader");
    Class<?> testClass = null;
    try{

      testClass = classLoader.loadClass("TestMyClassLoader");
      Object object = testClass.newInstance();

      System.out.println(object.getClass().getClassLoader());
    }catch (Exception ex) {

      ex.printStackTrace();
    }
  }
}
