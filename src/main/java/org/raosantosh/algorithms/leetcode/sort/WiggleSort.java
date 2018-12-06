package org.raosantosh.algorithms.leetcode.sort;

import java.util.Arrays;

/**
 * Created by s.rao on 7/7/18.
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example 1:

 Input: nums = [1, 5, 1, 1, 6, 4]
 Output: One possible answer is [1, 4, 1, 5, 1, 6].
 Example 2:

 Input: nums = [1, 3, 2, 2, 3, 1]
 Output: One possible answer is [2, 3, 1, 3, 1, 2].
 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class WiggleSort {

  public void wiggleSort(int[] nums) {

    Arrays.sort(nums);
    int left = (nums.length -1) /2 ;
    int right = nums.length -1;

    int[] result = new int[nums.length];

    for(int i = 0 ; i < result.length;  ++i ) {

      if( (i & 1) == 0 ) result[i] = nums[left--];
      else result[i] = nums[right--];
    }
    System.arraycopy(result, 0, nums,0, result.length);
  }

  public void wiggleSortEqualsSort(int[] nums) {

    Arrays.sort(nums);

    for(int i=1; i < nums.length; ++i) {

      int temp = nums[i];
      nums[i] = nums[i + 1];
      nums[i+1] = temp;
      i+=2;
    }

  }

  public void wiggleSortEquals(int[] nums) {

    for(int i=0 ; i < nums.length -1 ; ++i) {

      if(( i % 2 ) == 0 ) {
        if (nums[i] > nums[i + 1]) {
          int temp = nums[i];
          nums[i] = nums[i + 1];
          nums[i + 1] = temp;
        }
      }else {
        if (nums[i] < nums[i + 1]) {
          int temp = nums[i];
          nums[i] = nums[i + 1];
          nums[i + 1] = temp;
        }
      }
    }
  }

  public static void main(String args[]) {
    int nums[] = new int[6];

    /*nums[0] = 1 ;
    nums[1] = 5 ;
    nums[2] = 1;
    nums[3] = 1;
    nums[4] = 6 ;
    nums[5] = 4;*/

    nums[0] = 1 ;
    nums[1] = 3 ;
    nums[2] = 2;
    nums[3] = 2;
    nums[4] = 3 ;
    nums[5] = 1;

    // 1 1 2 2 3 3
    // 1 2  2 1 3 3
    // 1 2 1 3 2 3

    WiggleSort sorter = new WiggleSort();

    sorter.wiggleSort(nums);
    print(nums);
  }

  public static void print(int[] nums) {
    for(int num: nums) {
      System.out.println("n: " + num);
    }
  }

}
