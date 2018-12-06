package org.raosantosh.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class MaximumPathSum {

	public static void main(String args[]) {
		MaximumPathSum pathSum = new MaximumPathSum();

		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		System.out.println(pathSum.maxPathSum(root));
	}

	public int maxPathSum(TreeNode root) {

		List<Integer> sumValue = new ArrayList<>();
		sumValue.add(Integer.MIN_VALUE);

		dfs(root, sumValue);
		return sumValue.get(0);
	}

	public int dfs(TreeNode node, List<Integer> sumValue) {
		if (node == null)
			return 0;

		int leftSum = Math.max(0, dfs(node.left, sumValue));
		int rightSum = Math.max(0, dfs(node.right, sumValue));

		int max = Math.max(sumValue.get(0), leftSum + rightSum + node.val);

		if (max > sumValue.get(0)) {
			sumValue.clear();
			sumValue.add(max);
		}

		return node.val + Integer.max(leftSum, rightSum);
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int x) {
			val = x;
		}
	}

}
