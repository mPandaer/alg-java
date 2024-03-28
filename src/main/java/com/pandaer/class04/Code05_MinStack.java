package com.pandaer.class04;

import java.util.LinkedList;

/**
 * 最小栈问题
 * 获取到最小值的时间复杂度为 O(1)
 */
public class Code05_MinStack {


    class MinStack {
        private LinkedList<Integer> dataStack;
        private LinkedList<Integer> minStack;


        public MinStack() {
            this.dataStack = new LinkedList<>();
            this.minStack = new LinkedList<>();
        }

        public void push(int val) {
            if (dataStack.isEmpty()) {
                dataStack.addLast(val);
                minStack.addLast(val);
                return;
            }
            dataStack.addLast(val);
            Integer last = minStack.getLast();
            if (val < last) {
                minStack.addLast(val);
            }else {
                minStack.addLast(last);
            }
        }

        public void pop() {
            dataStack.removeLast();
            minStack.removeLast();
        }

        public int top() {
            return dataStack.getLast();
        }

        public int getMin() {
            return minStack.getLast();
        }

    }

    /**
     * 暂时没有对数器 线上OJ https://leetcode.cn/problems/min-stack/description/
     */

}
