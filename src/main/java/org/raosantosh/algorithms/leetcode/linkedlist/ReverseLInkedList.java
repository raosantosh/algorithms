package org.raosantosh.algorithms.leetcode.linkedlist;

/**
 * Created by s.rao on 4/11/18.
 */
public class ReverseLInkedList {

  public static class Node {
    int value;
    Node next;

    public Node(int value, Node next) {
      this.next = next;
      this.value = value;
    }
  }

  public Node reverse(Node root) {

    Node node = root;
    Node next = node.next;
    root.next = null;
    Node nextNext = null;
    while(next != null) {
      nextNext = next.next;
      next.next = node;

      node = next;
      next = nextNext;
    }

    return node;
  }

  public Node reverseAgain(Node root) {

    Node prev  = root ;
    Node current = root.next;
    Node next = null;
    if(current != null) next = current.next;

    while(current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    root.next = null;

    return prev;
  }

  public Node reverseRecursive(Node root) {
    if(root.next == null) return root;

    Node current = root.next;

    Node newRoot = reverseRecursive(root.next);
    current.next = root;
    root.next = null;
    return newRoot;
  }

  public static void main(String args[]) {
    Node three = new Node(3, null);
    Node two = new Node( 2, three);
    Node one = new Node( 1,two);
    print(one);

   ReverseLInkedList reverser = new ReverseLInkedList();
    Node result = reverser.reverseRecursive(one);
    print(result);
  }

  public static void print(Node root) {
    while (root != null) {
      System.out.println("Value is "+ root.value);
      root = root.next;
    }
  }
}
