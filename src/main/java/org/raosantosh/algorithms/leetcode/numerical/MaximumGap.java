package com.yahoo.sample.numerical;

public class MaximumGap {

	public static void main(String args[]) {

		MaximumGap maximumGap = new MaximumGap();
		System.out.println(maximumGap.maximumGap(getIntValues("1,7,10")));
		System.out.println(maximumGap.maximumGap(getIntValues("1,4,10,11,20")));
		System.out.println(maximumGap.maximumGap(getIntValues("1,4,10")));
		System.out.println(maximumGap.maximumGap(getIntValues("1,1,1,1")));
	}

	public static int[] getIntValues(String value) {
		String[] splitString = value.split(",");
		int[] intArray = new int[splitString.length];
		for (int i = 0; i < splitString.length; ++i) {
			intArray[i] = Integer.valueOf(splitString[i]);
		}
		return intArray;
	}

	public int maximumGap(int[] nums) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int size = nums.length;
		
		if(nums.length < 2) return 0;

		for (int num : nums) {
			if (num > max) {
				max = num;
			}
			if (num < min) {
				min = num;
			}
		}

		int range = ((max - min) / size ) + 1;
		int rangeSize = ((max - min) / range) + 1;
		int[] minArray = new int[rangeSize];
		int[] maxArray = new int[rangeSize];
		

		for (int val : nums) {
			int bucket = ((val -min) / range) ;
			if (minArray[bucket] == 0 || val < minArray[bucket]) {
				minArray[bucket] = val;
			}
			if (val > maxArray[bucket]) {
				maxArray[bucket] = val;
			}
		}

		int maxDiff = 0;
		int currentMin = minArray[0];
		int currentMax = 0;
		
		for (int i = 0; i < rangeSize; ++i) {
			if (minArray[i] != 0) {
				currentMax = minArray[i];
			}

			maxDiff = Integer.max(maxDiff, currentMax - currentMin);
			if (maxArray[i] != 0)
				currentMin = maxArray[i];
		}

		return maxDiff;
	}

}
