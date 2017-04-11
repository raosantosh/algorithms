package com.yahoo.sample.numerical;

import com.yahoo.sample.Common;

public class RemoveDuplicates {

	public static void main(String args[]) {
		RemoveDuplicates duplicates = new RemoveDuplicates();

		 System.out.println("DONE Res: "+duplicates.removeDuplicates(Common.toIntArray("1,2,2,3,4")));
		 System.out.println("DONE Res: " +duplicates.removeDuplicates(Common.toIntArray("1,1,1,1")));
		System.out.println("DONE Res: " + duplicates.removeDuplicates(Common.toIntArray("1,3,5,7")));
		System.out.println("DONE Res: " + duplicates.removeDuplicates(Common.toIntArray("1,1,2")));

	}

	public int removeDuplicates(int[] nums) {

		if (nums.length < 2)
			return 0;

		int maxPosition = 1;
		int currentPosition = 0;

		while (maxPosition < nums.length) {
			if (nums[currentPosition] != nums[maxPosition]) {
				++currentPosition;
				++maxPosition;
			} else {
				while (maxPosition < nums.length && nums[currentPosition] == nums[maxPosition]) {
					++maxPosition;
				}

				if (currentPosition < nums.length && maxPosition < nums.length)
					nums[++currentPosition] = nums[maxPosition];

			}
		}

		return currentPosition + 1;
	}
}
