package org.raosantosh.algorithms.leetcode.dp;

/**
 * Created by s.rao on 3/24/18.
 */
public class LongestPalindromeSubstring {


  // Palindrom Brute force
  public String longestPalindromeBruteForce(String s) {
    int maxLength = 0;
    String maxString = "";

    for (int i = 0; i < s.length(); ++i) {
      for (int j = 1; (j + i) <= s.length(); ++j) {
        String toCheck = s.substring(i, i + j);
        if (toCheck.length() > maxLength && isPalindrome(toCheck)) {
          maxLength = toCheck.length();
          maxString = toCheck;
        }
      }
    }

    return maxString;
  }

  boolean isPalindrome(String s) {
    //System.out.println(s);
    int startIndex = 0;
    int endIndex = s.length() - 1;

    while (startIndex < endIndex) {
      if (s.charAt(startIndex++) != s.charAt(endIndex--)) {
        return false;
      }
    }

    return true;
  }

  // Brute force ends

  public String longestPalindrome(String s) {

    ///
    boolean memoMatrix[][] = new boolean[s.length() + 1][s.length() + 1];
    int maxLength = 0;
    String result = "";

    for (int i = 0; i < s.length(); ++i) {
      memoMatrix[i][i] = true;
      memoMatrix[0][i] = true;
      memoMatrix[i][0] = true;
      maxLength = 1;
      result = s.substring(i, i+1);
    }



    for (int i = 0; i < s.length() - 1; ++i) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        memoMatrix[i + 1][i + 2] = true;
        memoMatrix[i + 2][i + 1] = true;
       maxLength = 2;
       result = s.substring(i, i+2);
      }
    }



    for (int length = 2  ;  length < s.length() ; ++length) {
      for (int startIndex = 0; startIndex < s.length()  && (startIndex + length) < s.length(); ++startIndex) {
        int endIndex = startIndex + length;
        if (s.charAt(startIndex) == s.charAt(endIndex)) {
          memoMatrix[startIndex + 1][ endIndex + 1] = memoMatrix[startIndex + 2 ][endIndex];
          if((memoMatrix[startIndex + 1][ endIndex + 1]) && length + 1 > maxLength) {
            result = s.substring(startIndex, endIndex+1);
            maxLength = length+1;
          }
        }
      }
    }
   return result;
  }


  public static void main(String args[]) {
    LongestPalindromeSubstring checker = new LongestPalindromeSubstring();
    //System.out.println(checker.longestPalindromeBruteForce("abcbaer"));
    System.out.println(checker.longestPalindrome("abcbaer"));
    System.out.println(checker.longestPalindrome("abcdcba"));
    System.out.println(checker.longestPalindrome("cbbd"));
    System.out.println(checker.longestPalindrome("babad"));
    System.out.println(checker.longestPalindrome("a"));
    System.out.println(checker.longestPalindrome("ccc"));
    System.out.println(checker.longestPalindrome("abcda"));
  }
}
