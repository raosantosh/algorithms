package org.raosantosh.algorithms.leetcode.summary;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LFUCache {

	public static void main(String args[]) {
		LFUCache cache = new LFUCache(10);

		// cache.put(1, 1);
		// cache.put(2, 2);
		// cache.get(1); // returns 1
		// cache.put(3, 3); // evicts key 2
		// cache.get(2); // returns -1 (not found)
		// cache.get(3); // returns 3.
		// cache.put(4, 4); // evicts key 1.
		// cache.get(1); // returns -1 (not found)
		// cache.get(3); // returns 3
		// cache.get(4); // returns 4
		
		Set<Integer> mySet = new LinkedHashSet<>();
		mySet.add(1);
		mySet.add(2);
		System.out.println(mySet.iterator().next());
		
		
//		[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],
//		[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],
//		[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],
//		[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],
//		[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],
//		[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],
//		[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],
//		[13,28],[11,26]]
//				
		cache.put(10, 13);
		cache.put(13, 17);
		cache.put(6,11);
		cache.put(10,5);
		cache.put(9,10);
		cache.get(13);
		cache.put(2,19);
		cache.get(2);
		cache.get(3);
		cache.put(5,25);
		cache.get(8);
		cache.put(9,22);
		cache.put(5,5);
		cache.put(1,30);
		cache.get(11);
		cache.put(9,12);
		cache.get(7);
		cache.get(5);
		cache.get(8);
		cache.get(9);
		cache.put(4,30);
		cache.put(9,3);
		cache.get(9);
		cache.get(10);
		cache.get(10);
		cache.put(6,14);
		cache.put(3,1);
		cache.get(3);
		cache.put(10,11);
		cache.get(8);
		cache.put(2,14);
		cache.get(1);
		cache.get(5);
		cache.get(4);
		cache.put(11,14);
		cache.put(12,24);
		cache.put(5,18);
		cache.get(13);
		cache.put(7,23);
		cache.get(8);
		cache.get(12);
		cache.put(3,27);
		cache.put(2,12);
		cache.get(5);
		cache.put(2,9);
		cache.put(13,4);
		cache.put(8,18);
		cache.put(1,7);
		cache.get(6);
		
		
		
		


//		cache.put(2, 1);
//		cache.put(1, 1);
//		cache.put(2, 3);
//		cache.put(4, 1);
//		cache.get(1);
//		cache.get(2);

	}

	private int capacity;
	private int currentSize;
	private Map<Integer, Integer> contentMap = new HashMap<>();
	private Map<Integer, Integer> contentCountMap = new HashMap<>();
	private Map<Integer, Set<Integer>> countValuesMap = new TreeMap<>();

	public LFUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!contentMap.containsKey(key))
			return -1;

		// Update the count in contnet count
		int count = contentCountMap.get(key);
		contentCountMap.remove(key);
		contentCountMap.put(key, count + 1);

		// Remove from the list for this content
		Set<Integer> values = countValuesMap.get(count);
		values.remove(key);

		if (values.isEmpty()) {
			countValuesMap.remove(count);
		}

		if (countValuesMap.containsKey(count + 1)) {
			countValuesMap.get(count + 1).add(key);
		} else {
			Set<Integer> countSet = new LinkedHashSet<>();
			countSet.add(key);
			countValuesMap.put(count + 1, countSet);
		}

		System.out.println("g: for " + key + " " + contentMap);
		System.out.println("g: for " + key + " " + contentCountMap);
		System.out.println("g: for " + key + " " + countValuesMap);

		return contentMap.get(key);
	}

	public void put(int key, int value) {

		if (capacity == 0)
			return;

		if (contentMap.containsKey(key)) {
			
			int cnt = contentCountMap.get(key);
			countValuesMap.get(cnt).remove(key);
			if(countValuesMap.get(cnt).isEmpty())
				countValuesMap.remove(cnt);
			
//			contentCountMap.remove(key);
			contentCountMap.put(key, cnt + 1);
			
			
			if (countValuesMap.containsKey(cnt + 1)) {
				countValuesMap.get(cnt + 1).add(key);
			} else {
				Set<Integer> countSet = new LinkedHashSet<>();
				countSet.add(key);
				countValuesMap.put(cnt + 1, countSet);
			}
			
			contentMap.put(key, value);
		} else if (currentSize >= capacity) {
			Map.Entry<Integer, Set<Integer>> valueEntry = countValuesMap.entrySet().iterator().next();
			Integer valueToRemove = valueEntry.getValue().iterator().next();
			valueEntry.getValue().remove(valueToRemove);
			if (valueEntry.getValue().size() == 0) {
				countValuesMap.remove(valueEntry.getKey());
			}

			contentMap.remove(valueToRemove);
			contentCountMap.remove(valueToRemove);

			contentMap.put(key, value);
			contentCountMap.put(key, 1);

			if (countValuesMap.containsKey(1)) {
				countValuesMap.get(1).add(key);
			} else {
				Set<Integer> countSet = new LinkedHashSet<>();
				countSet.add(key);
				countValuesMap.put(1, countSet);
			}
		} else {
			contentMap.put(key, value);
			contentCountMap.put(key, 1);
			currentSize++;

			if (countValuesMap.containsKey(1)) {
				countValuesMap.get(1).add(key);
			} else {
				Set<Integer> countSet = new LinkedHashSet<>();
				countSet.add(key);
				countValuesMap.put(1, countSet);
			}

		}

//		System.out.println("s: for " + key + " " + contentMap);
//		System.out.println("s: for " + key + " " + contentCountMap);
//		System.out.println("s: for " + key + " " + countValuesMap);

	}
}
