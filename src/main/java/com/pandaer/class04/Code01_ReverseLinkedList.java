package com.pandaer.class04;

import com.pandaer.class04.base.ListNode;

/**
 * 反转链表
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N)
 * 算法思想:
 * 反转链表,就是一个不断换头的过程,开始头是null,然后头不断地增加,
 */
public class Code01_ReverseLinkedList {

    /**
     * 反转链表
     * @param head 链表的头
     * @return 链表的新头
     */
    public ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    /**
     * 暂时没有对数器 线上OJ https://leetcode.cn/problems/UHnkqh/
     */

}
