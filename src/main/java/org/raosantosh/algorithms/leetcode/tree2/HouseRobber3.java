package org.raosantosh.algorithms.leetcode.tree2;

/**
 * Created by s.rao on 5/3/18.
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

 Determine the maximum amount of money the thief can rob tonight without alerting the police.

 Example 1:
 3
 / \
 2   3
 \   \
 3   1
 Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 Example 2:
 3
 / \
 4   5
 / \   \
 1   3   1
 Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobber3 {
   public static class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  public int rob(TreeNode root) {

     int[] rootOptions = getOptions(root);

     return Math.max(rootOptions[0], rootOptions[1]) ;
  }

  public int[] getOptions(TreeNode root) {

    if(root == null) return new int[] {0,0};

    int [] leftOptions = getOptions(root.left);
    int [] rightOptions = getOptions(root.right);

    int excludingValue = leftOptions[0] + rightOptions[0];
    int includingValue = Math.max(excludingValue, leftOptions[1] + rightOptions[1] + root.val);

    return new int[] {includingValue, excludingValue};
  }

  public static void main(String args[]) {

     HouseRobber3 robber = new HouseRobber3();

     TreeNode root = new TreeNode(3);
     TreeNode left = new TreeNode(2);
     TreeNode right = new TreeNode(3);
     root.left = left;
     root.right = right;
     TreeNode leftRight = new TreeNode(3);
     TreeNode rightRight = new TreeNode(1);
     root.left.right = leftRight;
     root.right.right = rightRight;

     System.out.println(robber.rob(root));

     TreeNode root1 = new TreeNode(4);
     root1.left = new TreeNode(1);
     root1.left.left = new TreeNode(2);
     root1.left.left = new TreeNode(3);
     System.out.println(robber.rob(root1));
  }
}
