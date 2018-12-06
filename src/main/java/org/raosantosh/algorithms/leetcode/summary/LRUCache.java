package org.raosantosh.algorithms.leetcode.summary;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

	public static void main(String args[]) {
		LRUCache cache = new LRUCache(2);
//		cache.put(1, 1);
//		cache.put(2, 2);
//		System.out.println(cache.get(1));       // returns 1
//		cache.put(3, 3);    // evicts key 2
//		System.out.println(cache.get(2));       // returns -1 (not found)
//		cache.put(4, 4);    // evicts key 1
//		System.out.println(cache.get(1));       // returns -1 (not found)
//		System.out.println(cache.get(3));       // returns 3
//		System.out.println(cache.get(4));       // returns 4
		
		System.out.println(cache.get(2));
		cache.put(2,6);
		System.out.println(cache.get(1));
		cache.put(1,5);
		cache.put(1,2);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
	}
	
	private Map<Integer, Integer> map = new LinkedHashMap<>();
	private int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		int value = -1;
		if (map.containsKey(key)) {
			value = map.get(key);
			map.remove(key);
			map.put(key, value);
		}

		return value;
	}

	public void put(int key, int value) {
		if(map.containsKey(key)) {
			map.remove(key);
		}
		
		map.put(key, value);
		
		if(map.size() > capacity) {
			map.remove(map.entrySet().iterator().next().getKey());
		}
	}
}
