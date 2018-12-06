package org.raosantosh.algorithms.leetcode.string2;

/**
 * Created by s.rao on 3/24/18.
 */
public class AddStrings {

  public String addStrings(String num1, String num2) {

    int len1 = num1.length() - 1;
    int len2 = num2.length() - 1;

    int carry = 0;

    String resultStr = "";
    while (len1 >= 0 || len2 >= 0) {

      int num1Val = 0;
      int num2Val = 0;

      if (len1 >= 0) {
        num1Val = Integer.valueOf(num1.substring(len1, len1 + 1));
      }

      if (len2 >= 0) {
        num2Val = Integer.valueOf(num2.substring(len2, len2 + 1));
      }



      int result = (num1Val + num2Val + carry);
      if (result > 9) {
        carry = result / 10;
        result = result % 10;
      } else {
        carry = 0;
      }

      System.out.println(num1Val + ":" + num2Val + ":" + carry+ ":"+ result);

      resultStr = String.valueOf(result) + resultStr;
      --len1;
      --len2;
    }

    if (carry > 0) {
      resultStr = String.valueOf(carry) + resultStr;
    }

    return resultStr;
  }

  public static void main(String args[]) {

    AddStrings add = new AddStrings();

    System.out.println(add.addStrings("100", "11100"));
    System.out.println(add.addStrings("1", "9"));

  }

}
