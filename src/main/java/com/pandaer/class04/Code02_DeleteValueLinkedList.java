package com.pandaer.class04;

import com.pandaer.class04.base.ListNode;

/**
 * 在链表中删除给定的值
 * 额外空间复制度: O(1)
 * 时间复杂度: O(N)
 * 算法思想:
 * 利用双指针,扫一遍,遇到要删除的值,直接跳过
 */
public class Code02_DeleteValueLinkedList {

    /**
     * 删除链表中给定的值
     * @param head 链表的头
     * @return 返回链表的新头
     */

    // [4,5,1,9] 5
    public ListNode deleteValueLinkedList(ListNode head,int target) {

        while (head != null && head.val == target) {
            head = head.next;
        }

        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head.next;
        ListNode pre = head;

        while (cur != null) {
            ListNode next = cur.next;
            if (cur.val == target) {
                pre.next = next;
                cur = next;
            }
            pre = cur;
            cur = next;
        }
        return head;
    }

    /**
     * 暂时没有对数器 线上OJ https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/submissions/514984319/
     */
}
