package com.yahoo.sample.summary;

import java.util.HashMap;
import java.util.Map;

public class TimeTravelingMapTree {

	private Map<String, TreeElement> elementMap = new HashMap<>();

	public static void main(String args[]) {
		TimeTravelingMapTree timeTravelingMap = new TimeTravelingMapTree();

		timeTravelingMap.insert("foo", 30, "foo30");
		timeTravelingMap.insert("foo", 10, "foo10");
		timeTravelingMap.insert("foo", 20, "foo20");
		timeTravelingMap.insert("foo", 40, "foo40");

		System.out.println("Foor for 29: " + timeTravelingMap.getValue("foo", 29));
		System.out.println("Foor for 30: " + timeTravelingMap.getValue("foo", 30));
		System.out.println("Foor for 40: " + timeTravelingMap.getValue("foo", 40));
		System.out.println("Foor for 19: " + timeTravelingMap.getValue("foo", 19));
		System.out.println("Foor for 0: " + timeTravelingMap.getValue("foo", 0));
		System.out.println("Foor for -1: " + timeTravelingMap.getValue("foo", -1));
	}

	public void insert(String key, int time, String value) {
		TreeElement element = new TreeElement(value, time);

		if (elementMap.containsKey(key)) {
			insertNode(elementMap.get(key), element);
		} else {
			elementMap.put(key, element);
		}
	}

	private void inorder(TreeElement element) {
		if (element.left != null)
			inorder(element.left);
		System.out.println("Element is " + element.time);
		if (element.right != null)
			inorder(element.right);
	}

	private void insertNode(TreeElement node, TreeElement element) {
		if (element.time > node.time) {
			if (node.right == null)
				node.right = element;
			else
				insertNode(node.right, element);
		} else {
			if (node.left == null)
				node.left = element;
			else
				insertNode(node.left, element);
		}
	}

	public String getValue(String key, int time) {
		TreeElement element = elementMap.get(key);
		inorder(element);

		return findNode(element, time).value;
	}

	private TreeElement findNode(TreeElement node, int time) {

		if (node == null)
			return null;

		if (node.time == time)
			return node;

		TreeElement element = null;

		if (time > node.time) {
			element = findNode(node.right, time);
		} else {
			element = findNode(node.left, time);
		}

		if (element == null)
			return node;

		return element;
	}

	public static class TreeElement {
		TreeElement left, right;
		String value;
		int time;

		public TreeElement(String value, int time) {
			this.value = value;
			this.time = time;
		}
	}
}
