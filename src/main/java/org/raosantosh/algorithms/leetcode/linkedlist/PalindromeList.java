package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 6/26/18.
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Example 1:
 *
 * Input: 1->2
 * Output: false
 * Example 2:
 *
 * Input: 1->2->2->1
 * Output: true
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeList {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public boolean isPalindrome(ListNode head) {

    if(head == null) return true;

    int totalItems = 0;

    ListNode current = head;

    while(current != null) {
      ++totalItems;
      current = current.next;
    }

    int toTestPointer = totalItems - ( totalItems/2) ;

    current = head;

    while(toTestPointer -- > 1){
      current = current.next;
    }

    ListNode toCheckNode = current.next;
    current.next = null;

    ListNode reversed = reverseList(toCheckNode);

    current = head;
    while(reversed != null) {
      System.out.println("cur="+current.val + " rev="+reversed.val);
      if(current.val != reversed.val)
        return false;
      current = current.next;
      reversed = reversed.next;
    }

    return true;
  }

  private ListNode reverseList(ListNode node) {

    if(node == null) return null;

    ListNode prev = node;
    ListNode current = node.next;

    while(current != null) {
      ListNode next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    node.next = null;

    return prev;
  }

  public static void main(String args[]) {

    PalindromeList list = new PalindromeList();

    ListNode node = new ListNode(1);
   /*node.next = new ListNode(2);
   node.next.next = new ListNode(3);
   node.next.next.next = new ListNode(4);
   node.next.next.next.next = new ListNode(3);
   node.next.next.next.next.next = new ListNode(2);
   node.next.next.next.next.next.next = new ListNode(1);*/

    list.printList(node);

    System.out.println(list.isPalindrome(node));
  }

  public void printList(ListNode node) {
    while(node != null) {
      System.out.println(" -> " + node.val);
      node = node.next;
    }
  }

}
