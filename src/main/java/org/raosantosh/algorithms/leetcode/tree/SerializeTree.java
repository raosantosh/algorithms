package org.raosantosh.algorithms.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import org.raosantosh.algorithms.leetcode.tree.CountTreeNodes.TreeNode;

public class SerializeTree {

	public static void main(String args[]) {
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(5);
		root.right = new TreeNode(4);
		root.left.left = new TreeNode(3);
		root.right.right = new TreeNode(2);

		SerializeTree tree = new SerializeTree();

		 String serialized = tree.serialize(root);
		
		System.out.println("Serialized: " + serialized);

		TreeNode newRoot = tree.deserialize(serialized);

		root = new TreeNode(1);
		root.right = new TreeNode(2);

		serialized = tree.serialize(root);
		System.out.println("Serialized: " + serialized);
	}

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {

		if (root == null)
			return null;

		String result = String.valueOf(root.val);
		result += "," + getString(root.left);

		result += "," + getString(root.right);

		return result;
	}

	public String getString(TreeNode node) {
		if (node == null)
			return null;

		return String.valueOf(node.val) + "," + getString(node.left) + "," + getString(node.right);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {

		if (data == null)
			return null;

		Deque<String> splitStringNodes = new ArrayDeque<>(Arrays.asList(data.split(",")));

		return createTree(splitStringNodes);
	}

	public TreeNode createTree(Deque<String> nodes) {

		if (nodes.isEmpty())
			return null;

		String data = nodes.removeFirst();

		if (data.equals("null")) {
			return null;
		}

		TreeNode node = new TreeNode(Integer.valueOf(data));

		TreeNode left = createTree(nodes);
		TreeNode right = createTree(nodes);
		node.left = left;
		node.right = right;

		return node;
	}

//	public static class TreeNode {
//		int val;
//		TreeNode left;
//		TreeNode right;
//
//		TreeNode(int x) {
//			val = x;
//		}
//	}
}
