package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 7/21/18.
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 Example 1:

 Input: "(()"
 Output: 2
 Explanation: The longest valid parentheses substring is "()"
 Example 2:

 Input: ")()())"
 Output: 4
 Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParenthesis {

  public int longestValidParentheses(String s) {

    int maxValue = 0;

    for(int i =0 ; i< s.length() - 1; ++i) {
      int left = 0;
      int right = 0;
      if(maxValue > (s.length() - i)) break;
      for(int j= i ; j < s.length(); ++j) {
        if(s.charAt(j) == '(') left++;
        else right++;

        if(right > left) break;

        if(left == right) maxValue = Math.max(maxValue, j -i + 1);

      }

    }

    return maxValue;

  }

  public static void main(String args[]) {
    LongestValidParenthesis longest = new LongestValidParenthesis();
    System.out.println(longest.longestValidParentheses("(()"));
    System.out.println(longest.longestValidParentheses(")()())"));
    System.out.println(longest.longestValidParentheses("(((((())))))"));
  }

}
