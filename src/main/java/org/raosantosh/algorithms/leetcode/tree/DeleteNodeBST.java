package com.yahoo.sample.tree;

public class DeleteNodeBST {

	public static void main(String args[]) {
		// [5,3,6,2,4,null,7] 3
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(7);

		DeleteNodeBST tree = new DeleteNodeBST();
		// System.out.println(tree.deleteNode(root, 3).val);

		root = new TreeNode(1);
		root.right = new TreeNode(2);
		// System.out.println(tree.deleteNode(root, 1).val);

		root = new TreeNode(1);
		// System.out.println(tree.deleteNode(root, 1));

		root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.right = new TreeNode(4);
		root.left.right = new TreeNode(2);

		// System.out.println(tree.deleteNode(root, 1).val);

		String serialized = "33,16,40,2,24,35,42,1,5,23,26,34,39,41,45,0,null,4,10,18,null,25,30,null,null,36,null,null,null,43,48,null,null,3,null,9,15,17,22,null,null,28,31,null,38,null,44,46,49,null,null,6,null,14,null,null,null,19,null,27,29,null,32,37,null,null,null,null,47,null,null,null,8,12,null,null,20,null,null,null,null,null,null,null,null,null,null,7,null,11,13,null,21";
		SerializeTree treeSerializer = new SerializeTree();
		root = treeSerializer.deserialize(serialized);
		System.out.println(tree.deleteNode(root, 26).val);

	}

	 public TreeNode deleteNode(TreeNode root, int key) {
	 if (root == null)
	 return null;
	
	 TreeNode node = findNode(root, key);
	 if (node == null)
	 return root;
	
	 if (!deleteNode1(null, root, key))
	 return null;
	
	 return root;
	 }

//	public TreeNode deleteNode(TreeNode root, int key) {
//		if (root == null)
//			return root;
//		if (root.val > key)
//			root.left = deleteNode(root.left, key);
//		else if (root.val < key)
//			root.right = deleteNode(root.right, key);
//		else { // found node to be deleted
//			if (root.left == null)
//				return root.right;
//			else if (root.right == null)
//				return root.left;
//			root.val = findMin(root.right).val;
//			root.right = deleteNode(root.right, root.val);
//		}
//		return root;
//	}

	public boolean deleteNode1(TreeNode parent, TreeNode currentNode, int key) {
		if (currentNode.val == key) {
			if (parent == null) {
				if (currentNode.right != null) {
					TreeNode minNode = findMin(currentNode.right);
					currentNode.val = minNode.val;
					return deleteNode1(currentNode, currentNode.right, minNode.val);
				} else if (currentNode.left != null) {
					currentNode.val = currentNode.left.val;
					return deleteNode1(currentNode, currentNode.left, currentNode.val);
				} else
					return false;
			}

			if (currentNode.left == null && currentNode.right != null) {
				if (parent.left != null && parent.left.val == currentNode.val) {
					parent.left = currentNode.right;
				} else {
					parent.right = currentNode.right;
				}
			} else if (currentNode.right == null && currentNode.left != null) {
				if (parent.left != null && parent.left.val == currentNode.val) {
					parent.left = currentNode.left;
				} else {
					parent.right = currentNode.left;
				}
			} else if (currentNode.left == null && currentNode.right == null) {
				if (parent.left != null && currentNode.val == parent.left.val)
					parent.left = null;
				else
					parent.right = null;
			} else {
				TreeNode minNode = findMin(currentNode.right);
				TreeNode maxNode = findMax(currentNode.left);

				if (minNode.val < maxNode.val) {
					currentNode.val = minNode.val;
					deleteNode1(currentNode, currentNode.right, minNode.val);
				} else {
					currentNode.val = maxNode.val;
					deleteNode1(currentNode, currentNode.left, maxNode.val);
				}
			}
		} else if (key < currentNode.val) {
			return deleteNode1(currentNode, currentNode.left, key);
		} else {
			return deleteNode1(currentNode, currentNode.right, key);
		}

		return true;
	}

	public TreeNode findMax(TreeNode node) {
		TreeNode minNode = node;

		while (minNode.right != null)
			minNode = minNode.right;

		return minNode;
	}

	public TreeNode findMin(TreeNode node) {
		TreeNode minNode = node;

		while (minNode.left != null)
			minNode = minNode.left;

		return minNode;
	}

	private int getMin(TreeNode root) {
		int min = root.val;
		
		while (root.left != null) {
			root = root.left;
			min = root.val;
		}
		return min;
	}

	public TreeNode findNode(TreeNode node, int key) {
		if (node == null)
			return null;
		if (node.val == key)
			return node;

		if (key <= node.val)
			return findNode(node.left, key);

		return findNode(node.right, key);
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
