package com.pandaer.class03;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 有一堆数据,其中有且只有两个数出现奇数次,其他的数都是出现偶数次,返回这两个数
 * 额外空间复制度: O(1)
 * 时间复杂度: todo 待定
 * 算法思想:
 * 从数组中选一个最小的排到数组的开头,再在剩下的数组中选一个最小的排在第二的位置,以此类推,就可以有序了
 */
public class Code04_AndXorFind {

    /**
     * 利用异或的那三个特点,加上 a&(-a) 能提取出最右边的1
     * @param arr 有条件限制的数组
     * @return 返回出现奇数次的两个数
     */
    public Set<Integer> andXorFind(int[] arr) {
        int xor = 0;
        for (int num : arr) {
            xor ^= num;
        }

        int binNum = xor & (-xor);

        int xor2 = 0;
        for (int num : arr) {
            if ((num & binNum) != 0) {
                xor2 ^= num;
            }
        }

        return new HashSet<>(Arrays.asList(xor2, xor2 ^ xor));

    }


    @Nested
    class AndXorFindTester {

        private final RandomDataGenerator generator = new RandomDataGenerator();

        @Test
        public void test() {
            int testTimes = 100_0000;
            int maxKinds = 25;
            int maxTimes = 10;
            int arrMaxNum = 100;
            for (int i = 0; i<testTimes;i++) {
                int[] arr = randomArray(maxKinds,maxTimes,arrMaxNum);
                Set<Integer> res1 = andXorFind(arr);
                Set<Integer> res2 = valid(arr);
                if (!res1.equals(res2)) {
                    System.out.println("原数组: " + Arrays.toString(arr));
                    System.out.println("算法: " + res1);
                    System.out.println("验证算法: " + res2);
                    return;
                }
            }
            System.out.println("验证通过");
        }


        public Set<Integer> valid(int[] arr) {
            Map<Integer,Integer> map = new HashMap<>();

            for (int num : arr) {
                if (map.containsKey(num)) {
                    map.put(num,map.get(num) + 1);
                }else {
                    map.put(num,1);
                }
            }

            Set<Integer> set = new HashSet<>();
            for (Integer num : map.keySet()) {
                if (map.get(num) % 2 != 0) {
                    set.add(num);
                }
            }

            return set;
        }


        private int[] randomArray(int maxKinds,int maxTimes, int arrMaxNum) {
            int kind = generator.nextInt(3,maxKinds);
            int times = generator.nextInt(1,maxTimes);
            times = times % 2 == 0 ? times + 1 : times;
            ArrayList<Integer> list = new ArrayList<>();
            int resNum = generator.nextInt(0,arrMaxNum);
            for (int i = 0; i < times; i++) {
                list.add(resNum);
            }
            kind--;

            int resNum2 = generator.nextInt(0,arrMaxNum);
            while (resNum2 == resNum) {
                resNum2 = generator.nextInt(0,arrMaxNum);
            }

            for (int i = 0; i < times; i++) {
                list.add(resNum2);
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
    }
}
