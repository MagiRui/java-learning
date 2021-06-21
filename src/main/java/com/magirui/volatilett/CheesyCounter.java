package com.magirui.volatilett;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class CheesyCounter {

  private volatile int value;

  public synchronized int increment(){

    return value++;
  }
}
