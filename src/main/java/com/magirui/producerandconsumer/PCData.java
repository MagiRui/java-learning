package com.magirui.producerandconsumer;

/**
 * γγ
 *
 * @author magirui
 * @create 2018-07-04 δΈε9:15
 */
public class PCData {

    private final int intData;

    public PCData(int intData){

        this.intData = intData;
    }

    public int getData(){

        return intData;
    }

    public String toString(){

        return "data:" + intData;
    }
}
