package com.pandaer.class02;


import com.pandaer.class02.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * 选择排序
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N^2)
 * 算法思想:
 * 从数组中选一个最小的排到数组的开头,再在剩下的数组中选一个最小的排在第二的位置,以此类推,就可以有序了
 */
public class Code01_SelectSort {

    /**
     * 选择排序
     * @param arr 要排序的数组
     */
    public static void selectSort(int[] arr) {
        if (Objects.isNull(arr) || arr.length < 2) {
            return;
        }

        // 0 ~ n-1
        // 1 ~ n-1
        for (int i = 0; i< arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j< arr.length;j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }

    /**
     * 验证器 验证选择排序是否正确
     * @param arr
     */
    public static void valid(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 选择排序的对数器
     */
    @Nested
    class SelectSortTester {
        @Test
        public void test() {
            int testTimes = 100000;
            int arrMaxLen = 4;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen,arrMaxNum);
                int[] sorted1 = Arrays.copyOf(arr, arr.length);
                int[] sorted2 = Arrays.copyOf(arr, arr.length);
                selectSort(sorted1);
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

    }


}
