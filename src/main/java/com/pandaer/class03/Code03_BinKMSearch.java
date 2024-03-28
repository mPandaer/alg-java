package com.pandaer.class03;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 在一堆数据中,有且仅存在一个出现K次的数,其他的数出现M次,其中 k < m,要求返回这个出现K次的数
 */
public class Code03_BinKMSearch {

    /**
     * 利用二进制 计数的方式来解决这个问题
     * @param arr 一个普通的数组
     * @return 返回出现K次的数
     */
    public int binKMSearch(int[] arr,int k,int m) {
        int[] bin = new int[32];
        for (int num : arr) {
            for (int i = 0; i<32;i++) {
                int andNum = 1 << i;
                if ((num & andNum) != 0) {
                    bin[i]++;
                }
            }
        }

        int res = 0;
        for (int i = 0; i<bin.length;i++) {
            if (bin[i] % m == k) {
                res |= (1 << i);
            }
        }

        return res;
    }

    @Nested
    class BinKMSearchTester {
        private final RandomDataGenerator generator = new RandomDataGenerator();
        private int k;
        private int m;

        @Test
        public void test() {
            int maxTestTimes = 100_000;
            int maxKinds = 100;
            int maxNum = 100;
            int maxTimes = 100;

            for (int i = 0; i < maxTestTimes; i++) {
                int[] arr = randomArray(maxKinds,maxTimes,maxNum);
                int res1 = binKMSearch(arr, k, m);
                int res2 = valid(arr, k, m);

                if (res1 != res2) {
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("算法: " + res1);
                    System.out.println("验证算法: " + res2);
                    return;
                }
            }

            System.out.println("验证通过");


        }

        public int valid(int[] arr,int k,int m) {
            Map<Integer,Integer> map = new HashMap<>();
            for (int num : arr) {
                if (map.containsKey(num)) {
                    map.put(num,map.get(num) + 1);
                }else {
                    map.put(num,1);
                }
            }

            for (Integer num : map.keySet()) {
                if (map.get(num) == k) {
                    return num;
                }
            }

            throw new IllegalStateException("数组不符合条件 " + Arrays.toString(arr));
        }

        private int[] randomArray(int maxKinds,int maxTimes,int maxNum) {
            int kind = generator.nextInt(2,maxKinds);
            int kNum = generator.nextInt(0,maxNum);
            List<Integer> list = new ArrayList<>();
            int kTimes = generator.nextInt(1,maxTimes-1);
            k = kTimes; //初始化k
            for (int i = 0; i < kTimes; i++) {
                list.add(kNum);
            }
            kind--;
            int mTimes = generator.nextInt(kTimes + 1,maxTimes);
            m = mTimes; //初始化m
            for (int i = 0; i < kind; i++) {
                int num = generator.nextInt(0,maxNum);
                while (num == kNum) {
                    num = generator.nextInt(0,maxNum);
                }
                for (int i1 = 0; i1 < mTimes; i1++) {
                    list.add(num);
                }
            }

            return list.stream().mapToInt(num -> num).toArray();

        }
    }
}
