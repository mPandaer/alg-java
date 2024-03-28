package com.pandaer.class03;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用异或寻找特定规律的数据
 * 详细描述: 有一堆数据,其中只有一个数字出现了奇数次,其他数字都出现了偶数次,找出这个数字
 * 利用异或的特点 N ^ N = 0   N ^ 0 = N 而且异或运算满足交换和结合率
 */
public class Code02_XorSearch {

    /**
     * 从数组中找到出现奇数次的数字
     * @param arr 有规律的数字
     * @return 那个出现奇数次的数字
     */
    public int xorSearch(int[] arr) {
        int xor = 0;
        for (int i : arr) {
            xor ^= i;
        }
        return xor;
    }

    @Nested
    class XorSearchTester {

        private final RandomDataGenerator generator = new RandomDataGenerator();

        @Test
        public void test() {
            int testTimes = 100_000;
            int maxKinds = 25;
            int maxTimes = 10;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = randomArray(maxKinds,maxTimes,arrMaxNum);
                int res1 = xorSearch(arr);
                int res2 = valid(arr);
                if (res1 != res2) {
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("算法: " + res1);
                    System.out.println("验证算法: " + res2);
                    return;
                }
            }
            System.out.println("验证通过");

        }

        private int[] randomArray(int maxKinds,int maxTimes, int arrMaxNum) {
            int kind = generator.nextInt(2,maxKinds);
            int times = generator.nextInt(1,maxTimes);
            times = times % 2 == 0 ? times + 1 : times;
            ArrayList<Integer> list = new ArrayList<>();
            int resNum = generator.nextInt(0,arrMaxNum);
            for (int i = 0; i < times; i++) {
                list.add(resNum);
            }
            kind--;
            for (int i = 0; i < kind; i++) {
                times = generator.nextInt(1,maxTimes);
                times = times % 2 == 0 ? times : times + 1;
                int num = generator.nextInt(0,arrMaxNum);
                for (int j = 0; j < times; j++) {
                    list.add(num);
                }
            }

            return list.stream().mapToInt(num -> num).toArray();
        }

        public int valid(int[] arr) {
            Map<Integer,Integer> map = new HashMap<>();
            for (int num : arr) {
                if (map.containsKey(num)) {
                    map.put(num,map.get(num) + 1);
                }else {
                    map.put(num,1);
                }
            }

            for (Integer num : map.keySet()) {
                if (map.get(num) % 2 != 0) {
                    return num;
                }
            }

            throw new IllegalStateException("不可能来到这里");
        }
    }
}
