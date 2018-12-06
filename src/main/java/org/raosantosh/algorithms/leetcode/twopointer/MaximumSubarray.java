package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 4/17/18.
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.

 Follow up:

 If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {

  public int maxSubArray(int[] nums) {

    long maxTotal = Integer.MIN_VALUE;
    long currentSum = Integer.MIN_VALUE;

    for(int i=0; i < nums.length; ++i) {
      if((currentSum + nums[i] ) < nums[i]) {
        currentSum = nums[i];
      } else {
        currentSum += nums[i];
      }
      maxTotal = Math.max(currentSum, maxTotal);
    }

    return (int)maxTotal;
  }

  public int maxSubArrayDnQ(int []nums) {



    return -1;
  }

  public static void main(String args[]) {
    MaximumSubarray arrayFinder = new MaximumSubarray();
    System.out.println(arrayFinder.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    System.out.println(arrayFinder.maxSubArray(new int[] {-6,-5}));
    System.out.println(arrayFinder.maxSubArray(new int[] {1}));
    System.out.println(arrayFinder.maxSubArray(new int[] {}));
    System.out.println(arrayFinder.maxSubArray(new int[] {1,2}));
  }

}
