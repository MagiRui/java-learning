package com.magirui.parallelsort;

/**
 * 〈简单的奇偶排序〉
 *
 * @author magirui
 * @create 2018-07-05 下午9:18
 */
public class SampleSortTest {

    public static void oddEventSort(int[] arr){

        int exchFlag = 1;
        int start = 0;

        while(exchFlag == 1 || start == 1){

            exchFlag = 0;
            for(int i = start; i < arr.length - 1; i+=2){

                if(arr[i] > arr[i+1]){

                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    exchFlag = 1;
                }
            }

            if(start == 0){

                start = 1;
            }else{

                start = 0;
            }
        }
    }

    public static void main(String[] args){

        SampleSortTest.oddEventSort(new int[]{0, 8, 7, 9, 2});
    }
}
