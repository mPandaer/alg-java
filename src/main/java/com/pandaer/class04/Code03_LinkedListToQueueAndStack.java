package com.pandaer.class04;

import com.pandaer.class04.base.ListNode;

/**
 * 利用链表实现栈和队列
 */
public class Code03_LinkedListToQueueAndStack {


    /**
     * 用链表实现队列
     */
    class Queue {
        ListNode head;
        ListNode tail;

        public void push(int val) {
            ListNode node = new ListNode(val);
            if (head == null) {
                head = node;
                tail = node;
                return;
            }
            tail.next = node;
            tail = node;
        }

        public int pop() {
            if (head == null) {
                throw new IllegalStateException("队列为空");
            }
            ListNode deleteNode = head;
            head = head.next;
            return deleteNode.val;
        }

        public int peek() {
            if (head == null) {
                throw new IllegalStateException("队列为空");
            }
            return head.val;
        }

    }


    /**
     * 链表实现栈
     */
    class Stack {
        ListNode top;

        public void push(int val) {
            ListNode node = new ListNode(val);
            if (top == null) {
                top = node;
                return;
            }
            node.next = top;
            top = node;
        }

        public int pop() {
            if (top == null) {
                throw new IllegalStateException("栈为空");
            }
            ListNode deleteNode = top;
            top = top.next;
            return deleteNode.val;
        }

        public int peek() {
            if (top == null) {
                throw new IllegalStateException("栈为空");
            }
            return top.val;
        }
    }




}
