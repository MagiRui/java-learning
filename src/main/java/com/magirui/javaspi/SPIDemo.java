package com.magirui.javaspi;

import com.sun.tools.javac.util.ServiceLoader;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class SPIDemo {

  public static void main(String[] args) {

    ServiceLoader<Search> serviceLoader = ServiceLoader.load(
        Search.class
    );

    for(Search fileSearch: serviceLoader) {

      fileSearch.searchDoc("Hello World");
    }
  }
}
