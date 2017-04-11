package com.yahoo.sample.numerical;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TwoSum {

	public static void main(String args[]) {

		TwoSum twoSum = new TwoSum();
		// int[] result = twoSum.twoSum(toIntArray("2,7,11,15"), 9);
		// System.out.println("Lower index:" + result[0] + " Higher index:" +
		// result[1]);
		// result = twoSum.twoSum(toIntArray("2,7,11,15"), 10);
		// System.out.println("Lower index:" + result[0] + " Higher index:" +
		// result[1]);
		// result = twoSum.twoSum(toIntArray("2,7,11,16"), 27);
		// System.out.println("Lower index:" + result[0] + " Higher index:" +
		// result[1]);
		// result = twoSum.twoSum(toIntArray("3,2,4"), 6);
		// System.out.println("Lower index:" + result[0] + " Higher index:" +
		// result[1]);
		int[] result = twoSum.twoSum(toIntArray("3,3"), 6);
		System.out.println("Lower index:" + result[0] + " Higher index:" + result[1]);
	}

	public static int[] toIntArray(String value) {
		String[] vals = value.split(",");
		int[] result = new int[vals.length];

		for (int i = 0; i < vals.length; ++i) {
			result[i] = Integer.valueOf(vals[i]);
		}

		return result;
	}

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, List<Integer>> myMap = new TreeMap<>();

		for (int i = 0; i < nums.length; ++i) {
			List<Integer> vals = new ArrayList<>();
			if (myMap.containsKey(nums[i])) {
				vals = myMap.get(nums[i]);
			}
			vals.add(i);
			myMap.put(nums[i], vals);
		}

		int sortedNums[] = new int[nums.length];
		int posNums[] = new int[nums.length];
		int position = 0;
		for (Map.Entry<Integer, List<Integer>> entry : myMap.entrySet()) {
			List<Integer> positions = entry.getValue();
			for (Integer pos : positions) {
				sortedNums[position] = entry.getKey();
				posNums[position++] = pos;
			}
		}

		int[] result = new int[2];
		int lowerIndex = 0;
		int highestIndex = nums.length - 1;

		while (true) {
			if (lowerIndex >= highestIndex)
				break;
			if (sortedNums[lowerIndex] + sortedNums[highestIndex] == target) {
				result[0] = posNums[lowerIndex];
				result[1] = posNums[highestIndex];
				break;
			} else if (target > (sortedNums[lowerIndex] + sortedNums[highestIndex])) {
				lowerIndex++;
			} else {
				highestIndex--;
			}
		}

		return result;
	}

}
