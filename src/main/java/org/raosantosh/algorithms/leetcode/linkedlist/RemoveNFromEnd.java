package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 6/30/18.
 */
public class RemoveNFromEnd {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {

    ListNode fakeHead = new ListNode(0);
    fakeHead.next = head;

    ListNode current = head;

    int totalSIze = 0;
    while(current != null) {
      current = current.next;
      totalSIze++;
    }

    int removeIndex = totalSIze - n;

    ListNode prev = fakeHead;
    current = prev.next;

    while(removeIndex -- > 0) {
       prev = current;
       current = current.next;
    }

    prev.next = current.next;

    return fakeHead.next;
  }

  public static void main(String args[]) {

    RemoveNFromEnd remover = new RemoveNFromEnd();

    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);
    node.next.next.next.next.next = new ListNode(6);
    node.next.next.next.next.next.next = new ListNode(7);
    ListNode result = remover.removeNthFromEnd(node, 6);

    remover.printList(result);
  }

  public void printList(ListNode node) {
    while (node != null) {
      System.out.println(" -> " + node.val);
      node = node.next;
    }
  }

}
