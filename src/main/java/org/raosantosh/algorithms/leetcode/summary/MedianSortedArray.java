package org.raosantosh.algorithms.leetcode.summary;

import org.raosantosh.algorithms.leetcode.Common;

public class MedianSortedArray {

	public static void main(String args[]) {
		MedianSortedArray sortedArray = new MedianSortedArray();

		System.out.println(
				sortedArray.findMedianSortedArrays(Common.toIntArray("1,2,3,4,5"), Common.toIntArray("1,2,3,4,5")));
		System.out.println(sortedArray.findMedianSortedArrays(Common.toIntArray("1,2,3,4,5,6,7,8,9,10"),
				Common.toIntArray("1,2,3,4,5")));
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int total = nums1.length + nums2.length;
		int median1 = (total + 1) / 2;
		int median2 = (total + 2) / 2;

		return (getKthElement(nums1, 0, nums2, 0, median1) + getKthElement(nums1, 0, nums2, 0, median2)) / 2.0;
	}

	private double getKthElement(int[] nums1, int nums1Start, int[] nums2, int nums2Start, int k) {
		if (nums1Start > nums1.length - 1) {
			return nums2[nums2Start + k - 1];
		}

		if (nums2Start > nums2.length - 1) {
			return nums1[nums1Start + k - 1];
		}

		if (k == 1)
			return Math.min(nums1[nums1Start], nums2[nums2Start]);

		int nums1Mid = Integer.MAX_VALUE;
		int nums2Mid = Integer.MAX_VALUE;
		if (nums1Start + k / 2 - 1 < nums1.length)
			nums1Mid = nums1[nums1Start + k / 2 - 1];
		if (nums2Start + k / 2 - 1 < nums2.length)
			nums2Mid = nums2[nums2Start + k / 2 - 1];

		if (nums1Mid < nums2Mid) {
			return getKthElement(nums1, nums1Start + k / 2, nums2, nums2Start, k - k / 2);
		}

		return getKthElement(nums1, nums1Start, nums2, nums2Start + k / 2, k - k / 2);
	}
}
