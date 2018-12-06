package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s.rao on 7/8/18.
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:

 Input: [5,2,6,1]
 Output: [2,1,1,0]
 Explanation:
 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 */
public class SmallestAfterSelf {

  public List<Integer> countSmaller(int[] nums) {
    List<Integer> result = new ArrayList<>();

    for(int i=0; i < nums.length; ++i) {
      int count = 0;
      for(int j=i+1; j < nums.length; ++j) {
        if(nums[i] > nums[j]) count++;
      }
      result.add(count);
    }

    return result;
  }

  public static void main(String args[]) {

    SmallestAfterSelf counter = new SmallestAfterSelf();
    int [] input = new int[4];
    input[0] = 5;
    input[1] = 2;
    input[2] = 6;
    input[3] = 1;

    List<Integer> result = counter.countSmaller(input);

    for(Integer val: result) {
      System.out.println("val: "+ val);
    }

  }

}
