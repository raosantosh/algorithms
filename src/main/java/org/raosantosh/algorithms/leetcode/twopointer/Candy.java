package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 7/22/18.
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 */
public class Candy {

  public int candy(int[] ratings) {

    if (ratings.length == 0) {
      return 0;
    }

    int[] sums = new int[ratings.length];

    int result = 0;

    sums[0] = 1;

    for (int i = 0; i < ratings.length - 1; ++i) {
      if (ratings[i + 1] > ratings[i]) {
        sums[i + 1] = sums[i] + 1;
      } else {
        sums[i + 1] = 1;
      }
    }

    for (int i = ratings.length - 1; i > 0; --i) {
      if (ratings[i - 1] > ratings[i]) {
        sums[i - 1] = Math.max(sums[i - 1], sums[i] + 1);
      }
    }

    for (int i = 0; i < sums.length; ++i) {
      result += sums[i];
    }

    return result;
  }

  public static void main(String args[]) {
    int[] nums = new int[3];
    nums[0] = 1;
    nums[1] = 0;
    nums[2] = 2;

    Candy finder = new Candy();

    System.out.println(finder.candy(nums));
  }

}
