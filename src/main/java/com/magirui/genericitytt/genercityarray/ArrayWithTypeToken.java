package com.magirui.genericitytt.genercityarray;


import java.lang.reflect.Array;

/**
 * @author MagiRui
 * @description
 * @date 6/16/21
 */
public class ArrayWithTypeToken<T> {

  private T[] array;

  public ArrayWithTypeToken(Class<T> type, int size) {

    array = (T[]) Array.newInstance(type, size);
  }

  public void put(int index, T item) {

    array[index] = item;
  }

  public T get(int index) {

    return array[index];
  }

  public T[] create() {

    return array;
  }

  public static void main(String[] args) {

    ArrayWithTypeToken<Integer> arrayWithTypeToken =
        new ArrayWithTypeToken<Integer>(Integer.class, 100);
    Integer[] array = arrayWithTypeToken.create();
  }
}
