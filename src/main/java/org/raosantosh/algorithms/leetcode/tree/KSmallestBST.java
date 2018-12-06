package org.raosantosh.algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

public class KSmallestBST {

	public static void main(String args[]) {
		TreeNode node = new TreeNode(2);
		node.left = new TreeNode(1);

		KSmallestBST kSmallestBST = new KSmallestBST();

		System.out.println(kSmallestBST.kthSmallest(node, 1));
	}

	public int kthSmallest(TreeNode root, int k) {
		List<Integer> elements = new ArrayList<>();
		inorder(root, k, elements);
		if (elements.size() >= k)
			return elements.get(k - 1);
		else
			return -1;
	}

	public void inorder(TreeNode node, int k, List<Integer> array) {

		if (node == null || array.size() >= k)
			return;

		if (node.left != null)
			inorder(node.left, k, array);

		array.add(node.val);

		if (array.size() >= k) {
			return;
		}

		if (node.right != null)
			inorder(node.right, k, array);
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
