package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 6/26/18.
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

 Example:

 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4

 */
public class MergeSortedLists {

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

    ListNode fakeHead = new ListNode(0);
    ListNode head =fakeHead;


    ListNode l1Current = l1;
    ListNode l2Current = l2;

    while(l1Current !=null && l2Current != null) {
      if(l1Current.val < l2Current.val) {
        head.next = l1Current;
        l1Current = l1Current.next;
      }else {
        head.next = l2Current;
        l2Current = l2Current.next;
      }
      head = head.next;
    }

    while(l1Current != null) {
      head.next = l1Current;
      l1Current = l1Current.next;
      head = head.next;
    }

    while(l2Current != null) {
      head.next = l2Current;
      l2Current = l2Current.next;
      head = head.next;
    }
    head.next = null;


    return fakeHead.next;
  }

  public static void main(String args[]) {

    MergeSortedLists merger = new MergeSortedLists();


    ListNode node = new ListNode(1);
   node.next = new ListNode(2);

    ListNode node1 = new ListNode(3);
   node1.next = new ListNode(4);
    merger.printList(node);

    System.out.println("result");
    ListNode result = merger.mergeTwoLists(node, node1);
    merger.printList(result);
  }

  public void printList(ListNode node) {
    while(node != null) {
      System.out.println(" -> " + node.val);
      node = node.next;
    }
  }
  }
