package org.raosantosh.algorithms.leetcode.tree2;

/**
 * Created by s.rao on 4/11/18.
 */
public class MinDepth {

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  public int minDepth(TreeNode root) {

    if(root == null) return 0;

    if(root.left == null && root.right == null)
      return 1;

    int leftDepth = Integer.MAX_VALUE;
    if(root.left != null)
        leftDepth = minDepth(root.left);

    int rightDepth = Integer.MAX_VALUE;
       if(root.right != null)
        rightDepth = minDepth(root.right);

    return Math.min(leftDepth , rightDepth);
  }

}
