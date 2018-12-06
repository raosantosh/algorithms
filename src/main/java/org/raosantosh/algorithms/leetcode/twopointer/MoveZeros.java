package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 5/3/18.
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

 Note:
 You must do this in-place without making a copy of the array.
 Minimize the total number of operations.
 */
public class MoveZeros {

  public void moveZeroes(int[] nums) {

    int currentPointer = 0;

    for(int i=0 ;i < nums.length; ++i) {
      if(nums[i] != 0) {
        nums[currentPointer++] = nums[i];
      }
    }

    while(currentPointer < nums.length) {
      nums[currentPointer++] = 0;
    }

  }

  public static void main(String args[]) {
    MoveZeros mover = new MoveZeros();

    int [] nums = new int[] {0, 1, 0, 3, 12};
    mover.moveZeroes(nums);

    print(nums);
  }

  public static void print(int [] nums) {
    for(int i=0 ; i < nums.length; ++i) {
      System.out.print(" " + nums[i]);
    }
  }

}
