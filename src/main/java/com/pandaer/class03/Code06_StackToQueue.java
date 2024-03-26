package com.pandaer.class03;

import java.util.LinkedList;

/**
 * 利用栈实现队列
 * 两个栈实现队列
 * 准备两个栈,一个push栈,一个pop栈, 准备一个函数,只要pop栈为空,就可以将push栈的数据全部倒进来
 * 倒数据的时候满足的两个条件
 * 1. pop栈只能为空
 * 2. 倒数据的时候,必须全部倒完
 */
public class Code06_StackToQueue {

    /**
     * 利用栈实现队列
     */
    class MockQueue {
        private final LinkedList<Integer> pushStack = new LinkedList<>();
        private final LinkedList<Integer> popStack = new LinkedList<>();

        public void push(Integer val) {
            pushStack.push(val);
        }

        public int pop() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new IllegalStateException("队列为空");
            }
            pushToPop();
            return popStack.pop();
        }

        public int peek() {
            if (pushStack.isEmpty() && popStack.isEmpty()) {
                throw new IllegalStateException("队列为空");
            }
            pushToPop();
            return popStack.peek();
        }

        public void pushToPop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }
    }
}
