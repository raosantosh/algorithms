package org.raosantosh.algorithms.leetcode.dp;

/**
 * Created by s.rao on 4/8/18.
 * Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:
 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".
 Example 2:
 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromeSubstrings {

  public int countSubstrings(String s) {
    boolean [][] memoMatrix = new boolean[s.length()][s.length()];

    int count = 0;

    for(int i=0 ; i < s.length(); ++i) {
      memoMatrix[i][i] = true;
      count++;
      if((i+1) < s.length() && s.charAt(i) == s.charAt(i+1)) {
        memoMatrix[i][i+1] = true;
        count++;
      }
    }

    for(int length = 2; length < s.length(); ++length) {
      for(int startIndex = 0; startIndex + length < s.length(); ++ startIndex) {
        if(s.charAt(startIndex) == s.charAt(startIndex + length)) {
          memoMatrix[startIndex][startIndex + length] = memoMatrix[startIndex + 1][startIndex + length -1];
        }
        if(memoMatrix[startIndex][startIndex + length])
          count++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    PalindromeSubstrings substr = new PalindromeSubstrings();
    System.out.println(substr.countSubstrings("abc"));
    System.out.println(substr.countSubstrings("aaa"));
    System.out.println(substr.countSubstrings(""));
    System.out.println(substr.countSubstrings("bab"));


  }

}
