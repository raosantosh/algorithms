package org.raosantosh.algorithms.leetcode.tree2;

/**
 * Created by s.rao on 4/11/18.
 */
public class MaxDepthTree {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  public int maxDepth(TreeNode root) {
    if(root == null) return 0;

    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);

    return Math.max(leftDepth + 1, rightDepth + 1);
  }
}
