package org.raosantosh.algorithms.leetcode.tree2;

/**
 * Created by s.rao on 4/21/18.
 */


public class InvertBinaryTree {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  public TreeNode invertTree(TreeNode root) {

    if(root == null) return null;

    TreeNode tmpLeft = root.left;

    root.left = invertTree(root.right);
    root.right = invertTree(tmpLeft);

    return root;
  }

  public static void main(String args[]) {

  }


}
