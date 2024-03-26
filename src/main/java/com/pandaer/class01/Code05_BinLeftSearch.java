package com.pandaer.class01;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

/**
 * 二分查找 最左>=target的数的下标
 * 额外空间复制度: O(1)
 * 时间复杂度: O(LogN)
 * 算法思想:
 * 在有序数组中, 通过二分,一直分到底,找到>=target的最左边的数
 */
public class Code05_BinLeftSearch {

    /**
     * 二分查找 >=target最左边的数
     * @param arr 有序的数组
     * @param target 要找>=target的target
     * @return 最左数的下标 没有找到返回-1
     */
    public int binLeftSearch(int[] arr,int target) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int resIndex = -1;
        while (left <= right) {
            int mid = left + ((right-left) >> 1);
            if (arr[mid] >= target) {
                resIndex = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return resIndex;
    }

    @Nested
    class BinLeftSearchTester {

        @Test
        public void test() {
            int testTimes = 100_0000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int target = (int)(Math.random() * arrMaxNum) - (int)(Math.random() * arrMaxNum);
                int[] arr = randomArray(arrMaxLen,arrMaxNum,target);

                int res1 = binLeftSearch(arr, target);
                int res2 = valid(arr, target);

                if (!Objects.equals(res1,res2)) {
                    System.out.println("原数组: " + Arrays.toString(arr) + " 目标数: " + target);
                    System.out.println("二分查找算法: " + res1);
                    System.out.println("验证算法: " + res2);
                    return;
                }
            }
            System.out.println("验证通过");
        }

        public int valid(int[] arr,int target) {
            for (int i =0; i< arr.length;i++) {
                if (arr[i] >= target) {
                    return i;
                }
            }
            return -1;
        }

        public int[] randomArray(int maxLen,int maxNum,int target) {
            int len = ((int)(Math.random() * maxLen)) + 1;
            int[] arr = new int[len];

            for (int i = 1; i< len; i++) {
                int num;
                do {
                    num = (int)(Math.random() * maxNum) - (int)(Math.random() * maxNum);
                }while (Objects.equals(num,target));

                arr[i] = num;
            }

            // 一半概率存在目标数
            arr[0] = Math.random() < 0.5 ? target : (int)(Math.random() * maxNum) - (int)(Math.random() * maxNum);

            //随机交换目标数位置
            int randomIndex = ((int)(Math.random() * len));
            int tmp = arr[0];
            arr[0] = arr[randomIndex];
            arr[randomIndex] = tmp;

            Arrays.sort(arr);

            return arr;
        }


    }
}
