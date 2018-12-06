package org.raosantosh.algorithms.leetcode.string2;

/**
 * Created by s.rao on 3/25/18.
 */
public class ValidPalindrome {

  boolean isPalindrome(String s) {

    String toCheck = s.toLowerCase().replaceAll("[^A-Za-z0-9]", "");

    int startIndex = 0;
    int endIndex = toCheck.length() - 1;

    System.out.println(toCheck);

    while(startIndex < endIndex) {
      if(toCheck.charAt(startIndex ++ ) != toCheck.charAt(endIndex--)) {
        System.out.println(startIndex + " char is " + endIndex);
        return false;
      }
    }

    return true;
  }

  public static void main(String args[]) {
    ValidPalindrome palindromeChecker = new ValidPalindrome();
    System.out.println(palindromeChecker.isPalindrome("A man, a plan, a canal: Panama"));
    System.out.println(palindromeChecker.isPalindrome("race a car"));
  }

}
