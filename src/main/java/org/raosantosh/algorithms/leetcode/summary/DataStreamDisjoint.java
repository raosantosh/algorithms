package org.raosantosh.algorithms.leetcode.summary;


import java.util.ArrayList;
import java.util.List;

public class DataStreamDisjoint {

	public static void main(String args[]) {

		SummaryRanges summaryRanges = new SummaryRanges();
		summaryRanges.addNum(1);
		System.out.println(summaryRanges.getIntervals());

		summaryRanges.addNum(-1);
		System.out.println(summaryRanges.getIntervals());

		summaryRanges.addNum(3);
		System.out.println(summaryRanges.getIntervals());
		summaryRanges.addNum(2);
		System.out.println(summaryRanges.getIntervals());
		summaryRanges.addNum(4);
		System.out.println(summaryRanges.getIntervals());

		summaryRanges.addNum(6);
		System.out.println(summaryRanges.getIntervals());
		summaryRanges.addNum(5);
		System.out.println(summaryRanges.getIntervals());

		summaryRanges.addNum(100);
		System.out.println(summaryRanges.getIntervals());

		summaryRanges.addNum(101);
		System.out.println(summaryRanges.getIntervals());

		summaryRanges.addNum(8);
		System.out.println(summaryRanges.getIntervals());

		summaryRanges.addNum(10);
		System.out.println(summaryRanges.getIntervals());

		summaryRanges.addNum(9);
		System.out.println(summaryRanges.getIntervals());

	}

	public static class SummaryRanges {

		private List<Interval> intervals = new ArrayList<>();

		public SummaryRanges() {

		}

		public void addNum(int val) {

			if (intervals.size() == 0) {
				Interval inter = new Interval(val, val);

				intervals.add(inter);
				return;
			}

			int index = binarySearch(val, 0, intervals.size() - 1);
			if (index < 0) {
				index = 0;
			}

			Interval interval = intervals.get(index);
			// System.out.println("found int: " + interval + " for: "+val);

			// We could make
			// - two intervals to 1
			// - create a new interval
			// - Add to an existing interval

			// In the interval
			if (val >= interval.start && val <= interval.end) {
				return;
			}
			
			if(val < interval.start) {
				if(interval.start -1 == val) {
					interval.start = val;	
				}else {
					Interval inter = new Interval(val, val);
					intervals.add(0, inter);
				}
			}
			// Extend interval
			else if (intervals.size() > index + 1 && intervals.get(index + 1).start - 1 == val) {
				intervals.get(index + 1).start = val;

				if (intervals.get(index).end + 1 == val) {
					intervals.get(index + 1).start = intervals.get(index).start;
					intervals.remove(index);
				}

			} else if (interval.end + 1 == val) {
				interval.end = val;
			} else {

				// Create interval
				Interval inter = new Interval(val, val);

				intervals.add(index + 1, inter);
			}
		}

		public List<Interval> getIntervals() {
			return intervals;
		}

		private int binarySearch(int value, int start, int end) {

			if (start > end) {
				return end;
			}

			int mid = (start + end) / 2;

			if (value >= intervals.get(mid).start && value <= intervals.get(mid).end)
				return mid;

			if (value > intervals.get(mid).start) {
				return binarySearch(value, mid + 1, end);
			}

			return binarySearch(value, start, mid - 1);
		}

	}

	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		public String toString() {
			return "Start:" + start + " End:" + end;
		}
	}
}
