package com.pandaer.class04;

/**
 * 递归思想 -- 一个大问题能够拆分出同等结构的小问题, 大 小 指的是数据规模的问题
 * Master公式 快速计算递归问题的时间复杂度
 * 有一类递归问题 子问题的规模大小是一样的 T(n) = a * T(N/b) + O(N^d)
 * logb^a < d  ===>> O(N^d)
 * logb^a > d  ===>> O(N^logb^a)
 * logb^a = d  ===>> O(LogN*N^d)
 */


public class Code08_RecursiveThink {

    /**
     * 利用递归寻找数组中的最大值
     *
     * 函数的定义: 在指定的 [start,end]区间寻找最大值
     * @return 返回数组中的最大值
     */
    public int getMax(int[] arr,int start,int end) {
        //列举base case
        if (start == end) {
            return arr[start];
        }
        int mid = start + ((end - start) >> 1);
        int leftMax = getMax(arr,start,mid);
        int rightMax = getMax(arr,mid + 1,end);
        return Math.max(leftMax,rightMax);
    }


    /**
     * 利用递归 解决数组中找值的问题 找到了返回对应的下标,没有找到返回-1
     *
     * 函数的定义: 在指定的区间 找值
     */
    public int find(int[] arr,int left,int right,int target) {
        if (left == right) {
            if (arr[left] == target) {
                return left;
            }
            return -1;
        }
        int mid = left + ((right - left) >> 1);
        int leftRes = find(arr,left,mid,target);
        int rightRes = find(arr,mid + 1,right,target);
        return Math.max(leftRes,rightRes);
    }


}
