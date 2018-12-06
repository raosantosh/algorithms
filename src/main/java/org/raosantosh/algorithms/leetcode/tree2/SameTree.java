package org.raosantosh.algorithms.leetcode.tree2;

/**
 * Created by s.rao on 4/21/18.
 * Given two binary trees, write a function to check if they are the same or not.

 Two binary trees are considered the same if they are structurally identical and the nodes have the same value.

 Example 1:

 Input:     1         1
 / \       / \
 2   3     2   3

 [1,2,3],   [1,2,3]

 Output: true

 */
public class SameTree {

   public static class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
     if(p == null ^ q == null) return false;
     if(p == null && q == null) return true;

     return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right,q.right);
  }

}
