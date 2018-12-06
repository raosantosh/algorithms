package org.raosantosh.algorithms.leetcode.tree2;

/**
 * Find the sum of all left leaves in a given binary tree.

 Example:

 3
 / \
 9  20
 /  \
 15   7

 There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {

    public class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  private int sumOfLeftLeaves(TreeNode node) {

      if(node == null) return 0;

      int sum = 0 ;

      if(node.left != null && node.left.left == null && node.right == null)
          sum += node.left.val;

      return sum + sumOfLeftLeaves(node.left) + sumOfLeftLeaves(node.right);
  }
}
