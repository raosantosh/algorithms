package org.raosantosh.algorithms.leetcode.numerical;

import org.raosantosh.algorithms.leetcode.Common;

public class SearchRotatedArray2 {

	public static void main(String args[]) {

		SearchRotatedArray2 search = new SearchRotatedArray2();

//		System.out.println(search.search(Common.toIntArray("1,2,3,4,5"), 5));
//		System.out.println(search.search(Common.toIntArray("4,5,1,2,3"), 5));
//		System.out.println(search.search(Common.toIntArray("1,2,3,4,6"), 5));
//		System.out.println(search.search(Common.toIntArray("5,1,2,3,4"), 5));
//		System.out.println(search.search(Common.toIntArray("5"), 5));
//		System.out.println(search.search(Common.toIntArray("3,1"), 1));
//		System.out.println(search.search(Common.toIntArray("1,1,3,1"), 3));
//		System.out.println(search.search(Common.toIntArray("4,5,6,7,8,1,2,3"), 8));
		System.out.println(search.search(Common.toIntArray("3,1,1"), 3));
	}

	public boolean search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left <= right) {
			
			int mid = (left + right) / 2;

			System.out.println("l: "+ left + " r: "+ right + " m "+ mid);
			
			if (nums[mid] == target)
				return true;
			
			if(nums[mid] <= nums[right] && target >= nums[mid] && target <= nums[right]) {
				left = mid +1;
			}else if(nums[mid] >= nums[left] && target <= nums[mid] && target >= nums[left]) {
				right = mid -1;// && target <= nums[right]
			}else if(nums[mid] >= nums[right]) {
				left = mid + 1;
			} else{
				right = mid -1;
			}
		}

		return false;
	}
}
