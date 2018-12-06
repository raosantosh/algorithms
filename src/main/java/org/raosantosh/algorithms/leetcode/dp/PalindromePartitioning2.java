package org.raosantosh.algorithms.leetcode.dp;

/**
 * Created by s.rao on 4/7/18.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return
 *
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * "efe"
 * [["e","f","e"],["efe"]]
 */
public class PalindromePartitioning2 {


  private int palindromePartition(String s) {
    boolean[][] memoMatrix = new boolean[s.length()][s.length()];
    int[] minCut = new int[s.length() + 1];

    for (int i = 0; i < s.length(); ++i) {
      memoMatrix[i][i] = true;
    }

    for (int i = 0; i < s.length() - 1; ++i) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        memoMatrix[i][i + 1] = true;
        memoMatrix[i + 1][i] = true;
      }
    }

    for (int length = 2; length <= s.length(); ++length) {
      for (int startIndex = 0; startIndex + length < s.length(); ++startIndex) {
        if (s.charAt(startIndex) == s.charAt(startIndex + length)) {
          memoMatrix[startIndex][startIndex + length] = memoMatrix[startIndex + 1][startIndex + length - 1];
        }
      }
    }

    for (int i = 0; i < s.length(); ++i) {
      int min = i;
      for (int j = 0; j <= i; ++j) {
        if (memoMatrix[j][i]) {
          min = j == 0 ? 0 : Math.min(min, minCut[j] + 1);
        }
      }
      minCut[i + 1] = min;
    }

    return minCut[s.length()];
  }


  private int createPalindormeTable(String s) {
    Map<Integer, Set<Integer>> startEndmap = new HashMap<Integer, Set<Integer>>();
    Map<Integer, Integer> endIndexMinMap = new HashMap<Integer, Integer>();
    boolean[][] memoMatrix = new boolean[s.length()][s.length()];

    for (int i = 0; i < s.length(); ++i) {
      memoMatrix[i][i] = true;
      addToMap(startEndmap, s, i, i);
      endIndexMinMap.put(0, 0);
    }

    for (int i = 0; i < s.length() - 1; ++i) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        memoMatrix[i][i + 1] = true;
        memoMatrix[i + 1][i] = true;
        addToMap(startEndmap, s, i, i + 1);
        int startOfEnd = endIndexMinMap.get(i);
        endIndexMinMap.put(i + 1, startOfEnd);
      } else {
        int startOfEnd = endIndexMinMap.get(i);
        endIndexMinMap.put(i + 1, startOfEnd + 1);
      }
    }

    for (int length = 2; length < s.length(); ++length) {
      for (int startIndex = 0; startIndex + length < s.length(); ++startIndex) {
        int endIndex = startIndex + length;
        if (s.charAt(startIndex) == s.charAt(endIndex)) {
          memoMatrix[startIndex][endIndex] = memoMatrix[startIndex + 1][endIndex - 1];
          System.out.println("st " + startIndex + " ned " + endIndex + " value " + memoMatrix[startIndex][endIndex]);
          if (memoMatrix[startIndex][endIndex]) {
            int startOfEnd = endIndexMinMap.get(startIndex);
            endIndexMinMap.put(endIndex, startOfEnd);
            addToMap(startEndmap, s, startIndex, endIndex);
          } else {
            int startOfEnd = endIndexMinMap.get(startIndex);
            endIndexMinMap.put(endIndex, startOfEnd + 1);
          }
        }
      }
    }

    System.out.println(startEndmap);

    return getMinPaths(startEndmap, s.length());
  }

  private void addToMap(Map<Integer, Set<Integer>> startEndmap, String s, int startIndex, int endIndex) {
    Set<Integer> myList = new TreeSet<Integer>(Collections.reverseOrder());
    if (startEndmap.containsKey(startIndex)) {
      myList = startEndmap.get(startIndex);
    }
    myList.add(endIndex + 1);
    startEndmap.put(startIndex, myList);
  }

  private static class ElementWithSize {

    int element;
    int size;

    public ElementWithSize(int element, int size) {
      this.element = element;
      this.size = size;
    }
  }

  private int getMinPaths(Map<Integer, Set<Integer>> startEndMap, int finalSize) {

    Queue<ElementWithSize> endIndexQueue = new ArrayDeque<ElementWithSize>();

    endIndexQueue.add(new ElementWithSize(0, 0));

    while (endIndexQueue.size() > 0) {
      ElementWithSize top = endIndexQueue.poll();
      if (startEndMap.containsKey(top.element)) {
        for (Integer suffix : startEndMap.get(top.element)) {
          System.out.println("suf is " + suffix);
          if (suffix >= finalSize) {
            return top.size;
          }
          endIndexQueue.add(new ElementWithSize(suffix, top.size + 1));
        }
      }
    }

    return -1;
  }

  public int minCut(String s) {
    return palindromePartition(s);
    //return createPalindormeTable(s);
  }

  public static void main(String args[]) {
    PalindromePartitioning2 partitioner = new PalindromePartitioning2();
    System.out.println(partitioner.minCut(
        "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi"));
    System.out.println(partitioner.minCut("efe"));
    System.out.println(partitioner.minCut("abcbfehe"));
    System.out.println(partitioner.minCut("aab"));
    System.out.println(partitioner.minCut("aabcddef"));
    System.out.println(partitioner.minCut("abcbae"));
    System.out.println(partitioner.minCut("abcccb"));
  }
}

