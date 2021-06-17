package com.magirui.deadlock;

import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 * @author MagiRui
 * @description
 * @date 6/17/21
 */
public class CollectionL1 {

  public static void main(String[] args) {

    HashSet<String> strHashSet = new HashSet<>();
    strHashSet.add("abc");

    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
    linkedHashMap.put("abc", "abc1");
  }
}
