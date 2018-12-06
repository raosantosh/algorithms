package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 6/30/18. Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list
 * "parts".
 *
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to some parts being null.
 *
 * The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal parts occurring
 * later.
 *
 * Return a List of ListNode's representing the linked list parts that are formed.
 *
 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ] Example 1: Input: root = [1, 2, 3], k = 5 Output: [[1],[2],[3],[],[]] Explanation:
 * The input and each element of the output are ListNodes, not arrays. For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3,
 * and root.next.next.next = null. The first element output[0] has output[0].val = 1, output[0].next = null. The last element output[4] is null, but it's string
 * representation as a ListNode is [].
 */
public class SplitListPairs {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode[] splitListToPartsTemp(ListNode root, int k) {

    ListNode[] headList = new ListNode[k];
    ListNode[] currentList = new ListNode[k];


    for(int i = 0 ; i < k ; ++i) {
      headList[i] = new ListNode(0);
      currentList[i] = headList[i];
    }

    int nodeIndex = 0 ;

    while( root != null) {
      int headIndex = nodeIndex % k;
      headList[headIndex].next = root;
      headList[headIndex] = headList[headIndex].next ;
      root = root.next;
      nodeIndex++;
    }

    for(int i = 0 ; i < k ; ++i) {
      headList[i].next = null ;
    }


    ListNode[] result = new ListNode[k];
    for(int i = 0 ; i < k ; ++i) {
      result[i] = currentList[i].next;
    }

    return result;

  }

  public ListNode[] splitListToParts(ListNode root, int k) {
    ListNode[] headList = new ListNode[k];
    ListNode[] currentList = new ListNode[k];

    for(int i = 0 ; i < k ; ++i) {
      headList[i] = new ListNode(0);
      currentList[i] = headList[i];
    }

    int totalSize = 0;
    ListNode current = root;
    while(current != null) {
      current = current.next;
      ++totalSize;
    }

    int minElements = totalSize / k;
    int leftOver = totalSize % k;

    int currentSize = 0;

    current = root;
    int currentIndex = 0;
    while(current != null) {
      for(int i=0; i < minElements; ++i) {
        headList[currentIndex].next = current;
        headList[currentIndex] = headList[currentIndex].next;
        current = current.next;
      }
      if(leftOver > 0) {
        headList[currentIndex].next = current;
        headList[currentIndex] = headList[currentIndex].next;
        current = current.next;
        leftOver--;
      }
      ++currentIndex;
    }

    for(int i = 0 ; i < k ; ++i) {
      headList[i].next = null ;
    }

    ListNode[] result = new ListNode[k];
    for(int i = 0 ; i < k ; ++i) {
      result[i] = currentList[i].next;
    }

    return result;
  }

  public static void main(String args[]) {
    SplitListPairs splitter = new SplitListPairs();

    ListNode node = new ListNode(1);
    node.next = new ListNode(2);
    node.next.next = new ListNode(3);
    node.next.next.next = new ListNode(4);
    node.next.next.next.next = new ListNode(5);
    node.next.next.next.next.next = new ListNode(6);
    node.next.next.next.next.next.next = new ListNode(7);
    ListNode[] result = splitter.splitListToParts(node, 2);

    for (int i = 0; i < result.length; ++i) {
      System.out.println(" round " + i);
      splitter.printList(result[i]);
    }
  }

  public void printList(ListNode node) {
    while (node != null) {
      System.out.println(" -> " + node.val);
      node = node.next;
    }
  }

}
