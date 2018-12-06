package org.raosantosh.algorithms.leetcode.dp;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s
 * is 1000.
 *
 * Example 1: Input:
 *
 * "bbbab" Output: 4 One possible longest palindromic subsequence is "bbbb".
 */
public class PalindromeSubsequence {

  public int longestPalindromeSubseq(String s) {

    int[][] memoMatrix = new int[s.length()][s.length()];

    for (int i = 0; i < s.length(); ++i) {
      memoMatrix[i][i] = 1;
    }

    /*for (int length = s.length() - 1; length >= 0; --length) {
      for (int startIndex = length + 1; startIndex < s.length(); ++startIndex) {
        if (s.charAt(startIndex) == s.charAt(length)) {
          memoMatrix[length][startIndex] = memoMatrix[length + 1][startIndex - 1] + 2;
        } else {
          memoMatrix[length][startIndex] = Math
              .max(memoMatrix[length + 1][startIndex], memoMatrix[length][startIndex - 1]);
        }
      }
    }
*/
    for(int length = 1; length < s.length(); ++ length) {
      for(int startIndex = length - 1 ; startIndex >= 0; -- startIndex) {
        if(s.charAt(startIndex) == s.charAt(length)) {
          memoMatrix[startIndex][length] = memoMatrix[startIndex + 1] [length - 1] + 2;
        }else {
          memoMatrix[startIndex][length] = Math.max(memoMatrix[startIndex][length -1], memoMatrix[startIndex + 1][length]);
        }
      }
    }

    return memoMatrix[0][s.length() - 1];
  }

  public static void main(String args[]) {
    PalindromeSubsequence subsequence = new PalindromeSubsequence();
    System.out.println(subsequence.longestPalindromeSubseq("bbbab"));
    System.out.println(subsequence.longestPalindromeSubseq("cbbd"));
  }

}
