package com.pandaer.class05;

import com.pandaer.class02.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 大于2n问题
 * 描述:
 * 在一个数组中,存在一个数字,比它后面数字的二倍还大,求这样的数的个数
 * 额外空间复制度: O(1)
 * 时间复杂度: O(NlogN)
 * 算法思想:
 * 老套路了,在后面,找小数,只不过这个小变成了 后面数的二倍都还比他小
 */
public class Code04_MoreThanTwice {


    public int moreThanTwice(int[] arr) {
        return process(arr,0,arr.length - 1);
    }


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


    private int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = help.length - 1;
        int p1 = mid;
        int p2 = right;
        int res = 0;

        //先求个数,因为移动的逻辑与有序的逻辑不一样
        while (p1 >= left && p2 >= mid+1) {
            if (arr[p1] > (arr[p2] * 2)) {
                res += p2 - (mid + 1) + 1;
                p1--;
            }else {
                p2--;
            }
        }

        p1 = mid;
        p2 = right;
        while (p1 >= left && p2 >= mid+1) {
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
                int res1 = moreThanTwice(arr1);
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
            int[] arr = {-2, 1, 2, 7, 1, -2};
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);
            int res1 = moreThanTwice(arr1);
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
                    if (arr[i] > arr[j] * 2) {
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
