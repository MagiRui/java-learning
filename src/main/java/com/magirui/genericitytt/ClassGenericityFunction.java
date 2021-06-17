package com.magirui.genericitytt;

/**
 * @author MagiRui
 * @description
 * @date 6/16/21
 */
public class ClassGenericityFunction {

  public <T> T getObject(Class<T> c) throws InstantiationException, IllegalAccessException {

    T t = c.newInstance();
    return t;
  }
}
