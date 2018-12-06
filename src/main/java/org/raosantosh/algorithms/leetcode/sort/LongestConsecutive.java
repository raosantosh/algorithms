package org.raosantosh.algorithms.leetcode.sort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by s.rao on 9/27/18.
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

 Your algorithm should run in O(n) complexity.

 Example:

 Input: [100, 4, 200, 1, 3, 2]
 Output: 4
 Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutive {

  public int longestConsecutive(int[] nums) {

    int maxConsecutive = 0;

    Set<Integer> set = new HashSet<>();

    for(int num: nums) set.add(num);

    for(int num: nums) {

      int currentConsecutive = 1;
      int currentNum = num;
      while(set.contains(--currentNum)) {
        currentConsecutive++;
      }

      maxConsecutive = Math.max(maxConsecutive,currentConsecutive);
    }

    return maxConsecutive;
  }

  public static void main(String args[]) {

    LongestConsecutive longest = new LongestConsecutive();
    System.out.println(longest.longestConsecutive(intFromString("5,4,200,1,3,2")));

  }

  private static int[] intFromString(String s) {

    String[] splitString = s.split(",");
    int[] result = new int[splitString.length];

    for(int i = 0 ; i < splitString.length; ++i) {
      result[i] = Integer.valueOf(splitString[i]);
    }

    return result;
  }



}
