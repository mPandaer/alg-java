package com.pandaer.class02;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 二分寻找局部最小值
 * 详细描述: 一个特殊的数组,相邻不相等,选择极小值,规定 arr[0] < arr[1] arr[0] 为最小值 arr[n-1] < arr[n-2] arr[n-1]为最小值
 *          arr[i]< arr[i-1] && arr[i]< arr[i+1] arr[i]为极小值
 * 额外空间复制度: O(1)
 * 时间复杂度: O(LogN)
 * 算法思想:
 * 由于数组的特殊性,如果将这些点画出一个图,一定是没有直线的曲线图,在arr[0] arr[n-1] 不是极小值的情况下,一定存在一个极小值
 */
public class Code07_BinLocalMinExist {

    /**
     * 二分寻找局部最小值
     * @param arr 带寻找的数据
     * @return 一个局部最小值的下标 如果数据非法返回-1
     */
    public int binLocalMinExist(int[] arr) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return -1;
        }
        if (arr.length < 2 || arr[0] < arr[1]) {
            return arr[0];
        }
        int size = arr.length;
        if (arr[size - 1] < arr[size -2]) {
            return arr[size - 1];
        }

        int left = 0;
        int right = size - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            }else if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    @Nested
    class BinLocalMinTester {

        @Test
        public void testDemo() {
            int[] arr = new int[]{-21,-35,-53,24};
            int i = binLocalMinExist(arr);
            Set<Integer> valid = valid(arr);
            System.out.println(i);
            System.out.println(valid);
        }

        @Test
        public void test() {
            int testTimes = 100_0000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = randomArray(arrMaxLen,arrMaxNum);
                int res1 = binLocalMinExist(arr);
                Set<Integer> resList = valid(arr);
                if (!resList.contains(res1)) {
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("二分查找算法: " + res1);
                    System.out.println("验证算法: " + resList);
                    return;
                }
            }
            System.out.println("验证通过");
        }

        public Set<Integer> valid(int[] arr) {
            if (Objects.isNull(arr) || arr.length == 0) {
                return Collections.singleton(-1);
            }
            if (arr.length < 2 || arr[0] < arr[1]) {
                return Collections.singleton(arr[0]);
            }
            int size = arr.length;
            if (arr[size - 1] < arr[size -2]) {
                return Collections.singleton(arr[size - 1]);
            }
            Set<Integer> list = new HashSet<>();
            for (int i = 1; i< size - 1; i++) {
                if (arr[i] < arr[i-1] && arr[i] < arr[i + 1]) {
                    list.add(i);
                }
            }
            return list;
        }

        public int[] randomArray(int maxLen,int maxNum) {
            int len = ((int)(Math.random() * maxLen)) + 1;
            int[] arr = new int[len];
            for (int i = 0; i< len; i++) {
                int num;
                do {
                    num = (int)(Math.random()  * (System.currentTimeMillis() % maxNum)) - (int)(Math.random()  * (System.currentTimeMillis() % maxNum));
                }while ( i > 0 && num == arr[i-1]);

                arr[i] = num;
            }
            return arr;
        }
    }
}
