package com.pandaer.class07;

import com.pandaer.class02.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 堆排序
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N^LogN)
 * 算法思想:
 * 利用堆的性质，如果是大根堆，根节点一定是最大的，那么根节点一定就会落在数组的最后一个位置上。
 */
public class Code03_HeapSort {


    /**
     *  堆排序 是可以不用完全实现堆的，这里为了测试之前写的堆是否正确，借助完整的堆来排序
     */
    public void heapSort01(int[] arr) {
        Code02_Heap.MaxHeap maxHeap = new Code02_Heap.MaxHeap();
        for (int num : arr) {
            maxHeap.push(num);
        }
        for (int i = arr.length - 1; i>=0; i--) {
            arr[i] = maxHeap.pop();
        }
    }

    /**
     * 日常说的堆排序
     */
    public void heapSort02(int[] arr) {
        //堆化
        for (int i = 0; i< arr.length; i++) {
            heapInsert(arr,i);
        }
        //交换
        int headSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr,0,--headSize);
            heapify(arr,0,headSize);
        }

    }

    /**
     * 优化版的堆排序
     */
    public void heapSort03(int[] arr) {
        //堆化
        int headSize = arr.length;
        for (int i = arr.length - 1; i >= 0 ; i--) {
            heapify(arr,i,headSize);
        }
        //交换
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr,0,--headSize);
            heapify(arr,0,headSize);
        }

    }


    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 往上 堆化
     * @param arr
     * @param index
     */
    private void heapInsert(int[] arr, int index) {
        int parentIndex = (index - 1) / 2;
        while (parentIndex >= 0 && arr[index] > arr[parentIndex]) {
            swap(arr,index,parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    /**
     * 向下堆化
     * @param arr
     * @param index
     * @param headSize
     */
    private void heapify(int[] arr, int index, int headSize) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < headSize) {
            int maxChildIndex = leftIndex + 1 < headSize && arr[leftIndex + 1] > arr[leftIndex] ? leftIndex + 1: leftIndex;
            if (arr[maxChildIndex] < arr[index]) {
                break;
            }
            swap(arr,index,maxChildIndex);
            index = maxChildIndex;
            leftIndex = index * 2 + 1;
        }
    }


    @Nested
    class HeapSortTester {

        @Test
        public void test() {
            int testTimes = 100000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen,arrMaxNum);
                int[] sorted1 = Arrays.copyOf(arr, arr.length);
                int[] sorted2 = Arrays.copyOf(arr, arr.length);
                heapSort03(sorted1);
                valid(sorted2);
                if (!Arrays.equals(sorted1,sorted2)) {
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("排序算法: " + Arrays.toString(sorted1));
                    System.out.println("验证算法: " + Arrays.toString(sorted2));
                    return;
                }
            }
            System.out.println("验证通过");
        }

        @Test
        public void test01() {
            int[] arr = {-1, 59, 26, 23};
            int[] sorted1 = Arrays.copyOf(arr, arr.length);
            int[] sorted2 = Arrays.copyOf(arr, arr.length);
            heapSort01(sorted1);
            valid(sorted2);
            System.out.println("原数组: " + Arrays.toString(arr));
            System.out.println("排序算法: " + Arrays.toString(sorted1));
            System.out.println("验证算法: " + Arrays.toString(sorted2));
        }

        @Test
        public void test02() {
            int[] arr = {-56, -21, -63};
            int[] sorted1 = Arrays.copyOf(arr, arr.length);
            int[] sorted2 = Arrays.copyOf(arr, arr.length);
            heapSort02(sorted1);
            valid(sorted2);
            System.out.println("原数组: " + Arrays.toString(arr));
            System.out.println("排序算法: " + Arrays.toString(sorted1));
            System.out.println("验证算法: " + Arrays.toString(sorted2));
        }

        @Test
        public void test03() {
            // -2147483646
            System.out.println(Integer.MIN_VALUE);
            System.out.println(Integer.MAX_VALUE);
        }

        public void valid(int[] arr) {
            Arrays.sort(arr);
        }
    }



}
