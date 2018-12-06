package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 6/26/18.
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

 You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

 Example 1:

 Input: 1->2->3->4->5->NULL
 Output: 1->3->5->2->4->NULL
 Example 2:

 Input: 2->1->3->5->6->4->7->NULL
 Output: 2->3->6->7->1->5->4->NULL
 */
public class EvenOddLinkedList {

 public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

  // a -> b -> c -> d -> e -> f -> g
  // a -> c -> e -> g -> b -> d -> f
  // Point a -> c and point b ->

  public ListNode oddEvenList(ListNode head) {

   if(head == null) return null;

   ListNode fakeHead = new ListNode(0);
   fakeHead.next = head;
   ListNode evenHead = head.next;

   ListNode oddNode = head;
   ListNode evenNode = head.next;

   while(evenNode !=null && evenNode.next !=null ) {
     oddNode.next = evenNode.next;
     oddNode = oddNode.next;

     evenNode.next = oddNode.next;
     evenNode = evenNode.next;
   }

   oddNode.next = evenHead;

   return fakeHead.next;
  }

  public static void main(String args[]) {

   EvenOddLinkedList list = new EvenOddLinkedList();

   ListNode node = new ListNode(1);
   /*node.next = new ListNode(2);
   node.next.next = new ListNode(3);
   node.next.next.next = new ListNode(4);
   node.next.next.next.next = new ListNode(5);
   node.next.next.next.next.next = new ListNode(6);
   node.next.next.next.next.next.next = new ListNode(7);
*/
   list.printList(node);

   ListNode result = list.oddEvenList(node);
   list.printList(result);
  }

  public void printList(ListNode node) {
   while(node != null) {
     System.out.println(" -> " + node.val);
     node = node.next;
   }
  }

}
