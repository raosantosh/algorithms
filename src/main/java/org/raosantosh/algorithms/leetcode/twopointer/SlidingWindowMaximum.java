package org.raosantosh.algorithms.leetcode.twopointer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by s.rao on 7/22/18. Given an array nums, there is a sliding window of size k which is moving from the very
 * left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves
 * right by one position. Return the max sliding window.
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3 Output: [3,3,5,5,6,7] Explanation:
 *
 * Window position
 * Max ---------------               ----- [1  3  -1] -3  5  3  6  7       3 1 [3  -1
 * -3] 5  3  6  7       3 1  3 [-1  -3  5] 3  6  7       5 1  3  -1 [-3  5  3] 6  7       5 1  3  -1  -3 [5  3  6] 7
 *   6 1  3  -1  -3  5 [3  6  7]      7 Note: You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty
 * array.
 *
 * Follow up: Could you solve it in linear time?
 */
public class SlidingWindowMaximum {

  public int[] maxSlidingWindow(int[] nums, int k) {

    if(nums.length == 0) return new int[0];

    int result[] = new int[nums.length - k+1];

    LinkedList<Integer> slidingQueue = new LinkedList<>();

    for (int i = 0; i < nums.length; ++i) {

      int current = nums[i];

      if(!slidingQueue.isEmpty() && slidingQueue.peek() <= (i-k)) slidingQueue.poll();

      while (!slidingQueue.isEmpty() && nums[slidingQueue.peekLast()] < current) {
        slidingQueue.removeLast();
      }

      slidingQueue.add(i);
      if(i >=k-1)
      result[i-k+1] = nums[slidingQueue.peek()];
    }

    return result;
  }

  public static void main(String args[]) {
    SlidingWindowMaximum finder = new SlidingWindowMaximum();

    //printArray(finder.maxSlidingWindow(getList("1,3,-1,-3,5,3,6,7"),3));
    printArray(finder.maxSlidingWindow(getList("1,3,1,2,0,5"),3));
  }

  private static void printArray(int nums[]) {
    for(int n: nums) System.out.println(n);
  }

  private static int [] getList(String elements) {

    String ele[] = elements.split(",");
    int nums[] = new int[ele.length];
    int ind = 0;

    for(String e: ele) {
      nums[ind++] = Integer.valueOf(e);
    }

    return nums;
  }

}
