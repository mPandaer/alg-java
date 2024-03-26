package com.pandaer.class01;

import com.pandaer.class01.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * 冒泡排序
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N^2)
 * 算法思想:
 * 每轮比较中选出一个最大的放在最后,只是这个选择的方式不一样,不是直接选最大最小,而是两两比较
 */
public class Code02_BubbleSort {

    /**
     * 冒泡排序
     * @param arr 待排序的数组
     */
    public void bubbleSort(int[] arr) {
        if (Objects.isNull(arr) || arr.length < 2) {
            return;
        }
        // 0 ~ n-1
        // 0 ~ n-2
        // 0 ~ n-3
        // 0 ~ n-4
        // ...
        // 0 ~ 1
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i;j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }

    }


    @Nested
    class BubbleSortTester {
        @Test
        public void test() {
            int testTimes = 1000000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen,arrMaxNum);
                int[] sorted1 = Arrays.copyOf(arr, arr.length);
                int[] sorted2 = Arrays.copyOf(arr, arr.length);
                bubbleSort(sorted1);
                valid(sorted2);
                if (!Arrays.equals(sorted1,sorted2)) {
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("选择排序算法: " + Arrays.toString(sorted1));
                    System.out.println("验证算法: " + Arrays.toString(sorted2));
                    return;
                }
            }
            System.out.println("验证通过");

        }

        public void valid(int[] arr) {
            Arrays.sort(arr);
        }
    }
}
