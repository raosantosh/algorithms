package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s.rao on 4/22/18. Given an array of integers nums and a positive integer k, find whether it's possible to
 * divide this array into k non-empty subsets whose sums are all equal.
 *
 * Example 1: Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4 Output: True Explanation: It's possible to divide it into 4
 * subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 */
public class PartitionKEqualSumSets {

  public boolean canPartitionKSubsetsBF(int[] nums, int k) {

    int totalSum = 0;
    for (int i = 0; i < nums.length; ++i) {
      totalSum += nums[i];
    }

    if (totalSum % k != 0) {
      return false;
    }

    int groupSum = totalSum / k;

    Map<Integer, Map<List<Integer>, Boolean>> memoMap = new HashMap<Integer, Map<List<Integer>, Boolean>>();
    List<Integer> itemSum = new ArrayList<Integer>();
    for (int i = 0; i < k-1; ++i) {
      itemSum.add(0);
    }

    Arrays.sort(nums);

    return combinationSumBF(0, nums, itemSum, groupSum, memoMap);
  }

  public boolean combinationSumBF(int currentIndex, int[] nums, List<Integer> currentSums, int targetSum,
      Map<Integer, Map<List<Integer>, Boolean>> memoMap) {

    if (currentIndex >= nums.length) {
      return false;
    }

    System.out.println("Coming here " + currentIndex + " val "+ currentSums);

    /*for (int i = 0; i < currentSums.size(); ++i) {
      if (currentSums.get(i) > targetSum) {
        return false;
      }
    }*/

    int counter = 0;

    for (; counter < currentSums.size(); ++counter) {
      if (currentSums.get(counter) != targetSum) {
        break;
      }
    }

    if (counter == currentSums.size()) {
      return true;
    }

    if (!memoMap.containsKey(currentIndex)) {
      memoMap.put(currentIndex, new HashMap<List<Integer>, Boolean>());
    }

    if (memoMap.get(currentIndex).containsKey(currentSums)) {
      return false;
    }
    memoMap.get(currentIndex).put(currentSums, false);

    for (int i = 0; i < currentSums.size(); ++i) {

      List<Integer> sums = new ArrayList<Integer>(currentSums);
      if((currentSums.get(i) + nums[currentIndex]) <= targetSum){
        sums.set(i, currentSums.get(i) + nums[currentIndex]);
        if (combinationSumBF(currentIndex + 1, nums, sums, targetSum, memoMap)) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean canPartitionKSubsets(int nums[],int k) {

    int totalSum = 0 ;
    for(int val: nums) totalSum +=val;

    if(totalSum %k != 0 ) return false;

    int targetSum = totalSum/k;

    return canFindSubsetSum(nums, new boolean[nums.length], k , targetSum, 0);
  }

  public boolean canFindSubsetSum(int nums[], boolean currentSet[], int k, int targetSum, int currentSum) {
    //System.out.println("cur "+ currentSum + " k " + k);

    if(k == 1) return true;

    for(int i= 0; i < currentSet.length && (currentSet.length - i ) >= k ; i++) {
      if(currentSet[i]) continue;

      int currentNum = nums[i];

      if(currentNum + currentSum < targetSum) {
        currentSet[i] = true;
        if(canFindSubsetSum(nums, currentSet, k , targetSum, currentSum + currentNum )) return true;
        currentSet[i] = false;
      }

      if(currentNum + currentSum == targetSum) {
        currentSet[i] = true;
        if(canFindSubsetSum(nums, currentSet, k - 1 , targetSum, 0 )) return true;
        currentSet[i] = false;
      }
    }

    return false;
  }

  public static void main(String args[]) {
    PartitionKEqualSumSets partitionKEqualSumSets = new PartitionKEqualSumSets();
    /*System.out.println(partitionKEqualSumSets.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
    System.out.println(partitionKEqualSumSets.canPartitionKSubsets(new int[] {1,5,11,5}, 2));
    System.out.println(partitionKEqualSumSets.canPartitionKSubsets(new int[] {1,2,3,5}, 2));
    System.out.println(partitionKEqualSumSets.canPartitionKSubsets(new int[] {1,2,3,5,3}, 2));
    System.out.println(partitionKEqualSumSets.canPartitionKSubsets(new int[] {4,5,3,2,5,5,5,1,5,5,5,5,5,5,5,5}, 14));*/
    System.out.println(partitionKEqualSumSets.canPartitionKSubsets(new int[] {129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22}, 3));
  }
}