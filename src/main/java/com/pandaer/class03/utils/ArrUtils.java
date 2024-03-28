package com.pandaer.class03.utils;

public class ArrUtils {


    public static int[] genRandomArray(int arrMaxLen, int arrMaxNum) {
        int len = ((int)(Math.random() * arrMaxLen)) + 1;
        int[] arr = new int[len];

        for (int i = 0; i<len;i++) {
            int num = ((int)(Math.random() * arrMaxNum)) - ((int)(Math.random() * arrMaxNum));
            arr[i] = num;
        }
        return arr;
    }
}
