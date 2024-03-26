package com.pandaer.class01;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 经典二分查找问题
 * 额外空间复制度: O(1)
 * 时间复杂度: O(LogN)
 * 算法思想:
 * 在一个有序的数组,利用排他性,每次排除一半的数据
 */
public class Code04_BinExist {

    /**
     * 利用二分法 判断数组中是否存在target
     * @param arr 查找数组
     * @param target
     * @return -1表示不存在 否则返回该数在数组中的索引
     */
    public int binExist(int[] arr,int target) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + ((right-left) >> 1);
            if (arr[mid] == target) {
                return mid;
            }else if(arr[mid] < target) {
                left = mid + 1;
            }else {
                right = mid -1;
            }
        }
        return -1;
    }

    @Nested
    class BinExistTester {

        @Test
        public void test() {
            int testTimes = 1000000;
            int arrMaxLen = 5;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int target = (int)(Math.random() * arrMaxNum) - (int)(Math.random() * arrMaxNum);
                int[] arr = randomArray(arrMaxLen,arrMaxNum,target);

                int res1 = binExist(arr, target);
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
            List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
            return list.indexOf(target);
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
