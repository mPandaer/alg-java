package com.pandaer.class04;

/**
 * 归并排序
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N*LogN)
 * 算法思想:
 * 将大问题拆成小问题,最后整合这些小问题的答案,变成大问题的答案.
 * 将大的数组拆成小数组,让小数组有序,最后再整合这些有序的小数组,从而让大数组有序
 * 总结:
 * 如果在写递归函数的时候,我们只需要假设这个函数已经完成了
 * 当做一个API来使用,利用这个API来完成当前的任务,只不过这个API传入的数据规模必须比当前的数据规模小
 */
public class Code01_MergeSort {

    /**
     * 递归版的归并排序
     * @param arr 待排序的数组
     */
    public void mergeSort01(int[] arr) {
        process01(arr,0,arr.length-1);
    }

    /**
     * 归并排序的真正实现
     * 定义: 在数组的[left,right]区间有序
     * @param arr 待排序的数组
     * @param left 左边界
     * @param right 右边界
     */
    private void process01(int[] arr, int left, int right) {
        //base case
        if (left == right) {
            return;
        }
        int mid = left + ((right-left) >> 1);
        //拆分成小问题
        process01(arr,left,mid);
        process01(arr,mid+1,right);
        //整合小问题的答案
        merge(arr,left,mid,right);
    }


    /**
     * 归并排序的合并过程 -- 整合小问题答案的过程
     * @param arr 待排序的数组
     * @param left 左边界
     * @param mid 范围中值
     * @param right 右边界
     */
    private void merge(int[] arr, int left, int mid, int right) {
        //定义辅助数组
        int[] help = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2<=right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        while (p2 <= right) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i<help.length;i++) {
            arr[left+i] = help[i];
        }
    }




/*=======================================================非递归版==========================================================*/

    /**
     * 归并排序 非递归版
     * @param arr 待排序的数组
     */
    public void mergeSort02(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int size = arr.length;
        int step = 1;

        while (step < size) {
            int left = 0; //第一次左组的第一个位置

            while (left < size) {
                int mid = left + step - 1; //左组最后一个位置
                if (mid >= size) {
                    break;
                }
                int right = mid + step; //右组最后一个位置
                if (right >= size) {
                    right = size - 1;
                }
                merge(arr,left,mid,right);
                left = right + 1;
            }

            if (step > size/2) {
                break;
            }
            step <<= 1; //扩大两倍
        }
    }


    /**
     * 暂时没有对数器 线上OJ: https://leetcode.cn/problems/sort-an-array/submissions/516638059/
     */

}
