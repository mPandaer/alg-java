package com.pandaer.class07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据结构：堆
 * 堆的本质是一个完全二叉树，什么是完全二叉树，理解完全二叉树，得先明白什么是满二叉树，满二叉树其实就是除了叶子节点，其余节点都有左右孩子。
 * 而完全二叉树就是在向满二叉树进化过程中的一个中间样子。而堆就是完全二叉树 + 一些限制条件
 * 大根堆： 任何一颗子树的最大值是根节点
 * 小根堆： 任何一颗子树的最小值是根节点
 * 堆有两种操作：
 * 1. heapInsert操作，一个元素加入堆的时候，将元素加入堆中，并进行堆化
 * 2. heapify操作，由于某些操作导致原有的值向着堆的反方向变化，需要进行heapify操作进行重新堆化
 */
public class Code02_Heap {

    /**
     * 大根堆为例
     */
    static class MaxHeap {
        List<Integer> list = new ArrayList<>();
        int heapSize = 0;

        public void push(Integer value) {
            if (heapSize == 0) {
                list.add(value);
                heapSize++;
                return;
            }
            list.add(heapSize,value);
            heapSize++;
            heapInsert(heapSize - 1);
        }

        public Integer pop() {
            if (heapSize == 0) {
                throw new IllegalStateException("堆中没有数据");
            }
            Integer res = list.get(0);
            swap(list, 0, --heapSize);
            heapify(0);
            return res;
        }

        private void swap(List<Integer> list, int i, int j) {
            Integer tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }


        public Integer peek() {
            return list.get(0);
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        /**
         * 往上 到达他该在的位置
         */
        public void heapInsert(int index) {
            while (list.get(index) > list.get((index - 1) / 2)) {
                swap(list, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 往下 到达他该有的位置
         */
        public void heapify(int index) {
            int leftIndex = index * 2 + 1;
            while (leftIndex < heapSize) {
                int maxChildIndex = leftIndex + 1 < heapSize && list.get(leftIndex + 1) > list.get(leftIndex)
                        ? leftIndex + 1 : leftIndex ;
                if (list.get(maxChildIndex) < list.get(index)) {
                    break;
                }
                swap(list, index, maxChildIndex);
                index = maxChildIndex;
                leftIndex = index * 2 + 1;
            }
        }

        /**
         * 友好的显示这个堆
         */
        public void display() {
           int startIndex = 0;
           int num = 1;
           while (startIndex < heapSize) {
               int size = Math.min(num,list.size() - startIndex);
               for (int i = 0; i< size; i++) {
                   System.out.print(list.get(startIndex + i) + " ");
               }
               startIndex = startIndex * 2 + 1;
               num *= 2;
               System.out.println();
           }
        }


    }


    @Test
    public void test01() {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.push(1);
        maxHeap.push(2);
        maxHeap.push(3);
        maxHeap.push(4);
        maxHeap.display();
        System.out.println("====================");
        maxHeap.pop();
        maxHeap.display();
        System.out.println("=====================");
        System.out.println(maxHeap.peek());
    }

}
