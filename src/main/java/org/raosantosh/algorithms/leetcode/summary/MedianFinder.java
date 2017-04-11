package com.yahoo.sample.summary;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

	public static void main(String args[]) {
		MedianFinder finder = new MedianFinder();
		finder.addNum(5);
		System.out.println(finder.findMedian());
		finder.addNum(4);
		System.out.println(finder.findMedian());
		finder.addNum(1);
		System.out.println(finder.findMedian());
		finder.addNum(10);
		System.out.println(finder.findMedian());
		finder.addNum(11);
		System.out.println(finder.findMedian());
		finder.addNum(12);
		System.out.println(finder.findMedian());
		finder.addNum(12);
		System.out.println(finder.findMedian());
		
		
	}

	private PriorityQueue<Integer> leftSideHeap = new PriorityQueue<>(10, Collections.reverseOrder());
	private PriorityQueue<Integer> rightSideHeap = new PriorityQueue<>(10);

	public MedianFinder() {
		

	}

	public void addNum(int num) {
		if (leftSideHeap.size() == 0) {
			leftSideHeap.add(num);
		} else if(leftSideHeap.size() == rightSideHeap.size()) {
			if(num < leftSideHeap.peek()) {
				leftSideHeap.add(num);
			}else {
				rightSideHeap.add(num);
				leftSideHeap.add(rightSideHeap.poll());
			}
		} else {
			if(num < leftSideHeap.peek()) {
				rightSideHeap.add(leftSideHeap.poll());
				leftSideHeap.add(num);
			}else {
				rightSideHeap.add(num);
			}
		}
	}

	public double findMedian() {
		
		if (leftSideHeap.size() == 0)
			return 0;

		if (leftSideHeap.size() == rightSideHeap.size()) {
			return (leftSideHeap.peek() + rightSideHeap.peek()) / 2.0;
		}
		return leftSideHeap.peek();
	}
}
