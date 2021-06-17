package com.magirui.commontest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 〈〉
 *
 * @author magirui
 * @create 2018-06-20 上午11:41
 */
public class CommonTest {

    @Test
    public void commonTest(){

        List<Object> list = new ArrayList<Object>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        List<Object> vList = new ArrayList<Object>(list);

        list.clear();
        System.out.println(vList);
        System.out.println(list);
    }

    @Test
    public void commonTest1(){


    }
}
