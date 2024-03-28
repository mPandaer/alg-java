package com.pandaer.class04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列实现栈
 */
public class Code07_QueueToStack {

    /**
     * 队列实现栈
     */
    class MockStack {
        Queue<Integer> dataStack = new LinkedList<>();
        Queue<Integer> helpStack = new LinkedList<>();

        public void push(int x) {
            dataStack.offer(x);
        }

        public int pop() {
            while (dataStack.size() > 1) {
                helpStack.offer(dataStack.poll());
            }
            int res = dataStack.poll();
            Queue<Integer> tmp = dataStack;
            dataStack = helpStack;
            helpStack = tmp;
            return res;

        }

        public int top() {
            while (dataStack.size() > 1) {
                helpStack.offer(dataStack.poll());
            }
            int res = dataStack.poll();
            Queue<Integer> tmp = dataStack;
            dataStack = helpStack;
            helpStack = tmp;
            dataStack.offer(res);
            return res;
        }

        public boolean empty() {
            return dataStack.isEmpty() && helpStack.isEmpty();
        }
    }
}
