package org.raosantosh.algorithms.leetcode.twopointer;

import java.util.Arrays;

/**
 * Created by s.rao on 5/3/18. You are a professional robber planning to rob houses along a street. Each house has a
 * certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the
 * neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact
 * the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 * money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2] Output: 3 Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because
 * they are adjacent houses. Example 2:
 *
 * Input: [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you
 * can rob = 1 + 3 = 4.
 */
public class HouseRobber2 {

  public int rob(int[] nums) {

    if(nums.length <=1) {
      return robBasic(nums);
    }

    int val1 = robBasic(Arrays.copyOfRange(nums, 0, nums.length-1 ));
    int val2 = robBasic(Arrays.copyOfRange(nums, 1, nums.length ));

    return Math.max(val1, val2);
  }


  public int robBasic(int[] nums) {

    if(nums.length == 0 ) return 0;

    int[] soFar = new int[nums.length];


    for(int i=0 ; i < nums.length ; ++i) {
      if(i == 0 ) {
        soFar[0] = nums[0];
      }else if(i == 1) {
        soFar[1] = Math.max(soFar[0], nums[i]);
      }else {
        soFar[i] = Math.max(soFar[i - 1], nums[i] + soFar[i - 2]);
      }
    }

    return soFar[nums.length-1];
  }

  public static void main(String args[]) {
    HouseRobber2 robber = new HouseRobber2();
    System.out.println(robber.rob(new int[]{2,3,2}));
    System.out.println(robber.rob(new int[]{1,2,3,1}));
    System.out.println(robber.rob(new int[]{0}));
    System.out.println(robber.rob(new int[]{1,2,1}));
    System.out.println(robber.rob(new int[]{1}));
    System.out.println(robber.rob(new int[]{}));
    System.out.println(robber.rob(new int[]{1,2}));
    System.out.println(robber.rob(new int[]{1,2,1,1}));

  }
}
