package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 6/26/18.
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 *
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 */
public class SwapInPairs {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode swapPairs(ListNode head) {

    if(head == null || head.next == null) return head;

    ListNode fakeHead = new ListNode(0);
    ListNode result = fakeHead;

    ListNode start = head;
    ListNode next = start.next;

    if (next != null) {
      fakeHead.next = next;
    } else {
      fakeHead.next = start;
    }

    ListNode prev = fakeHead;

    while (start != null && next != null) {
      ListNode nextNext = next.next;
      next.next = start;
      start.next = nextNext;

      prev.next = next;
      prev = start;

      start = nextNext;
      if(start != null)
      next = start.next;
    }

    return result.next;
  }

  public static void main(String args[]) {

    SwapInPairs list = new SwapInPairs();

    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);
    node.next.next.next.next.next = new ListNode(6);
    node.next.next.next.next.next.next = new ListNode(7);
    node.next.next.next.next.next.next.next = new ListNode(8);
    list.printList(node);

    ListNode result = list.swapPairs(node);
    System.out.println("RESULT");
    list.printList(result);
  }

  public void printList(ListNode node) {
    while (node != null) {
      System.out.println(" -> " + node.val);
      node = node.next;
    }
  }

}
