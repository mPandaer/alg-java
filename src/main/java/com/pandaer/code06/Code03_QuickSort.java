package com.pandaer.code06;

import com.pandaer.class02.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 随机快速排序
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N^LogN)
 * 算法思想:
 * 递归使用荷兰国旗问题的解法就可以解决问题
 */
public class Code03_QuickSort {


    /**
     * 利用荷兰国旗问题的解法,进行快速排序  递归写法
     * @param arr 普通的数组
     */
    public void quickSort(int[] arr) {
        process(arr,0,arr.length-1);
    }


    private class Task {
        public int left;
        public int right;

        public Task(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    /**
     * 快速排序非递归写法
     * @param arr 普通的数组
     */
    public void quickSort02(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        LinkedList<Task> stack = new LinkedList<>();
        int randomIndex = (int) (Math.random() * (arr.length - 1));
        swap(arr,randomIndex,arr.length-1);
        int[] equalsArr = partition(arr, 0, arr.length - 1);
        stack.push(new Task(0,equalsArr[0]-1));
        stack.push(new Task(equalsArr[1] + 1,arr.length-1));


        while (!stack.isEmpty()) {
            Task task = stack.pop();
            if (task.left < task.right) {
                randomIndex = task.left + (int) (Math.random() * (task.right - task.left + 1));
                swap(arr,randomIndex,task.right);
                equalsArr = partition(arr, task.left, task.right);
                stack.push(new Task(task.left,equalsArr[0] - 1));
                stack.push(new Task(equalsArr[1] + 1,task.right));
            }
        }

    }

    /**
     * 快速排序的递归实现
     * 函数定义:
     * 在[left,right]给arr排好序.
     * @param arr 普通的数组
     * @param left 左边界
     * @param right 右边界
     */
    private void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //这两句加上后,性能提升了很多
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(arr,randomIndex,right);



        int[] partition = partition(arr, left, right);
        process(arr,left,partition[0] - 1);
        process(arr,partition[1] + 1,right);

    }


    /**
     * 荷兰国旗问题解法
     * @param arr 普通的数组
     * @param left 左边界
     * @param right 右边界
     * @return 相等的下标
     */
    public int[] partition(int[] arr,int left,int right) {
        int target = arr[right];
        int lessR = left-1;
        int moreL = right;
        int cur = left;
        while (cur < moreL) {
            if (arr[cur] < target) {
                swap(arr, ++lessR, cur++);
            } else if (arr[cur] > target) {
                swap(arr, --moreL, cur);
            } else {
                cur++;
            }
        }
        swap(arr, moreL, right);
        return new int[]{lessR + 1, moreL};
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Nested
    class QuickSortTester {

        @Test
        public void test() {
            int testTimes = 100000;
            int arrMaxLen = 4;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen,arrMaxNum);
                int[] sorted1 = Arrays.copyOf(arr, arr.length);
                int[] sorted2 = Arrays.copyOf(arr, arr.length);
//                System.out.println(Arrays.toString(arr));
                quickSort(sorted1);
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


        @Test
        public void test01() {
            int[] arr = {93, 51};
//            partition(arr,0,arr.length-1);
//            System.out.println(Arrays.toString(arr));
            quickSort02(arr);
            System.out.println(Arrays.toString(arr));

        }


        public void valid(int[] arr) {
            Arrays.sort(arr);
        }

    }

}
