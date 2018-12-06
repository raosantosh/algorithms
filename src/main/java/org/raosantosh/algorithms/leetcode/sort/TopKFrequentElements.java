package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by s.rao on 4/28/18.
 * Given a non-empty array of integers, return the k most frequent elements.

 For example,
 Given [1,1,1,2,2,3] and k = 2, return [1,2].

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 */
public class TopKFrequentElements {

  public List<Integer> topKFrequent(int[] nums, int k) {

    Map<Integer,Integer> countMap = new HashMap<Integer,Integer>();
    Map<Integer, List<Integer>>  countElementsMap = new HashMap<Integer, List<Integer>>();

    for(int i =0 ; i < nums.length; ++i) {
      if(countMap.containsKey(nums[i])) {
        countMap.put(nums[i], countMap.get(nums[i]) +1);
      }else {
        countMap.put(nums[i], 1);
      }
    }

    for(Map.Entry<Integer, Integer> elements: countMap.entrySet()) {

      List<Integer> toAdd =new ArrayList<Integer>();

      if(countElementsMap.containsKey(elements.getValue())) {
        toAdd = countElementsMap.get(elements.getValue());
      }else {
        toAdd = new ArrayList<Integer>();
        countElementsMap.put(elements.getValue(), toAdd);
      }
      toAdd.add(elements.getKey());
    }

    List<Integer> sorted = new ArrayList(new HashSet<Integer>(countMap.values()));
    Collections.sort(sorted, Collections.<Integer>reverseOrder());

    System.out.println(countMap);
    System.out.println(countElementsMap);

    List<Integer> returnList = new ArrayList<Integer>();

    for(int i=0 ;i < sorted.size() && returnList.size() < k ; ++i) {
      int count = sorted.get(i);
      List<Integer> eles = countElementsMap.get(count);
      for(int j=0 ; j < eles.size() && returnList.size() < k; ++j) {
        returnList.add(eles.get(j));
      }
    }

    return returnList;
  }

  public static void main(String args[]) {
    TopKFrequentElements finder = new TopKFrequentElements();
    System.out.println(finder.topKFrequent(new int[] {1,1,1,2,2,3}, 2));
    System.out.println(finder.topKFrequent(new int[] {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6}, 10));
  }



}

