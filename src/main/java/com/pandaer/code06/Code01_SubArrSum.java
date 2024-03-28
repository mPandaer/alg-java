package com.pandaer.code06;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 子数组求和问题
 * 详细描述: 有一个普通的数组,给定一个区间为[lower,upper],如果这个数组中的子数组的和落在这个范围上,则记录一下,问:一共有多少个这样的子数组
 * 要求:
 * 额外空间复杂度: O(N)
 * 时间复杂度: O(N^LogN)
 * 算法思路:
 * 1.子数组必须是连续的
 * 我们可以暴力解,穷举出全部的子数组,然后求和并看看是不是落在指定的区间上,这样时间复杂度会来到O(N^3)
 * 这是由于子数组的规模是数组规模的平方倍,然后求和又是O(N)的操作.换个思路,求和一定需要O(N)的操作吗,如果我之前有一个前缀和数组呢?
 * 那么求和操作就可以变成O(1) 比如 求数组 arr[i,j]的和 = arr[0,j]的和 - arr[0,i-1]的和. 而前缀和数组保留的就是 arr[0,x]的和.
 * 所以求和算法是可以优化到O(1)的,那么算法的时间复杂度就从O(N^3)变为了O(N^2)只和子数组的规模有关了,感觉已经优化到了极限.真的吗?其实不见得吧,
 * 我们现在的优化点就是减少穷举子数组的个数,这样或许有办法降低时间复杂度,如果我一口气可以确定多个子数组,那么就有可能优化时间复杂度.
 * 那么我们根据公式 lower< sum(arr[i,j]) < upper ==>  lower< sum(arr[0,j]) - sum(arr[0,i-1]) < upper ==>
 * sum(arr[0,j]) - upper < sum(arr[0,i-1]) <  sum(arr[0,j]) - lower  然后 i < j, 如果sum一开始就求好了,即前缀和数组,那么你会发现,
 * 其实就是在求j位置之前有多少个数的范围落在了 [sum[j]-upper,sum[j]-lower]上, 只要发现求一个数的前面有多少个符合某个条件的,
 * 然后又需要优化时间复杂度,那么就可以试试归并排序的改法.
 */
public class Code01_SubArrSum {


    /**
     * 子数组求和问题
     *
     * @param arr   普通的数组
     * @param lower 下限
     * @param upper 上限
     * @return 返回符合条件的子数组的个数
     */
    public int subArrSum(int[] arr, int lower, int upper) {
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return process(sum, 0, arr.length - 1, lower, upper);
    }

    /**
     * 子数组求和问题的真实实现
     * 函数定义: 在sum数组中[left,right]中寻找满足条件的数的个数.
     *
     * @param sum   普通的数组的前缀和数组
     * @param left  数组的左边界
     * @param right 数组的右边界
     * @param lower 下限
     * @param upper 上限
     * @return 返回符合条件的子数组的个数
     */
    private int process(long[] sum, int left, int right, int lower, int upper) {
        if (left == right) {
            if (sum[left] >= lower && sum[left] <= upper) {
//                System.out.println(Arrays.toString(sum));
                System.out.println("0~" + left);
                return 1;
            }
            return 0;
        }

        int mid = left + ((right - left) >> 1);
        int resLeft = process(sum, left, mid, lower, upper);
        int resRight = process(sum, mid + 1, right, lower, upper);
        int resMerge = merge(sum, left, mid, right, lower, upper);
        return resLeft + resRight + resMerge;
    }

    /**
     * 在[left,right]整合满足条件的数的个数,并让数组在[left,right]有序
     *
     * @param sum   前缀和数组
     * @param left  左边
     * @param mid   中间
     * @param right 右边
     * @param lower 下限
     * @param upper 上限
     * @return 满足条件的个数
     */
    private int merge(long[] sum, int left, int mid, int right, int lower, int upper) {
        // 满足条件的窗口大小 [L,R)
        int L = left;
        int R = left;
        int p = mid + 1;
        int res = 0;
        while (p <= right) {
            long newLower = sum[p] - upper;
            long newUpper = sum[p] - lower;
            while (L <= mid && sum[L] < newLower) {
                L++;
            }

            while (R <= mid && sum[R] <= newUpper) {
                R++;
            }
            res += (R - L);
            p++;
        }

        //归并排序的正常merge过程
        long[] help = new long[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = sum[p1] < sum[p2] ? sum[p1++] : sum[p2++];
        }

        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }

        while (p2 <= right) {
            help[i++] = sum[p2++];
        }

        for (i = 0; i < help.length; i++) {
            sum[left + i] = help[i];
        }

        return res;
    }

    @Test
    public void test() {
        int[] arr = new int[]{-3,1,2,-2,2,-1};
        int lower = -3;
        int upper = -1;
        System.out.println(subArrSum(arr,lower,upper));
    }

    /**
     * 暂时没有对数器,有线上OJ https://leetcode.cn/problems/count-of-range-sum/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
     */

}
