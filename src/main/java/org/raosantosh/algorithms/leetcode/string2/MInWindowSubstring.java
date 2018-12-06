package org.raosantosh.algorithms.leetcode.string2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by s.rao on 7/21/18. Given a string S and a string T, find the minimum window in S which will contain all the
 * characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC" Output: "BANC" Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "". If there is such window,
 * you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MInWindowSubstring {

  public String minWindow(String s, String t) {
    String result = "";
    int minSize = Integer.MAX_VALUE;

    Map<Character, Set<Integer>> tcharMap = new HashMap<>();
    Map<Character, Integer> requiredChars = new HashMap<>();

    for (char c : t.toCharArray()) {
      if(requiredChars.containsKey(c)) {
        requiredChars.put(c, requiredChars.get(c) + 1);
      } else
      requiredChars.put(c, 1);
    }

    int currentStart = 0;

    for (int i = 0; i < s.length(); ++i) {

      if (requiredChars.containsKey(s.charAt(i))) {
        if (!tcharMap.containsKey(s.charAt(i))) {
          tcharMap.put(s.charAt(i), new TreeSet<Integer>());
        }
        tcharMap.get(s.charAt(i)).add(i);
      }

      if(isMatching(tcharMap, requiredChars)) {
        int currentSize = i - currentStart;
        if(currentSize < minSize) {
          result = s.substring(currentStart,i + 1);
          minSize = currentSize;
        }
      }

      while(isMatching(tcharMap, requiredChars)) {
        if(requiredChars.containsKey(s.charAt(currentStart))) {
          Set<Integer> items = tcharMap.get(s.charAt(currentStart));
          items.remove(currentStart);
          if(items.size() ==0) tcharMap.remove(s.charAt(currentStart));
        }

        int currentSize = i - currentStart;
        if(currentSize < minSize) {
          result = s.substring(currentStart,i + 1);
          minSize = currentSize;
        }
        currentStart++;
      }
    }

    return result;
  }

  boolean isMatching(Map<Character, Set<Integer>> current, Map<Character,Integer> required) {

    if(current.size() == required.size()) {
      for(Map.Entry<Character,Integer> elements: required.entrySet()) {
        if(current.get(elements.getKey()).size() < elements.getValue()) return false;
      }
    }else return false;

    return true;
  }

  public static void main(String args[]) {

    MInWindowSubstring finder = new MInWindowSubstring();

    System.out.println(finder.minWindow("ADOBECODEBANC","ABC"));
    System.out.println(finder.minWindow("a","aa"));
    System.out.println(finder.minWindow("ab","b"));
  }

}
