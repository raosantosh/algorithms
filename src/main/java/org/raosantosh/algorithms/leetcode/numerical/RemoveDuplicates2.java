package org.raosantosh.algorithms.leetcode.numerical;

import org.raosantosh.algorithms.leetcode.Common;

public class RemoveDuplicates2 {

	public static void main(String args[]) {
		RemoveDuplicates duplicates = new RemoveDuplicates();

		System.out.println("DONE Res: " + duplicates.removeDuplicates(Common.toIntArray("1,2,2,3,4")));
		System.out.println("DONE Res: " + duplicates.removeDuplicates(Common.toIntArray("1,1,1,1")));
		System.out.println("DONE Res: " + duplicates.removeDuplicates(Common.toIntArray("1,3,5,7")));
		System.out.println("DONE Res: " + duplicates.removeDuplicates(Common.toIntArray("1,1,2")));
	}

	public int removeDuplicates(int[] nums) {
		return -1;
	}
}
