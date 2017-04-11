package com.yahoo.sample.summary;

import java.util.Collections;
import java.util.PriorityQueue;

import com.yahoo.sample.Common;

public class MedianInSliding {

	public static void main(String args[]) {
		String value = "1,3,-1,-3,5,3,6,7";

		MedianInSliding median = new MedianInSliding();
		double[] val = median.medianSlidingWindow(Common.toIntArray(value), 3);

		for (int i = 0; i < val.length; ++i) {
			System.out.println("Index: " + i + " Value: " + val[i]);
		}

		val = median.medianSlidingWindow(Common.toIntArray("1"), 1);

		for (int i = 0; i < val.length; ++i) {
			System.out.println("Index: " + i + " Value: " + val[i]);
		}
	}

	private PriorityQueue<Integer> topMinHeap = new PriorityQueue<>();
	private PriorityQueue<Integer> bottomMaxHeap = new PriorityQueue<>(Collections.reverseOrder());

	public double[] medianSlidingWindow(int[] nums, int k) {
		topMinHeap = new PriorityQueue<>();
		bottomMaxHeap = new PriorityQueue<>(Collections.reverseOrder());

		if (nums.length == 0) {
			return new double[0];
		}

		if (k >= nums.length) {
			for (int i = 0; i < nums.length; ++i) {
				add(nums[i]);
			}
			double[] res = new double[1];
			res[0] = getMid();
			return res;
		}

		for (int i = 0; i < k - 1; ++i) {
			add(nums[i]);
		}

		double[] result = new double[nums.length - k + 1];

		for (int i = k - 1; i < nums.length; ++i) {
			add(nums[i]);
			result[i - k + 1] = getMid();
			remove(nums[i - k + 1]);
		}

		return result;
	}

	private void add(int value) {
		if (bottomMaxHeap.size() == 0 || value <= bottomMaxHeap.peek()) {
			if (bottomMaxHeap.size() == topMinHeap.size()) {
				bottomMaxHeap.add(value);
			} else {
				bottomMaxHeap.add(value);
				topMinHeap.add(bottomMaxHeap.poll());
			}
		} else {
			if (bottomMaxHeap.size() == topMinHeap.size()) {
				topMinHeap.add(value);
				bottomMaxHeap.add(topMinHeap.poll());
			} else {
				topMinHeap.add(value);
			}
		}
	}

	private void remove(int value) {
		if (value <= bottomMaxHeap.peek()) {
			bottomMaxHeap.remove(value);
		} else {
			topMinHeap.remove(value);
		}

		if (topMinHeap.size() > bottomMaxHeap.size()) {
			bottomMaxHeap.add(topMinHeap.poll());
		} else if (bottomMaxHeap.size() > topMinHeap.size() + 1) {
			topMinHeap.add(bottomMaxHeap.poll());
		}
	}

	private double getMid() {
		if (topMinHeap.size() == bottomMaxHeap.size()) {
			return ((topMinHeap.peek() + bottomMaxHeap.peek()) * 1.0) / 2.0;
		}
		return bottomMaxHeap.peek();
	}
}
