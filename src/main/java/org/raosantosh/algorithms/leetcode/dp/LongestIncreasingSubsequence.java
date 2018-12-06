package org.raosantosh.algorithms.leetcode.dp;

import java.util.TreeMap;

/**
 * Created by s.rao on 9/29/18.
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 Example:

 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 Note:

 There may be more than one LIS combination, it is only necessary for you to return the length.
 Your algorithm should run in O(n2) complexity.
 Follow up: Could you improve it to O(n log n) time complexity?

 */
public class LongestIncreasingSubsequence {

  public int lengthOfLIS(int[] nums) {

    if(nums.length == 0) return 0;


    int lengthRuns[] = new int[nums.length ];
    int max = 0;
    TreeMap<Integer,Integer> positionSizeMap = new TreeMap<>();

    for(int i=0; i < lengthRuns.length; ++i) {
      lengthRuns[i] = 1;

      if(positionSizeMap.lowerKey(nums[i]) != null) {
        lengthRuns[i] = positionSizeMap.lowerEntry(nums[i]).getValue() + 1;
      }

      /*for(int j = i-1; j >= 0; --j) {
        if(nums[j] <= nums[i]) lengthRuns[i] = Math.max(lengthRuns[i] , lengthRuns[j] + 1);
      }*/

      positionSizeMap.put(nums[i], lengthRuns[i]);

      if(positionSizeMap.higherKey(nums[i]) != null && positionSizeMap.higherEntry(nums[i]).getValue() <= lengthRuns[i]) {
        positionSizeMap.put(positionSizeMap.higherKey(nums[i]), positionSizeMap.higherEntry(nums[i]).getValue());
        System.out.println(positionSizeMap.higherKey(nums[i]));
      }

      System.out.println(" " + i + " " + nums[i]  + " " + lengthRuns[i]);

      max = Math.max(max, lengthRuns[i]);

    }

    return max;
  }

  public static void main(String args[]) {

    LongestIncreasingSubsequence subsequence = new LongestIncreasingSubsequence();
    System.out.println(subsequence.lengthOfLIS(fromString("10,9,2,5,3,7,101,18")));

  }

  private static int[] fromString(String s) {
    String [] splitString = s.split(",");

    int[] result = new int[splitString.length];

    for(int i=0; i < splitString.length; ++i) {
      result[i] = Integer.valueOf(splitString[i]);
    }


    return result;
  }






}
