package com.pandaer.class03;

import com.pandaer.class03.utils.ArrUtils;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 利用异或完成交换动作
 * 异或的特点:
 * 1. N ^ N = 0
 * 2. N ^ 0 = N
 * 3. 满足交换和结合率
 * 4. 将异或理解无进位加法
 * 二进制与十进制是一个数据的两面,就类似一个拼图的两面 拼好一面,另一面就拼好了
 */
public class Code01_XorSwap {

    /**
     * 指定索引位置的数据交换
     * 缺点: 不能是同一片内存区域 会被刷成0
     * @param arr 一个普通的数组
     * @param i 索引
     * @param j 索引
     */
    public void xorSwap(int[] arr,int i,int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    @Nested
    class XorSwapTester {

        private RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

        @Test
        public void test() throws InterruptedException {
            int testTimes = 100_000;
            int arrMaxLen = 100;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = ArrUtils.genRandomArray(arrMaxLen,arrMaxNum);
                int[] arr1 = Arrays.copyOf(arr,arr.length);
                int[] arr2 = Arrays.copyOf(arr,arr.length);
                int size = arr.length;

                int oneIndex = randomDataGenerator.nextInt(0, size - 1);
                int otherIndex = randomDataGenerator.nextInt(0, size - 1);
                System.out.println("开始..... " + i);
                while (oneIndex == otherIndex) {
                    if (randomDataGenerator.nextInt(0, 99) < 50) {
                        oneIndex = randomDataGenerator.nextInt(0, size / 2);
                    } else {
                        otherIndex = randomDataGenerator.nextInt(size / 2, size - 1);
                    }
                }
                System.out.println("结束..... " + i);
                xorSwap(arr1,oneIndex,otherIndex);
                swap(arr2,oneIndex,otherIndex);
                if (!Arrays.equals(arr1,arr2)) {
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("算法: " + Arrays.toString(arr1));
                    System.out.println("验证算法: " + Arrays.toString(arr2));
                    return;
                }
            }
            System.out.println("验证通过");
        }

        public void swap(int[] arr,int i,int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }


        @Test
        public void test2() {
            int maxTestTimes = 10000;
            int maxLen = 100;
            for (int i = 0; i < maxTestTimes; i++) {
                int size = randomDataGenerator.nextInt(1,maxLen);
                int oneIndex = randomDataGenerator.nextInt(0, size);
                int otherIndex = randomDataGenerator.nextInt(0, size);
                System.out.println("开始..... " + i);
                while (oneIndex == otherIndex) {
                    if (randomDataGenerator.nextInt(0, 99) < 50) {
                        oneIndex = randomDataGenerator.nextInt(0, size / 2);
                    } else {
                        otherIndex = randomDataGenerator.nextInt(size / 2, size - 1);
                    }
                }
                System.out.println("结束..... " + i);
            }

        }
    }

}
