package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s.rao on 4/25/18.
 */
public class OveralappingInterval {

  public static class Interval implements Comparable<Interval> {

    int start;
    int end;

    public Interval() {

    }

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Interval o) {
      return o.start - start;
    }

    @Override
    public String toString() {
      return "s: " + start + " e: " + end;
    }
  }

  public List<Interval> returnOveralp(List<Interval> interval1, List<Interval> interval2) {
    List<Interval> returnInterval = new ArrayList<Interval>();

    int interval1Index = 0;
    int interval2Index = 0;

    while (interval1Index < interval1.size() && interval2Index < interval2.size()) {

      Interval interval1Current = interval1.get(interval1Index);
      Interval interval2Current = interval2.get(interval2Index);

      if (interval1Current.start > interval2Current.start && interval1Current.start < interval2Current.end
          || interval2Current.start > interval1Current.start && interval2Current.start < interval1Current.end) {
        returnInterval.add(interval1Current);
        returnInterval.add(interval2Current);
      }

      if (interval1Current.end >= interval2Current.end) {
        interval2Index++;
      }

      if (interval1Current.end <= interval2Current.end) {
        interval1Index++;
      }
    }

    return returnInterval;
  }

   public static void main(String args[]) {

    List<Interval> interval1 = new ArrayList<Interval>();
    interval1.add(new Interval(1, 4));
     interval1.add(new Interval(5, 6));
     interval1.add(new Interval(8,10));
     interval1.add(new Interval(10,11));

     List<Interval> interval2 = new ArrayList<Interval>();
     interval2.add(new Interval(2, 3));
     interval2.add(new Interval(4, 5));
     interval2.add(new Interval(7,11));

     OveralappingInterval overlapper = new OveralappingInterval();

     System.out.println(overlapper.returnOveralp(interval1, interval2));

   }

}
