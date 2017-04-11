package com.yahoo.sample.summary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MergeInterval {
	
	
	public static void main(String args[]) {
		MergeInterval mergeInterval = new MergeInterval();
		List<Interval> intervals = new ArrayList<>();
//		intervals.add(new Interval(-5, 1));
//		intervals.add(new Interval(0, 5));
//		intervals.add(new Interval(7, 10));
//		intervals.add(new Interval(9, 12));
//		intervals.add(new Interval(15, 20));
		
		intervals.add(new Interval(1, 4));
		intervals.add(new Interval(1, 5));
		System.out.println(mergeInterval.merge(intervals));
	}

	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> result = new ArrayList<>();
		
		Collections.sort(intervals, new Comparator<Interval>() {
			public int compare(Interval left, Interval right) {
				return left.start - right.start;
			}
		});
		

		Interval currentInterval = null;
		for (Interval interval : intervals) {
			System.out.println(interval.start + " end " + interval.end);
			if (currentInterval == null) {
				currentInterval = new Interval(interval.start, interval.end);
			} else if (currentInterval.end >= interval.start) {
				currentInterval.end = Integer.max(interval.end, currentInterval.end);
			} else {
				result.add(currentInterval);
				currentInterval = new Interval(interval.start, interval.end);
			}
		}
		
		if(currentInterval != null)
		result.add(currentInterval);

		return result;
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
			// TODO Auto-generated method stub
			return "Start: " + start + " End: " + end; 
		}
	}
}
