package org.raosantosh.algorithms.leetcode.summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeTravelingMap {

	public static void main(String args[]) {
//		TimeTravelingMap timeTravelingMap = new TimeTravelingMap();
//		timeTravelingMap.insertElement("foo", 10, "foo10");
//		timeTravelingMap.insertElement("foo", 20, "foo20");
//		timeTravelingMap.insertElement("foo", 30, "foo30");
//		timeTravelingMap.insertElement("foo", 40, "foo40");
//
//		System.out.println("Foor for 29: " + timeTravelingMap.getValue("foo", 29));
//		System.out.println("Foor for 30: " + timeTravelingMap.getValue("foo", 30));
//		System.out.println("Foor for 40: " + timeTravelingMap.getValue("foo", 40));
//		System.out.println("Foor for 19: " + timeTravelingMap.getValue("foo", 19));
//		System.out.println("Foor for 0: " + timeTravelingMap.getValue("foo", 0));
//		System.out.println("Foor for -1: " + timeTravelingMap.getValue("foo", -1));
		
		String abc = ".";
		
		System.out.println(abc.equals(".."));
	}

	private Map<String, List<ElementPair>> elements = new HashMap<>();

	public void insertElement(String key, int time, String value) {
		List<ElementPair> keyElements = new ArrayList<>();

		if (elements.containsKey(key)) {
			keyElements = elements.get(key);
		}
		keyElements.add(new ElementPair(time, value));
		elements.put(key, keyElements);
	}

	public String getValue(String key, int time) {
		if (!elements.containsKey(key)) {
			return null;
		}

		return search(time, elements.get(key));
	}

	private String search(int time, List<ElementPair> keyElements) {
		if (time == -1) {
			return keyElements.get(keyElements.size() - 1).value;
		}

		return binarySearch(keyElements, 0, keyElements.size() - 1, time);
	}

	private String binarySearch(List<ElementPair> keyElements, int start, int end, int time) {

		int mid = (start + end) / 2;

		System.out.println("start" + start + " end " + end);
		
		
		if (keyElements.get(mid).time == time) {
			return keyElements.get(mid).value;
		} else if(start >= end) {
			return keyElements.get(mid).value;
		}

		if (time > keyElements.get(mid).time)
			return binarySearch(keyElements, mid + 1, end, time);
		else
			return binarySearch(keyElements, start, mid - 1, time);
	}

	public static class ElementPair {
		int time;
		String value;

		public ElementPair(int time, String value) {
			this.time = time;
			this.value = value;
		}
	}
}
