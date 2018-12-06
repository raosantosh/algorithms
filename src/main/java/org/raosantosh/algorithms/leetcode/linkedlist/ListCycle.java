package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 6/26/18.
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * Note: Do not modify the linked list.
 *
 * Follow up:
 * Can you solve it without using extra space?
 */
public class ListCycle {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }


  public ListNode detectCycle(ListNode head) {
    if(head == null) return null;

    ListNode slow = head;
    ListNode fast = slow;
    boolean starting = true;

    while(starting || (slow != fast && fast != null)) {
      slow = slow.next;
      fast = fast.next;
      if(fast != null)
        fast = fast.next;
      starting = false;
    }

    if(fast == null) return null;

    slow = head;
    while(slow != fast) {
      slow = slow.next;
      fast = fast.next;

    }

    return slow;
  }

  public static void main(String args[]) {

    ListCycle cycle = new ListCycle();

    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);
    node.next.next.next.next.next = new ListNode(6);
    node.next.next.next.next.next.next = node;

    ListNode result = cycle.detectCycle(node);

    System.out.println(result == null ? "NULL" : result.val);
  }

  public void printList(EvenOddLinkedList.ListNode node) {
    while (node != null) {
      System.out.println(" -> " + node.val);
      node = node.next;
    }
  }

}
