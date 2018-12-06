package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 4/11/18.
 */
public class ReverseLinkedList2 {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode reverseBetween(ListNode head, int m, int n) {

    ListNode startHead = head;
    ListNode first = startHead;
    for (int i = 1; i < m - 1; ++i) {
      startHead = startHead.next;
    }

    int toReverse = n - m;

    if(m > 1)
    first = startHead.next;

    ListNode current = first;
    ListNode next = current.next;
    ListNode nextNext = null;

    while (toReverse-- > 0) {
      nextNext = next.next;
      next.next = current;
      current = next;
      next = nextNext;
    }

    if(current != null)
    startHead.next = current;

    if(first !=null)
    first.next = next;

    if(startHead == first ) return current;

    return head;
  }

  public ListNode reverseBetweenBF(ListNode head, int m, int n) {
    ListNode prevMNode = null;
    ListNode prevnNode = null;

    if (m == n) {
      return head;
    }

    ListNode current = head;

    int currentIndex = 0;

    while (current.next != null) {
      currentIndex++;

      if (currentIndex == m - 1) {
        prevMNode = current;
      }

      if (currentIndex == n - 1) {
        prevnNode = current;
      }

      if (prevMNode != null && prevnNode != null) {
        break;
      }
      current = current.next;
    }

    if (prevMNode != null && prevnNode != null) {
      ListNode currentNNode = prevnNode.next;
      ListNode currentMNode = prevMNode.next;
      ListNode currentMNext = currentMNode.next;
      ListNode currentNNext = currentNNode.next;

      prevMNode.next = currentNNode;
      prevnNode.next = currentMNode;
      currentNNode.next = currentMNext;
      currentMNode.next = currentNNext;
    } else if (n == 1) {
      ListNode currentNNode = head;
      ListNode currentMNode = prevMNode.next;
      ListNode currentMNext = currentMNode.next;
      ListNode currentNNext = currentNNode.next;

      prevnNode.next = currentMNode;
      currentNNode.next = currentMNext;
      currentMNode.next = currentNNext;
      head = currentMNode;
    } else if (m == 1) {
      ListNode currentNNode = prevnNode.next;
      ListNode currentMNode = head;
      ListNode currentMNext = currentMNode.next;
      ListNode currentNNext = currentNNode.next;

      prevnNode.next = currentMNode;
      currentNNode.next = currentMNext;
      currentMNode.next = currentNNext;
      head = currentNNode;
    }

    return head;
  }

  public static void main(String args[]) {

    ReverseLinkedList2 reverser = new ReverseLinkedList2();

    ListNode node_1 = new ListNode(1);
    ListNode node_2 = new ListNode(2);
    ListNode node_3 = new ListNode(3);
    ListNode node_4 = new ListNode(4);
    ListNode node_5 = new ListNode(5);
    ListNode node_6 = new ListNode(6);
    ListNode node_7 = new ListNode(7);
    node_1.next = node_2;
    node_2.next = node_3;
    node_3.next = node_4;
    node_4.next = node_5;
    node_5.next = node_6;
    node_6.next = node_7;

    print(node_1);
    System.out.println("after");
    print(reverser.reverseBetween(node_1, 2,4));

    ListNode node3 = new ListNode(3);
    ListNode node5 = new ListNode(5);
    node3.next = node5;

    System.out.println("second");
    print(node3);
    System.out.println("after");
    print(reverser.reverseBetween(node3, 1,2));
  }

  private static void print(ListNode node) {
    while (node != null) {
      System.out.println(node.val);
      node = node.next;
    }
  }
}
