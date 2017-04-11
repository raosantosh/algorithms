package com.yahoo.sample.tree;

import java.util.ArrayList;
import java.util.List;

public class ValidateTree {
	
	 public static class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
	 
	 public static void main(String args[]) {
		 ValidateTree tree = new ValidateTree();
		 TreeNode node = new TreeNode(3);
	 }

	public boolean isValidBST(TreeNode root) {
		return inoder(root, new ArrayList<>());
	}
	
	private boolean inoder(TreeNode node, List<Integer> currentList) {
		
		boolean isValid = true;
		
		if(node.left != null) 
			isValid = inoder(node.left, currentList);
		
		if(!isValid ) return false;
		
		if(currentList.get(currentList.size() - 1) > node.val) return false;
		
		currentList.add(node.val);
		
		if(node.right != null)
			isValid = inoder(node.right, currentList);
		
		return isValid;
		
	}
	
	

}
