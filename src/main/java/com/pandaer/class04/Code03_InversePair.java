package com.pandaer.class04;

import com.pandaer.class01.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 逆序对问题
 * 额外空间复制度: O(1)
 * 时间复杂度: O(NlogN)
 * 详细描述:
 * 在一个数组中,一个数和它后面的一个数组合成降序排列,那么这就是一个逆序对,求整个数组中有多少个逆序对.
 * 例子: [1,2,3,9,5,6]
 * 1: None
 * 2: None
 * 3: None
 * 9: 5 , 6 ==> (9,5) (9,6)
 * 5: None
 * 6: None
 * answer: 2个
 * 思路: 在后面,找比他小的个数
 * 这道题和小和问题太像了, [小和问题是在后面,找比他大的数] 所以同样可以用归并排序的思想来解决问题
 */
public class Code03_InversePair {

    /**
     * 逆序对问题
     * @param arr 普通的数组
     * @return 逆序对的个数
     */
    public int inversePair(int[] arr) {
        return process(arr,0,arr.length - 1);
    }

    /**
     * 逆序对问题解法的真实实现
     * 函数定义: 在arr的[left,right]范围内,找出逆序对的个数并将[left,right]有序
     * @param arr 普通的数组
     * @param left 左边界
     * @param right 右边界
     * @return 逆序对的个数
     */
    private int process(int[] arr, int left, int right) {
        // base case
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftCount = process(arr,left,mid);
        int rightCount = process(arr,mid+1,right);
        int mergeCount = merge(arr,left,mid,right);
        return leftCount + rightCount + mergeCount;
    }

    /**
     * 从[left,mid]中取出一个数,在[mid+1,right]中寻找比它小的数 并 让[left,right]整体有序
     * @param arr [left,mid]有序,[mid+1,right]有序
     * @param left 左边界
     * @param mid 中值
     * @param right 右边界
     * @return 逆序对的个数
     */
    private int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = help.length - 1;
        int p1 = mid;
        int p2 = right;
        int res = 0;

        while (p1 >= left && p2 >= mid+1) {
            res += arr[p1] > arr[p2] ? p2 - (mid + 1) + 1 : 0;
            help[i--] = arr[p1] > arr[p2]? arr[p1--] : arr[p2--];
        }

        while (p1 >= left) {
            help[i--] = arr[p1--];
        }

        while (p2 >= mid + 1) {
            help[i--] = arr[p2--];
        }

        for (i = 0; i<help.length;i++) {
            arr[left + i] = help[i];
        }

        return res;

    }


    @Nested
    class InversePairTester {

        @Test
        public void test() {
            int testTimes = 1000000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen,arrMaxNum);
                int[] arr1 = Arrays.copyOf(arr, arr.length);
                int[] arr2 = Arrays.copyOf(arr, arr.length);
                int res1 = inversePair(arr1);
                int res2 = valid(arr2);
                if (res2 != res1) {
                    System.out.println("测试失败 " + i);
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("算法: " + res1);
                    System.out.println("验证算法: " + res2);
                    return;
                }
            }
            System.out.println("验证通过");
        }

        @Test
        public void test01() {
            int[] arr = {-1, 1, 3, 0, -3};
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);
            int res1 = inversePair(arr1);
            int res2 = valid(arr2);
            System.out.println(res1+ ", " + res2);
        }


        private int valid(int[] arr) {
            if (arr == null || arr.length <= 1) {
                return 0;
            }
            int count = 0;
            for (int i = 0; i< arr.length;i++) {
                for (int j = i + 1; j< arr.length;j++) {
                    if (arr[i] > arr[j]) {
                        count++;
                    }
                }
            }
            return count;
        }

        private int[] randomArray(int arrMaxLen, int arrMaxNum) {
            int len = ((int)(Math.random() * arrMaxLen)) + 1;
            int[] arr = new int[len];
            for (int i = 0; i<len;i++) {
                int num = ((int)(Math.random() * arrMaxNum)) - ((int)(Math.random() * arrMaxNum));
                arr[i] = num;
            }
            return arr;
        }
    }
}
