package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 6/26/18.
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class SortList {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode sortList(ListNode head) {

    int listSize = 0;

    ListNode current = head;

    while (current != null) {
      listSize++;
      current = current.next;
    }

    if (listSize <= 1) {
      return head;
    }

    int halfSize = listSize / 2;

    ListNode firstHalf = head;

    ListNode secondHalf = head;
    while (halfSize-- > 1) {
      secondHalf = secondHalf.next;
    }

    ListNode prev = secondHalf;
    secondHalf = secondHalf.next;
    prev.next = null;

    ListNode sortedFirstHalf = sortList(firstHalf);
    ListNode sortedSecondHalf = sortList(secondHalf);

    ListNode merged = merge(sortedFirstHalf, sortedSecondHalf);

    return merged;
  }

  public ListNode merge(ListNode first, ListNode second) {

    ListNode fakeHead = new ListNode(0);

    ListNode mergedPointer = fakeHead;
    ListNode currentFirst= first;
    ListNode currentSecond = second;

    while(currentFirst != null && currentSecond != null) {
      if(currentFirst.val > currentSecond.val) {
        fakeHead.next = currentSecond;
        currentSecond = currentSecond.next;
      }else {
        fakeHead.next = currentFirst;
        currentFirst = currentFirst.next;
      }
      fakeHead = fakeHead.next;
    }

    while (currentFirst != null) {
      fakeHead.next = currentFirst;
      currentFirst = currentFirst.next;
      fakeHead = fakeHead.next;
    }

    while (currentSecond != null) {
      fakeHead.next = currentSecond;
      currentSecond = currentSecond.next;
      fakeHead = fakeHead.next;
    }

    return mergedPointer.next;
  }

  public static void main(String args[]) {

    SortList list = new SortList();

    ListNode node = new ListNode(1);
   node.next = new ListNode(2);
   node.next.next = new ListNode(3);
   node.next.next.next = new ListNode(8);
   node.next.next.next.next = new ListNode(5);
   node.next.next.next.next.next = new ListNode(6);
   node.next.next.next.next.next.next = new ListNode(7);

    list.printList(node);

    System.out.println("RESULT");

    ListNode result = list.sortList(node);
    list.printList(result);
  }

  public void printList(ListNode node) {
    while(node != null) {
      System.out.println(" -> " + node.val);
      node = node.next;
    }
  }
}
