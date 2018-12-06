package org.raosantosh.algorithms.leetcode.dp;

/*

Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

 */

public class RemoveKDigits {

  public String removeKdigits(String num, int k) {
    String result = removedigits(num,k);

    int leadingZeroOffset = -1;
    for(int i=0 ; i< result.length(); ++i) {
      if (result.charAt(i) == '0')
        leadingZeroOffset = i;
      else
        break;
    }

    if(leadingZeroOffset == result.length()-1 || result.isEmpty()) return "0";

    return result.substring(leadingZeroOffset+1);

  }
  public String removedigits(String num, int k) {
    if(k <= 0) {
      return num;
    }else if(num.length() == k)
      return "";

    String prefix = num.substring(0, k + 1);

    int minIndex = 0;
    for(int i= 1 ; i < prefix.length(); ++i) {
      if(prefix.charAt(i) < prefix.charAt(minIndex))
        minIndex = i;
    }

    String toContinue = num.substring(minIndex+1);

    String suffix = removedigits(toContinue, k- (minIndex)  );

    return prefix.charAt(minIndex) + suffix;
  }

  public static void main(String args[]) {
    RemoveKDigits remover = new RemoveKDigits();
    System.out.println(remover.removeKdigits("1432219", 3));
    System.out.println(remover.removeKdigits("10200", 1));
    System.out.println(remover.removeKdigits("10", 2));
    System.out.println(remover.removeKdigits("112", 1));
    System.out.println(remover.removeKdigits("4321", 1));
    System.out.println(remover.removeKdigits("43214321", 4));
  }



}
