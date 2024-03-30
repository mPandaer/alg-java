package com.pandaer.class07;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给数组排序
 * 数组的特点：数组中的每个元素距排序好后的位置，移动不超过K个距离
 * 额外空间复制度: O(k)
 * 时间复杂度: O(NLogK)
 * 算法思想:
 * 利用堆，比如排序后数组的0号位置的数 一定在[0,0+k]这个范围内
 * 所以我们可以利用小根堆，将 [0,0+k]的数堆化，然后根节点的数一定就是排好序的0号位置的数
 */
public class Code04_SortArrayDistanceLessK {

    public void sortArrayDistanceLessK(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i <= Math.min(k, arr.length - 1); i++) {
            heap.add(arr[i]);
        }
        int i = 0;
        for (; i < arr.length; i++) {
            arr[i] = heap.poll();
            if (i + k + 1 < arr.length) {
                heap.add(arr[i + k + 1]);
            }
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }

    }

    public void sortArrayDistanceLessK01(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index;

        //先加入k-1个
        for (index = 0; index < Math.min(k, arr.length); index++) {
            heap.add(arr[index]);
        }

        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }

    }


    @Nested
    class SortArrayDistanceLessKTester {

        @Test
        public void test() {
            int testTime = 500000;
            int maxSize = 100;
            int maxValue = 100;
            for (int i = 0; i < testTime; i++) {
                int k = (int) (Math.random() * maxSize) + 1;
                int[] arr = randomArrayNoMoveMoreK(maxSize, maxValue, k);
                int[] arr1 = Arrays.copyOf(arr, arr.length);
                int[] arr2 = Arrays.copyOf(arr, arr.length);
                sortArrayDistanceLessK01(arr1, k);
                valid(arr2, k);
                if (!Arrays.equals(arr1, arr2)) {
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("排序算法: " + Arrays.toString(arr1));
                    System.out.println("验证算法: " + Arrays.toString(arr2));
                    return;
                }
            }
            System.out.println("验证通过");
        }

        public void valid(int[] arr, int k) {
            Arrays.sort(arr);
        }


        public int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
            int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            }
            // 先排个序
            Arrays.sort(arr);
            // 然后开始随意交换，但是保证每个数距离不超过K
            // swap[i] == true, 表示i位置已经参与过交换
            // swap[i] == false, 表示i位置没有参与过交换
            boolean[] isSwap = new boolean[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
                if (!isSwap[i] && !isSwap[j]) {
                    isSwap[i] = true;
                    isSwap[j] = true;
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
            return arr;
        }

    }
}
