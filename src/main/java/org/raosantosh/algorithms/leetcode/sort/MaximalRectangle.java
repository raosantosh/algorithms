package org.raosantosh.algorithms.leetcode.sort;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 Example:

 Input:
 [
 ["1","0","1","0","0"],
 ["1","0","1","1","1"],
 ["1","1","1","1","1"],
 ["1","0","0","1","0"]
 ]
 Output: 6
 */
public class MaximalRectangle {

  public int maximalRectangle(char[][] matrix) {

    if(matrix.length == 0 ) return 0;

    int heights[][] = new int[matrix.length][matrix[0].length];
    int maxArea = Integer.MIN_VALUE;


    for(int i = matrix.length -1; i >= 0; --i) {
      for(int j = 0 ; j < matrix[i].length; ++j) {
        if(matrix[i][j] == '0') {
          heights[i][j] =0;
        }else {
          if(i < matrix.length - 1)
          heights[i][j] = heights[i+1][j] + 1;
          else
            heights[i][j] = 1;
        }
      }
    }

    for(int i=0 ; i < heights.length; ++i) {
      maxArea = Math.max(maxArea, getMaxArea(heights[i]));
    }


    return maxArea;

  }

  private int getMaxArea(int [] bars) {

    Stack<Integer> heightStacks = new Stack<>();

    int index = 0;
    int maxArea = Integer.MIN_VALUE;

    while (index < bars.length) {

      if(heightStacks.isEmpty() || bars[heightStacks.peek()] <= bars[index]) {
        heightStacks.push(index++);
      }else {
        int topHeight = bars[heightStacks.pop()];
        int width = heightStacks.isEmpty() ? index : index - heightStacks.peek() - 1;
        maxArea = Math.max(maxArea, topHeight * width);
      }
    }

    while(!heightStacks.isEmpty()) {
      int topHeight = bars[heightStacks.pop()];
      int width = heightStacks.isEmpty() ? index : index - heightStacks.peek() - 1;
      maxArea = Math.max(maxArea, topHeight * width);
    }

    return maxArea;
  }

  public static void main(String args[]) {
    MaximalRectangle rectangle = new MaximalRectangle();
    char array[][] = new char[4][5];
    array[0][0] = '1';
    array[0][1] = '0';
    array[0][2] = '0';
    array[0][3] = '1';
    array[0][4] = '0';

    array[1][0] = '1';
    array[1][1] = '1';
    array[1][2] = '1';
    array[1][3] = '1';
    array[1][4] = '1';

    array[2][0] = '1';
    array[2][1] = '0';
    array[2][2] = '1';
    array[2][3] = '1';
    array[2][4] = '1';

    array[3][0] = '1';
    array[3][1] = '0';
    array[3][2] = '1';
    array[3][3] = '0';
    array[3][4] = '0';

    System.out.println(rectangle.maximalRectangle(array));

  }

}
