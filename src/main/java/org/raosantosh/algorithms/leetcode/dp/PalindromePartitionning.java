package org.raosantosh.algorithms.leetcode.dp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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
 "efe"
 [["e","f","e"],["efe"]]
 */
public class PalindromePartitionning {


  private List<List<String>> createPalindormeTable(String s) {
    Map<Integer, List<String>> startEndmap = new HashMap<Integer, List<String>>();
    boolean[][] memoMatrix = new boolean[s.length()][s.length()];

    for (int i = 0; i < s.length(); ++i) {
      memoMatrix[i][i] = true;
      addToMap(startEndmap, s, i, i);
    }

    for (int i = 0; i < s.length() - 1; ++i) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        memoMatrix[i][i + 1] = true;
        memoMatrix[i + 1][i] = true;
        addToMap(startEndmap, s, i, i + 1);
      }
    }

    for (int length = 2; length < s.length(); ++length) {
      for (int startIndex = 0; startIndex + length < s.length(); ++startIndex) {
        int endIndex = startIndex + length;
        if (s.charAt(startIndex) == s.charAt(endIndex)) {
          memoMatrix[startIndex][endIndex] = memoMatrix[startIndex + 1][endIndex - 1];
          System.out.println("st " +startIndex + " ned " + endIndex + " value " + memoMatrix[startIndex][endIndex] );
          if (memoMatrix[startIndex][endIndex]) {
            addToMap(startEndmap, s, startIndex, endIndex);
          }
        }
      }
    }

    System.out.println(startEndmap);

    List<List<String>> allPaths = new ArrayList<List<String>>();
    getAllPaths(s, startEndmap, new ArrayList<String>(), allPaths, 0);
    return allPaths;
  }

  private void addToMap(Map<Integer, List<String>> startEndmap, String s, int startIndex, int endIndex) {
    System.out.println("Adding " + s.substring(startIndex, endIndex + 1));
    List<String> myList = new ArrayList<String>();
    if (startEndmap.containsKey(startIndex)) {
      myList = startEndmap.get(startIndex);
    }
    myList.add(s.substring(startIndex, endIndex + 1));
    startEndmap.put(startIndex, myList);
  }

  private static class ElementWithSize {
    int element;
    int size;
    public ElementWithSize(int element, int size) {
      this.element = element;
      this.size = size;
    }
    public ElementWithSize() {

    }
  }

  private int getMinPaths(Map<Integer, List<String>> startEndMap, int finalSize) {

    Queue<ElementWithSize> endIndexQueue = new ArrayDeque<ElementWithSize>();

    endIndexQueue.add(new ElementWithSize(0, 0));

    int pathLength = -1;

    while (endIndexQueue.size() > 0) {
      ElementWithSize top = endIndexQueue.poll();
      if(startEndMap.containsKey(top.element)) {
        for(String suffix: startEndMap.get(top)) {
          if(top.element + suffix.length() == finalSize) return top.size + 1;
          endIndexQueue.add(new ElementWithSize(top.element + suffix.length(), top.size +1));
        }
      }
    }

    return -1;
  }

  private void getAllPaths(String original, Map<Integer, List<String>> startEndMap, List<String> currentPath,
      List<List<String>> allPaths, int currentIndex) {
    if (currentIndex == original.length()) {
      allPaths.add(currentPath);
      return;
    }

    if (currentIndex > original.length()) {
      return;
    }

    if (startEndMap.containsKey(currentIndex)) {
      for (String suffix : startEndMap.get(currentIndex)) {
        List<String> currentPathCopied = new ArrayList<String>(currentPath);
        currentPathCopied.add(suffix);
        getAllPaths(original, startEndMap, currentPathCopied, allPaths, currentIndex + suffix.length());
      }
    }
  }

  public List<List<String>> partition(String s) {
    return createPalindormeTable(s);
  }

  public static void main(String args[]) {
    PalindromePartitionning partitioner = new PalindromePartitionning();
    System.out.println(partitioner.partition("aab"));
    System.out.println(partitioner.partition("efe"));

  }
}
