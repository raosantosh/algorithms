package com.yahoo.sample.tree;

public class RecoverTree {

	public static void main(String args[]) {
//		TreeNode root = new TreeNode(2);
//		root.left = new TreeNode(3);
//		root.right = new TreeNode(1);
		
		TreeNode root = new TreeNode(3);
		root.right = new TreeNode(2);
		root.right.right = new TreeNode(1);
		

		RecoverTree tree = new RecoverTree();

		tree.recoverTree(root);
		System.out.println(tree.firstElement.val);
		System.out.println(tree.secondElement.val);
	}

	TreeNode firstElement = null;
	TreeNode secondElement = null;
	TreeNode prevElement = null;

	public void recoverTree(TreeNode root) {
		if (root == null)
			return;

		traverse(root);

		if (firstElement == null && secondElement == null)
			return;

		if (secondElement == null)
			secondElement = root;

		int temp = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = temp;
	}

	private void traverse(TreeNode node) {
		if (node == null)
			return;
		
		traverse(node.left);
		
		if(firstElement == null && (prevElement == null || prevElement.val >= node.val)) {
			firstElement = prevElement;
		}
		
		if(firstElement !=null && (prevElement == null || prevElement.val >= node.val)) {
			secondElement = node;
		}

		prevElement = node;
		
		if (firstElement != null && secondElement != null)
			return;

		traverse(node.right);

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
