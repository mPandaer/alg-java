package com.pandaer.class05;

import com.pandaer.class02.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 小和问题
 * 详细描述: 一个数组中,如果当前数的左边存在比他小的数,就加起来,周而复始,直到数组的末尾,返回整个小数的和.
 * 额外空间复制度: O(1)
 * 时间复杂度: O(NlogN)
 * 解题思路:
 * 题目最后的返回值并没有要求返回每个数的最小和,而返回整个数组的最小和,即每个数的小和加起来.
 * 从具体的例子中寻找规律
 * [1,2,3,9,6,5]
 * 1: None
 * 2: 1
 * 3: 1 + 2
 * 9: 1 + 2 + 3
 * 6: 1 + 2 + 3
 * 5: 1 + 2 + 3
 * sum: 5*1 + 2*4 + 3*3
 * 可以很明显的发现,除了那一种显而易见的思路(先求每个数的小和,再加起来)外,还可以倒过来想问题
 * 新思路: 如果一个数的右边存在n个数,比自己大,那么在最后的结果中,一定包含 [自己 * n]的这个值
 * 如何可以更快的寻找比自己大的数呢? 如果这个数组是有序数组的话,寻找比自己大的数,只需要找到第一个比自己大的数就可以了.因为后面的数一定比自己大
 * 可是题目没有给数组有序,那如果我们人为将数组有序可以吗? 答案是不行的,因为将数字在数组中的相对关系改变了.
 * 重新回到新思路上来,我们要的并不是整个数组的有序性,我们需要的是,当前数后面的数有序,然后找到第一个大值,就可以得到有多少个比自己大了
 * 感觉有点像分组有序,比如将当前数之前的划分为一组,当前数之后划分为一组,让他们有序,那么这个时候,右组出现第一个大于当前数的时候,就可以知道结果中有几个自己了
 * 既然是分组有序,那么归并排序的思路就可以用进来了.
 */
public class Code02_SmallSum {

    /**
     * 小和问题
     *
     * @param arr 普通的数组
     * @return 整个数组的小和
     */
    public int smallSum(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    /**
     * 小和问题真实实现
     * 函数定义: 在数组的[left,right]范围里求小和并让[left,right]范围有序
     *
     * @param arr   普通的数组
     * @param left  左边界
     * @param right 右边界
     * @return 小和
     */
    private int process(int[] arr, int left, int right) {
        //base case
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        int leftRes = process(arr, left, mid);
        int rightRes = process(arr, mid + 1, right);
        int mergeRes = merge(arr, left, mid, right);
        return leftRes + rightRes + mergeRes;
    }

    /**
     * 整合[left,mid]在[mid + 1,right]中的小和
     *
     * @param arr   在[left,mid]有序 [mid+1,right]有序
     * @param left  左边界
     * @param mid   中值
     * @param right 右边界
     * @return 左组在右组中的小和
     */
    private int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        int res = 0;

        while (p1 <= mid && p2 <= right) {
            res += arr[p1] < arr[p2] ? (right - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= right) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }

        return res;

    }


    @Nested
    class SmallSumTester {

        @Test
        public void test() {
            int testTimes = 1000000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen,arrMaxNum);
                int[] arr1 = Arrays.copyOf(arr, arr.length);
                int[] arr2 = Arrays.copyOf(arr, arr.length);
                int res1 = smallSum(arr1);
                int res2 = valid(arr2);
                if (res2 != res1) {
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
            int[] arr = {1,2,3,9,6,5};
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);
            int res1 = smallSum(arr1);
            int res2 = valid(arr2);
            System.out.println(res1+ ", " + res2);
            System.out.println(5 + 2*4 + 3*3);
        }


        private int valid(int[] arr) {
            if (arr == null || arr.length <= 1) {
                return 0;
            }
            int res = 0;
            for (int i = 0; i< arr.length;i++) {
                for (int j = i-1; j >=0; j--) {
                    if (arr[j] < arr[i]) {
                        res += arr[j];
                    }
                }
            }
            return res;
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
