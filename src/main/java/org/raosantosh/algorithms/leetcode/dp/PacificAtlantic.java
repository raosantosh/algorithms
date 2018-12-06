package org.raosantosh.algorithms.leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by s.rao on 7/1/18. Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean"
 * touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 *
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 *
 * Note: The order of returned grid coordinates does not matter. Both m and n are less than 150. Example:
 *
 * Given the following 5x5 matrix:
 *
 * Pacific ~   ~   ~   ~   ~ ~  1   2   2   3  (5) * ~  3   2   3  (4) (4) * ~  2   4  (5)  3   1  * ~ (6) (7)  1   4   5  * ~ (5)  1   1   2   4  * *   *   *
 * * Atlantic
 *
 * Return:
 *
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlantic {

  public static class GridIndex {

    int row;
    int column;

    public GridIndex(int r, int c) {
      row = r;
      column = c;
    }

    @Override
    public int hashCode() {
      return (new String(row + "" + column)).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof GridIndex)) {
        return false;
      }
      GridIndex g = (GridIndex) obj;
      if (g.row == row && g.column == column) {
        return true;
      }
      return false;
    }
  }


  public List<int[]> pacificAtlantic(int[][] matrix) {

    Map<GridIndex, Boolean> finalResult = new HashMap<>();

    Map<GridIndex, Boolean> foundAtlantic = new HashMap<>();
    Map<GridIndex, Boolean> foundPacific = new HashMap<>();

    List<Map<GridIndex, Boolean>> memoList = new ArrayList<>();
    memoList.add(foundAtlantic);
    memoList.add(foundPacific);

    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[0].length; ++j) {
        GridIndex current = new GridIndex(i,j);
        if(finalResult.containsKey(current) && finalResult.get(current)) continue;

        findAndUpdate(matrix, i, j, new ArrayList<GridIndex>(), memoList, Integer.MAX_VALUE);
        if (foundAtlantic.get(current) && foundPacific.get(current)) {
          finalResult.put(current, true);
        }
      }
    }

    List<int[]> result = new ArrayList<int[]>();

    for (Map.Entry<GridIndex, Boolean> g : finalResult.entrySet()) {
      if (g.getValue()) {
        result.add(new int[]{g.getKey().row, g.getKey().column});
      }
    }

    return result;
  }

  private int findAndUpdate(int[][] matrix, int row, int column, List<GridIndex> visited, List<Map<GridIndex, Boolean>> memoList, int maxWeight) {
    Map<GridIndex, Boolean> foundAtlantic = memoList.get(0);
    Map<GridIndex, Boolean> foundPacific = memoList.get(1);

    GridIndex current = new GridIndex(row, column);

    if (row >= matrix.length || column >= matrix[0].length) {
      return 1;
    }

    if (row < 0 || column < 0) {
      return 2;
    }

    if (matrix[row][column] > maxWeight) {
      return 0;
    }

    if (foundPacific.containsKey(current)) {
      return (foundPacific.get(current) ? 2 : 0) | (foundAtlantic.get(current) ? 1 : 0);
    }

    if (visited.contains(new GridIndex(row, column))) {
      return 0;
    }
    visited.add(new GridIndex(row, column));

    int a = 0 ;
    int b = 0;
    int c = 0;
    int d = 0;

     a = findAndUpdate(matrix, row + 1, column, visited, memoList, matrix[row][column]);
     if( (a | b| c| d) != 3)
     b = findAndUpdate(matrix, row - 1, column, visited, memoList, matrix[row][column]);
    if( (a | b| c| d) != 3)
     c = findAndUpdate(matrix, row, column - 1, visited, memoList, matrix[row][column]);
    if( (a | b| c| d) != 3)
     d = findAndUpdate(matrix, row, column + 1, visited, memoList, matrix[row][column]);

    int result = a | b | c | d;

    // if(result == 3)
    // System.out.println("row " + row + " col " + column + " values " + a + " , " + b + " , " + c + " , " + d);

    foundAtlantic.put(current, (result & 1) == 1);
    foundPacific.put(current, (result & 2) == 2);

    return result;
  }

  public List<int[]> pacificAtlanticNew(int[][] matrix) {
    List<int[]> result = new ArrayList<int[]>();

    if(matrix.length == 0) return result;

    char pacificMatrix[][] = new char[matrix.length][matrix[0].length];
    char atlanticMatrix[][] = new char[matrix.length][matrix[0].length];

    for(int i=0 ; i < matrix.length; ++i) {
     updateMatrix(matrix, pacificMatrix, 'P', i , 0, Integer.MIN_VALUE);
     updateMatrix(matrix, atlanticMatrix, 'A', i, matrix[0].length-1, Integer.MIN_VALUE);
    }

    for(int i=0 ; i < matrix[0].length; ++i) {
      updateMatrix(matrix, pacificMatrix, 'P', 0, i ,  Integer.MIN_VALUE);
      updateMatrix(matrix, atlanticMatrix, 'A',  matrix.length -1, i,   Integer.MIN_VALUE);
    }

    for(int i = 0 ; i < matrix.length; ++i) {
      for(int j=0; j < matrix[0].length ; ++j) {
        if(pacificMatrix[i][j] == 'P' && atlanticMatrix[i][j] == 'A')
          result.add(new int[]{i,j});
      }
    }

    return result;
  }

  private void updateMatrix(int [][] matrix, char [][] memo, char c, int row, int column, int maxWeight) {

    if( row < 0 || row >= matrix.length || column < 0 || column >= matrix[0].length) return;

    if(memo[row][column] == c) return;

    if(matrix[row][column] < maxWeight)
      return;

    memo[row][column] = c;

    updateMatrix(matrix, memo, c, row + 1, column, matrix[row][column]);
    updateMatrix(matrix, memo, c, row -1, column, matrix[row][column]);
    updateMatrix(matrix, memo, c, row , column +1, matrix[row][column]);
    updateMatrix(matrix, memo, c, row , column-1, matrix[row][column]);
  }


  public static void main(String args[]) {
    /*
      Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic
     */
    /*int matrix[][] = new int[5][5];
    matrix[0][0] = 1;
    matrix[1][0] = 3;
    matrix[2][0] = 2;
    matrix[3][0] = 6;
    matrix[4][0] = 5;

    matrix[0][1] = 2;
    matrix[1][1] = 2;
    matrix[2][1] = 4;
    matrix[3][1] = 7;
    matrix[4][1] = 1;

    matrix[0][2] = 2;
    matrix[1][2] = 3;
    matrix[2][2] = 5;
    matrix[3][2] = 1;
    matrix[4][2] = 1;

    matrix[0][3] = 3;
    matrix[1][3] = 4;
    matrix[2][3] = 3;
    matrix[3][3] = 4;
    matrix[4][3] = 2;

    matrix[0][4] = 5;
    matrix[1][4] = 4;
    matrix[2][4] = 1;
    matrix[3][4] = 5;
    matrix[4][4] = 4;
*/

    int matrix[][] = new int[3][3];

   /* matrix[0][0] = 10;
    matrix[1][0] = 10;
    matrix[2][0] = 10;

    matrix[0][1] = 10;
    matrix[1][1] = 1;
    matrix[2][1] = 10;

    matrix[0][2] = 10;
    matrix[1][2] = 10;
    matrix[2][2] = 10;*/

    matrix[0][0] = 3;
    matrix[0][1] = 3;
    matrix[0][2] = 3;

    matrix[1][0] = 3;
    matrix[1][1] = 1;
    matrix[1][2] = 3;

    matrix[2][0] = 0;
    matrix[2][1] = 2;
    matrix[2][2] = 4;

    PacificAtlantic p = new PacificAtlantic();
    List<int[]> result = p.pacificAtlanticNew(matrix);

    for (int[] ele : result) {
      System.out.println("row=" + ele[0] + " col=" + ele[1]);
    }
  }
}
