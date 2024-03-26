package com.pandaer.class01;

import com.pandaer.class01.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * 插入排序
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N^2) 最好可以做到 O(N)
 * 算法思想:
 * 假设数据是增长的,而不是一开始就存在了,在每个增长的时刻,维护局部有序,当数据不在增长了,就全局有序了
 * 当前轮的比较,依赖了上一轮的结果即 基于当前数组有序,给新进来的数组找一个合适位置
 */
public class Code03_InsertSort {

    /**
     * 插入排序
     * @param arr 待排序的数组
     */
    public void insertSort(int[] arr) {
        if (Objects.isNull(arr) || arr.length < 2) {
            return;
        }

        // 0 ~ 0 已经有序
        // 0 ~ 1
        // 0 ~ 2
        // 0 ~ 3
        // 0 ~ 4
        // 0 ~ 5
        // 0 ~ n-1
        for (int i = 1; i< arr.length;i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] >= arr[j-1]) {
                    break;
                }
                int tmp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = tmp;
            }
        }
    }

    @Nested
    class InsertSortTester {
        @Test
        public void test() {
            int testTimes = 100000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen,arrMaxNum);
                int[] sorted1 = Arrays.copyOf(arr, arr.length);
                int[] sorted2 = Arrays.copyOf(arr, arr.length);
                insertSort(sorted1);
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
