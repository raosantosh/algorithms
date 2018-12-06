package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by s.rao on 5/6/18. A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now
 * suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by
 * these buildings collectively (Figure B).
 *
 * Buildings  Skyline Contour The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x
 * coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX,
 * and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key
 * point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the
 * termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline
 * contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000]. The input list is already sorted in ascending order by the left x
 * position Li. The output list must be sorted by the x position. There must be no consecutive horizontal lines of equal height in the output skyline. For
 * instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such:
 * [...[2 3], [4 5], [12 7], ...]
 */

public class SkyLineProblem {

  public static class Item implements Comparable<Item> {

    int x;
    int height;
    boolean isStart;

    public Item(int x, int height, boolean isStart) {
      this.x = x;
      this.height = height;
      this.isStart = isStart;
    }

    @Override
    public int compareTo(Item o) {
      if (x != o.x) return x - o.x;

      if (isStart && o.isStart) return o.height - height;

      if (!isStart && !o.isStart) return height - o.height;

      if (o.isStart) return -1;

      return 1;
    }
  }


  public List<int[]> getSkyline(int[][] buildings) {

    List<Item> allItems = new ArrayList<>();
    List<int[]> result = new ArrayList<>();

    for (int i = 0; i < buildings.length; ++i) {
      allItems.add(new Item(buildings[i][0], buildings[i][2], true));
      allItems.add(new Item(buildings[i][1], buildings[i][2], false));
    }

    Collections.sort(allItems);

    PriorityQueue<Integer> heapItems = new PriorityQueue<>(Collections.<Integer>reverseOrder());

    for (int i = 0; i < allItems.size(); ++i) {
      Item currentItem = allItems.get(i);

      if (currentItem.isStart) {

        if (heapItems.isEmpty() || currentItem.height > heapItems.peek()) {
          result.add(new int[]{currentItem.x, currentItem.height});
        }

        heapItems.add(currentItem.height);
      } else {
        heapItems.remove(currentItem.height);

        if (heapItems.isEmpty()) {
          result.add(new int[]{currentItem.x, 0});
        } else if (currentItem.height > heapItems.peek()) {
          result.add(new int[]{currentItem.x, heapItems.peek()});
        }
      }
    }

    return result;
  }

  public static void main(String args[]) {
    int[][] buildings = new int[5][3];

    buildings[0][0] = 2;
    buildings[0][1] = 9;
    buildings[0][2] = 10;

    buildings[1][0] = 3;
    buildings[1][1] = 7;
    buildings[1][2] = 15;

    buildings[2][0] = 5;
    buildings[2][1] = 12;
    buildings[2][2] = 12;

    buildings[3][0] = 15;
    buildings[3][1] = 20;
    buildings[3][2] = 10;

    buildings[4][0] = 19;
    buildings[4][1] = 24;
    buildings[4][2] = 8;

    SkyLineProblem skyline = new SkyLineProblem();

    List<int[]> result = skyline.getSkyline(buildings);

    for (int[] item : result) {
      System.out.println(item[0] + " " + item[1]);
    }

    //[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

  }
}
