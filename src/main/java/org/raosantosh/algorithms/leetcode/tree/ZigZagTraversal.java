package com.yahoo.sample.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class ZigZagTraversal {

	public static void main(String args[]) {

	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if(root == null) 
			return result;
			
		Queue<Item> items = new ArrayDeque<>();
		items.add(new Item(root, 0));

		
		List<Integer> firstLevel = new ArrayList<>();
		firstLevel.add(root.val);
		result.add(firstLevel);

		while (!items.isEmpty()) {
			Item item = items.poll();
			int toUseLevel = item.level + 1;

			List<Integer> levelElements = new ArrayList<>();
			if (result.size() > toUseLevel) {
				levelElements = result.get(toUseLevel);
			}else {
				result.add(levelElements);
			}

			if (item.value.left != null) {
				items.add(new Item(item.value.left, toUseLevel));
				levelElements.add(item.value.left.val);
			}
			if (item.value.right != null) {
				items.add(new Item(item.value.right, toUseLevel));
				levelElements.add(item.value.right.val);
			}
			
			if(levelElements.size() == 0) 
				result.remove(levelElements);
			
		}
		
		for(int i=0; i < result.size(); ++i) {
			if(i % 2 == 0) continue;
			else {
				Collections.reverse(result.get(i));
			}
		}

		return result;

	}

	public static class Item {
		TreeNode value;
		int level;

		Item(TreeNode value, int level) {
			this.value = value;
			this.level = level;
		}
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
