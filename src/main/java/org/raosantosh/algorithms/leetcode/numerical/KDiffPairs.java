package com.yahoo.sample.numerical;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.yahoo.sample.Common;

public class KDiffPairs {

	public static void main(String args[]) {
		KDiffPairs kDiffPairs = new KDiffPairs();
		System.out.println(kDiffPairs.findPairs(Common.toIntArray("3, 1, 4, 1, 5"), 2));
		System.out.println(kDiffPairs.findPairs(Common.toIntArray("1,2,3,4,5"), 1));
	}

	public int findPairs(int[] nums, int k) {
		Arrays.sort(nums);
		Set<Pair> pairs = new HashSet<>();

		int i = 0;
		int j = 1;

		while (j <= (nums.length - 1)) {
			if (i == j) {
				j++;
				continue;
			}
			if (nums[j] - nums[i] == k) {
				pairs.add(new Pair(nums[i], nums[j]));
				i++;
			} else if (nums[j] - nums[i] < k) {
				j++;
			} else {
				i++;
			}
		}

		return pairs.size();
	}

	public static class Pair {
		int i;
		int j;

		public Pair(int i, int j) {
			if (i > j) {
				this.j = i;
				this.i = j;
			} else {
				this.j = j;
				this.i = i;
			}
		}

		@Override
		public int hashCode() {
			return (new Integer(i)).hashCode() * 31 + (new Integer(j)).hashCode();
		}

		@Override
		public boolean equals(Object obj) {

			Pair pair = (Pair) obj;
			if (pair.i == i && pair.j == j)
				return true;

			return false;
		}

	}

}
