package com.pandaer.class03;

/**
 * 利用数组实现队列和栈
 */
public class Code04_ArrayToQueueAndStack {


    class Queue {
        private int head = 0;
        private int tail = 0;
        private int size;
        private final int limit;
        private final int[] arr;
        public Queue(int size) {
            this.limit = size;
            this.arr = new int[size];
        }

        public void push(int val) {
            size++;
            if (size == limit) {
                throw new IllegalStateException("队列满了");
            }
            arr[tail] = val;
            tail = (tail + 1) % limit;
        }

        public int pop() {
            if (size == 0) {
                throw new IllegalStateException("队列为空");
            }
            size--;
            int res = arr[head];
            head = (head + 1) % limit;
            return res;
        }

        public int peek() {
            if (size == 0) {
                throw new IllegalStateException("队列为空");
            }
            return arr[head];
        }
    }


    class Stack {
        private int top = 0;
        private final int limit;
        private final int[] arr;
        public Stack(int limit) {
            this.limit = limit;
            this.arr = new int[limit];
        }

        public void push(int val) {
           if (top >= limit) {
               throw new IllegalStateException("栈满了");
           }
           arr[top++] = val;
        }

        public int pop() {
            if (top == 0) {
                throw new IllegalStateException("栈为空的");
            }
            return arr[--top];
        }

        public int peek() {
            if (top == 0) {
                throw new IllegalStateException("栈为空的");
            }
            return arr[top];
        }
    }

}
