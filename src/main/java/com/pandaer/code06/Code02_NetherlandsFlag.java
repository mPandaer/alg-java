package com.pandaer.code06;

import com.pandaer.class02.utils.ArrUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 荷兰国旗问题
 * 具体描述:
 * 在数组中,以数组最后一个值为目标值target,小于等于target的数移动到数组的左半部分,大于target的数移动到右半部分
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N)
 * 算法思想:
 * 划分一个左区域,小于等于target的值和这个左区域的后一个交换,遇到大于的就直接跳过,直到走到数组的最后.
 */
public class Code02_NetherlandsFlag {


    /**
     * 荷兰国旗问题的解法
     *
     * @param arr 普通的数组
     */
    public void netherlandsFlag01(int[] arr) {
        int size = arr.length;
        int target = arr[size - 1];
        int lessR = -1;
        int cur = 0;
        while (cur < size - 1) {
            if (arr[cur] <= target) {
                swap(arr, ++lessR, cur);
            }
            cur++;
        }
        swap(arr, lessR + 1, size - 1);
    }


    /**
     * 荷兰国旗优化问题
     * 小于target值的放左边 等于target值的放中间 大于target值的放右边
     *
     * @param arr 普通的数组
     */
    public int[] netherlandsFlag02(int[] arr) {
        int size = arr.length;
        int target = arr[size - 1];
        int lessR = -1;
        int moreL = size - 1;
        int cur = 0;
        while (cur < moreL) {
            if (arr[cur] < target) {
                swap(arr, ++lessR, cur++);
            } else if (arr[cur] > target) {
                swap(arr, --moreL, cur);
            } else {
                cur++;
            }
        }
        swap(arr, moreL, size - 1);
        return new int[]{lessR + 1, moreL};
    }


    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Nested
    class NetherlandsFlagTester {

        @Test
        public void test() {
            int testTimes = 1000000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i < testTimes; i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen, arrMaxNum);
                int[] arr1 = Arrays.copyOf(arr, arr.length);
//                int target = arr1[arr1.length - 1];
//                netherlandsFlag01(arr1);
                int[] res = netherlandsFlag02(arr1);
                if (!valid02(arr1, res)) {
                    System.out.println("测试失败 " + i);
                    System.out.println("原数组: " + Arrays.toString(arr));
//                    System.out.println("现在的数组: " + Arrays.toString(arr1) + " target: " + target);
                    System.out.println("现在的数组: " + Arrays.toString(arr1) + " res: " + Arrays.toString(res));
                    return;
                }
            }
            System.out.println("验证通过");
        }


        @Test
        public void test01() {
            int[] arr = {43, -10, -2, -4, 31, 35, -2};
            System.out.println(Arrays.toString(arr));
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] res = netherlandsFlag02(arr1);
            System.out.println(Arrays.toString(arr1));
            System.out.println(Arrays.toString(res));
            boolean result = valid02(arr1, res);
            System.out.println(result);

        }

        public boolean valid(int[] arr, int target) {
            List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
            int index = list.lastIndexOf(target);
            if (index == -1) {
                return false;
            }
            for (int i = 0; i < index; i++) {
                if (arr[i] > arr[index]) {
                    return false;
                }
            }
            for (int i = index + 1; i < arr.length; i++) {
                if (arr[i] <= arr[index]) {
                    return false;
                }
            }
            return true;
        }

        public boolean valid02(int[] arr, int[] res) {

            for (int i = 0; i < res[0]; i++) {
                if (arr[i] > arr[res[0]]) {
                    return false;
                }
            }
            for (int i = res[1] + 1; i < arr.length; i++) {
                if (arr[i] < arr[res[1]]) {
                    return false;
                }
            }
            for (int i = res[0]; i<=res[1]; i++) {
                if (arr[i] != arr[res[0]]) {
                    return false;
                }
            }
            return true;
        }


        private int[] randomArray(int arrMaxLen, int arrMaxNum) {
            int len = ((int) (Math.random() * arrMaxLen)) + 1;
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                int num = ((int) (Math.random() * arrMaxNum)) - ((int) (Math.random() * arrMaxNum));
                arr[i] = num;
            }
            return arr;
        }
    }
}
