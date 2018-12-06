package org.raosantosh.algorithms.leetcode.sort;

/**
 * Created by s.rao on 7/4/18. Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with
 * the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0] Output: [0,0,1,1,2,2] Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort. First, iterate the array counting number of 0's, 1's, and 2's, then overwrite
 * array with total number of 0's, then 1's and followed by 2's. Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors {

  public void sortColors(int[] nums) {

    int blueCount = 0;
    int redCount = 0;
    int whiteCount = 0;

    for (int num : nums) {
      if (num == 0) {
        redCount++;
      } else if (num == 1) {
        whiteCount++;
      } else if (num == 2) {
        blueCount++;
      }
    }

    int currentPointer = 0;
    while (redCount-- > 0) {
      nums[currentPointer++] = 0;
    }
    while (whiteCount-- > 0) {
      nums[currentPointer++] = 1;
    }
    while (blueCount-- > 0) {
      nums[currentPointer++] = 2;
    }
  }

  public void sortSinglePass(int [] nums) {

    int redPointer = -1;
    int whitePointer = -1;
    int bluePointer = -1;

    for(int i = 0 ; i< nums.length; ++i) {
      if(nums[i] == 0) {
        if(bluePointer >= 0) {
          nums[i] = 2;
          bluePointer++;
        }

        if(whitePointer >=0) {
          nums[++whitePointer] = 1;
        }
        nums[++redPointer] = 0;
      }else if(nums[i] == 1) {
        if(bluePointer >= 0) {
          nums[i] = 2;
          bluePointer++;
        }
        if(whitePointer == -1) {
          whitePointer = redPointer +1;
          nums[whitePointer] = 1;
        } else
        nums[++whitePointer] = 1;
      }else {
        ++bluePointer;
      }
    }
  }

  public static void main(String args[]) {
    int [] nums = new int[6];
    nums[0] = 2;
    nums[1] = 0;
    nums[2] = 2;
    nums[3] = 1;
    nums[4] = 1;
    nums[5] = 0;
    SortColors sorter = new SortColors();

    sorter.sortSinglePass(nums);
    for(int num: nums) System.out.println(num);

  }

}
