package com.yahoo.sample.summary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedCollection {

	public static void main(String args[]) {
		RandomizedCollection collection = new RandomizedCollection();

//		collection.insert(-1);
//		collection.remove(-2);
//		collection.insert(-2);
//		collection.getRandom();
//		collection.remove(-1);
//		collection.remove(-2);
//		collection.getRandom();
		collection.insert(10);
		collection.insert(10);
		collection.insert(20);
		collection.insert(20);
		collection.insert(30);
		collection.insert(30);
		collection.remove(10);
		collection.remove(10);
		collection.remove(30);
		collection.remove(30);
	}

	private Map<Integer, List<Integer>> elementMap = new HashMap<>(500);
	private List<Integer> elementList = new ArrayList<>(500);

	/** Initialize your data structure here. */
	public RandomizedCollection() {
		elementMap = new HashMap<>();
		elementList = new ArrayList<>();
	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */
	public boolean insert(int val) {
		elementList.add(val);

		boolean exists = elementMap.containsKey(val);

		if (!exists) {
			elementMap.put(val, new ArrayList<>(500));
		}
		elementMap.get(val).add(elementList.size() - 1);

		System.out.println("imap:" + elementMap);
		System.out.println("ilis:" + elementList);

		return !exists;
	}

	/**
	 * Removes a value from the collection. Returns true if the collection
	 * contained the specified element.
	 */
	public boolean remove(int val) {
		boolean exists = elementMap.containsKey(val);

		if (exists) {
			List<Integer> positions = elementMap.get(val);
			
			int toIndex = positions.get(positions.size() - 1);
			positions.remove(positions.size() - 1);
			if (positions.isEmpty())
				elementMap.remove(val);

			int elementToPosition = elementList.get(elementList.size() - 1);
			
			if(elementToPosition != val && toIndex < elementList.size() ) {
				List<Integer> newElementPosList = elementMap.get(elementToPosition);
				newElementPosList.remove(newElementPosList.size() - 1);
				newElementPosList.add(0, toIndex);
				elementList.set(toIndex, elementToPosition);
			}
		
			elementList.remove(elementList.size() - 1);
		}

		System.out.println("rmap:" + elementMap);
		System.out.println("rlis:" + elementList);

		return exists;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
//		System.out.println("gmap:" + elementMap);
//		System.out.println("glis:" + elementList);
		return elementList.get(((new Random()).nextInt(elementList.size())));
	}
}