package org.raosantosh.algorithms.leetcode.sort;

import java.util.Stack;

/**
 * Created by s.rao on 9/27/18. Given n non-negative integers representing the histogram's bar height where the width of
 * each bar is 1, find the area of largest rectangle in the histogram.
 *
 *
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 *
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 *
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3] Output: 10
 */
public class LargestRectangle {

  public int largestRectangleArea(int[] heights) {

    if(heights.length ==0) return 0;

    Stack<Integer> heightIndex =new Stack<>();
    int maxHeight = Integer.MIN_VALUE;

    int i = 0;

    while(i < heights.length) {

      if(heightIndex.empty() || heights[i] >= heights[heightIndex.peek()] ) {
        heightIndex.push(i++);
      }else {
        int topIndex = heightIndex.pop();
        int width = heightIndex.isEmpty() ? i : (i-heightIndex.peek() -1);
        int rectange = heights[topIndex] * width;
        maxHeight = Integer.max(rectange, maxHeight);
      }
    }

    while(!heightIndex.isEmpty()) {
      int topIndex = heightIndex.pop();
      int width = heightIndex.isEmpty() ? i : (i-heightIndex.peek() - 1);
      int rectange = heights[topIndex] * width;
      maxHeight = Integer.max(rectange, maxHeight);
    }

    return maxHeight;
  }

  public static void main(String args[]) {
    LargestRectangle rectangle = new LargestRectangle();

    System.out.println(rectangle.largestRectangleArea(intFromString("2,1,6,5,2,3")));
    System.out.println(rectangle.largestRectangleArea(intFromString("1")));
    System.out.println(rectangle.largestRectangleArea(intFromString("2,1,2")));
  }

  private static int[] intFromString(String s) {

    String[] splitString = s.split(",");
    int[] result = new int[splitString.length];

    for (int i = 0; i < splitString.length; ++i) {
      result[i] = Integer.valueOf(splitString[i]);
    }

    return result;
  }


}
