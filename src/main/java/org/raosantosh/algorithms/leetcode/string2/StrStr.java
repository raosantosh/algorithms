package org.raosantosh.algorithms.leetcode.string2;

/**
 * Created by s.rao on 4/7/18.
 * Implement strStr().

 Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

 Example 1:

 Input: haystack = "hello", needle = "ll"
 Output: 2
 Example 2:

 Input: haystack = "aaaaa", needle = "bba"
 Output: -1
 */
public class StrStr {

  public int strStr(String haystack, String needle) {

    if(needle.equals("")) return 0;

    int expectedHashCode = needle.hashCode();

    for(int i=0; i <= haystack.length() - needle.length(); ++i) {
      System.out.println(haystack.substring(i, needle.length() + i));
      if(haystack.substring(i, needle.length() + i).hashCode() == expectedHashCode) {
        return i;
      }
    }


   return -1;
  }

  public static void main(String argss[]) {
    StrStr str = new StrStr();
    System.out.println(str.strStr("hello", "ll"));
    System.out.println(str.strStr("hello", "lo"));
  }

}
