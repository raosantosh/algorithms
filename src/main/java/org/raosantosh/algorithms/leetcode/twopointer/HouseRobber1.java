package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 5/3/18.
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 * money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [1,2,3,1] Output: 4 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you
 * can rob = 1 + 3 = 4. Example 2:
 *
 * Input: [2,7,9,3,1] Output: 12 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money =
 * 1). Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber1 {

  public int rob(int[] nums) {

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
    HouseRobber1 robber = new HouseRobber1();

    System.out.println(robber.rob(new int[] {1,2,3,1}));
    System.out.println(robber.rob(new int[] {2,7,9,3,1}));
    System.out.println(robber.rob(new int[] {2,1}));
  }

}
