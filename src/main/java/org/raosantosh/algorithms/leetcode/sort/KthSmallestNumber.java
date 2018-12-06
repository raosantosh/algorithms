package org.raosantosh.algorithms.leetcode.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Created by s.rao on 3/24/18.
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 For example,
 Given [3,2,1,5,6,4] and k = 2, return 5.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class KthSmallestNumber {

  public int findKthLargestNonDuplicate(int[] nums, int k) {

    TreeSet<Integer> elements = new TreeSet<Integer>(Collections.<Integer>reverseOrder());

    for(int i=0; i < nums.length ; ++i) {

      if(elements.size() < k || nums[i] > elements.last() ) {
        System.out.println("adding " + nums[i]);
        elements.add(nums[i]);
        if( elements.size() > k)
          elements.pollLast();
      }
    }

    //System.out.println(elements);


    return elements.pollLast();
  }

  public int findKthLargest(int[] nums, int k) {

   int[] sorted = new int [ k +1];
    Arrays.fill(sorted, Integer.MIN_VALUE);


    for(int i=0; i < nums.length ; ++i) {

      int currentIndex = Math.min(i, k-1 );

        while(currentIndex >= 0 && sorted[currentIndex] < nums[i])
            sorted[currentIndex +1 ] = sorted[currentIndex--];
        sorted[currentIndex+1] = nums[i];
    }

    int returnIndex = Math.min(k-1, nums.length-1);


    return sorted[returnIndex];
  }

  public static void main(String args[]) {
    KthSmallestNumber finder = new KthSmallestNumber();
    /*System.out.println(finder.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    System.out.println(finder.findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    System.out.println(finder.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));*/
    System.out.println(finder.findKthLargest(new int[]{-1,2,0},3));

  }

}
