package org.raosantosh.algorithms.leetcode.tree2;

/**
 * Created by s.rao on 6/1/18.
 */
public class MaxLeafToLeaf {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  int max = 0;

  public int maxLeafToLeaf(TreeNode node){

    if(node == null) return 0;

    int leftmax = maxLeafToLeaf(node.left);
    int rightMax = maxLeafToLeaf(node.right);

    max = Math.max(max,leftmax + rightMax + 1);

    return Math.max(leftmax, rightMax) + 1;
  }

  public static void main(String args[]) {

    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(6);
    root.right = new TreeNode(7);

    MaxLeafToLeaf max = new MaxLeafToLeaf();
    max.maxLeafToLeaf(root);
    System.out.println(max.max);
  }
}
