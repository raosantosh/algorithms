package org.raosantosh.algorithms.leetcode.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by s.rao on 4/22/18. Given a non-empty array containing only positive integers, find if the array can be
 * partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Note: Each of the array element will not exceed 100. The array size will not exceed 200. Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11]. Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionSetEqualSum {

  public boolean canPartition(int[] nums) {

    Map<Integer,Map<Integer,Boolean>> memoMap = new HashMap<Integer,Map<Integer,Boolean>>();

    int totalSum = 0;
    for (int i = 0; i < nums.length; ++i) {
      totalSum += nums[i];
    }

    if (totalSum % 2 != 0) {
      return false;
    }

    int needed = totalSum / 2;
    Arrays.sort(nums);
    return combinationalSum(0, nums, 0, needed, memoMap);
  }

  boolean combinationalSum(int currentIndex, int[] nums, int currentSum, int targetSum, Map<Integer,Map<Integer,Boolean>> memoMap) {

    if(currentSum == targetSum) return true;

    if(!memoMap.containsKey(currentIndex)) {
      memoMap.put(currentIndex, new HashMap<Integer,Boolean>());
    }

    if(memoMap.get(currentIndex).containsKey(currentSum)) return false;
    else
      memoMap.get(currentIndex).put(currentSum, true);

    if (currentSum > targetSum || currentIndex > nums
        .length - 1) {
      return false;
    }

    return combinationalSum(currentIndex + 1, nums, currentSum + nums[currentIndex], targetSum, memoMap) ||
        combinationalSum(currentIndex + 1, nums, currentSum, targetSum, memoMap);
  }



  public static void main(String args[]) {

    PartitionSetEqualSum partition = new PartitionSetEqualSum();
    System.out.println(partition.canPartition(new int[] {1,5,11,5}));
    System.out.println(partition.canPartition(new int[] {1,2,3,5}));
    System.out.println(partition.canPartition(new int[] {1,2,3,5,3}));
  }


}
