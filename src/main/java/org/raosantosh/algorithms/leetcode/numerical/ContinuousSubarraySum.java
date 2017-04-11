package com.yahoo.sample.numerical;

import com.yahoo.sample.Common;

public class ContinuousSubarraySum {

	public static void main(String args[]) {

		ContinuousSubarraySum continuousSubarraySum = new ContinuousSubarraySum();

		System.out.println(continuousSubarraySum.checkSubarraySum(Common.toIntArray("23, 2, 4, 6, 36"), 41));
		System.out.println(continuousSubarraySum.checkSubarraySum(Common.toIntArray("0,0"), 0));
		System.out.println(continuousSubarraySum.checkSubarraySum(Common.toIntArray("1,1"), 2));
	}

	public boolean checkSubarraySum(int[] nums, int k) {
		
		if(isPrime(k))
			return false;
		
		if (k == 0) {
			if (nums.length < 2)
				return false;

			for (int num : nums) {
				if (num != 0)
					return false;
			}
			return true;
		}

		for (int i = 0; i < nums.length - 1; ++i) {
			long sum = nums[i];
			for (int j = i + 1; j < nums.length; ++j) {
				sum+= nums[j];
				if (sum % k == 0) {
					return true;
				}
			}
		}

		return false;
	}

	private long getSumBetween(int[] nums, int start, int end) {
		int sum = 0;
		for (int i = start; i <= end; ++i) {
			sum += nums[i];
		}

		return sum;
	}

	private boolean isPrime(int num) {
		int temp = 0;
		boolean isPrime = false;
		for (int i = 2; i <= num / 2; i++) {
			temp = num % i;
			if (temp == 0) {
				isPrime = false;
				break;
			}
		}

		return isPrime;
	}
}
