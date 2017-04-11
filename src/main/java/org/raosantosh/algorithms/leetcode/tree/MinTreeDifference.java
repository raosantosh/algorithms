package com.yahoo.sample.tree;

import java.util.ArrayList;
import java.util.List;

public class MinTreeDifference {

	public int getMinimumDifference(TreeNode root) {
		List<Integer> ordered = new ArrayList<>();
		
		inorder(root, ordered);
		
		int min = Integer.MAX_VALUE;
		for(int i = 1; i < ordered.size(); ++i) {
			min = Integer.min(min, Math.abs(ordered.get(i-1) - ordered.get(i)));
		}
		
		return min;
	}

	private void inorder(TreeNode node, List<Integer> ordered) {
		if (node == null)
			return;
		if (node.left != null)
			inorder(node.left, ordered);

		ordered.add(node.val);
		if (node.right != null)
			inorder(node.right, ordered);
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
