package org.raosantosh.algorithms.leetcode.sort;

/**
 * Created by s.rao on 7/7/18.
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

 Note:
 The number of people is less than 1,100.


 Example

 Input:
 [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

 Output:
 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class ReconstructByHeight {

  public int[][] reconstructQueue(int[][] people) {

    if(people.length == 0) return new int[0][2];

    int[][] result = new int[people.length][2];

    int[][] workPeople = new int[people.length][people[0].length];

    for(int i=0 ; i < people.length; ++i) {
      for(int j=0; j < people[0].length; ++j) {
        workPeople[i][j] = people[i][j];
      }
    }

    for(int i=0; i < people.length; ++i) {
      int  minIndex = getMimimum(workPeople);
      result[i][0] = people[minIndex][0];
      result[i][1] = people[minIndex][1];
    }

    return result;
  }

  private int getMimimum(int [][] people) {

    int [] element = new int[2];
    element[0] = Integer.MAX_VALUE;
    int index = -1;

    for(int i = 0 ; i < people.length; ++i) {
      if(people[i][0] < element[0] && people[i][1] == 0) {
        element[0] = people[i][0];
        element[1] = people[i][1];
        index = i;
      }
    }

    people[index][0] = Integer.MIN_VALUE;
    people[index][1] = Integer.MAX_VALUE;

    for(int i = 0 ; i < people.length; ++i) {
      if(index !=i && people[i][0] <= element[0]) {
        people[i][1] --;
      }
    }

    return index;
  }

  public static void main(String args[]) {

    ReconstructByHeight constructer = new ReconstructByHeight();

    //[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

    int[][] elements = new int[6][2];

    elements[0][0] = 7;
    elements[0][1] = 0;

    elements[1][0] =4;
    elements[1][1] = 4;

    elements[2][0] = 7;
    elements[2][1] = 1;

    elements[3][0] = 5;
    elements[3][1] = 0;

    elements[4][0] = 6;
    elements[4][1] = 1;

    elements[5][0] = 5;
    elements[5][1] = 2;

    int [][] result = constructer.reconstructQueue(elements);

    for(int i=0 ; i < result.length; ++i) {
      System.out.println("i " + result[i][0] + " j " + result[i][1]);
    }



  }

}
