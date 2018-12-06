package org.raosantosh.algorithms.leetcode.dp;

/**
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */
public class RegularExpression {

  public boolean isMatchWorking(String s, String p) {
    boolean [][] memoMatrix = new boolean[s.length() + 1][p.length() +1];

    memoMatrix[s.length()][p.length()] = true;

    for(int sIndex = s.length()  ; sIndex >= 0; --sIndex) {
      for(int pIndex= p.length() -1 ; pIndex >= 0; --pIndex) {

        boolean match = sIndex < s.length() && p.charAt(pIndex) == s.charAt(sIndex) || p.charAt(pIndex) == '.' ;

        if(pIndex +1 < p.length() && p.charAt(pIndex + 1) == '*') {
            memoMatrix[sIndex] [ pIndex ] = memoMatrix[sIndex] [ pIndex +2]  || match && memoMatrix[sIndex +1][pIndex];
        } else {
          memoMatrix[sIndex][pIndex] = match && memoMatrix[sIndex +1][pIndex + 1];
        }

      }
    }

    return memoMatrix[0][0];
  }

  public boolean isMatch(String s, String p) {
    boolean [][] memoMatrix = new boolean[s.length()+1][p.length()+1];

    memoMatrix[0][0] = true;

    for(int i=0 ; i < p.length(); ++i) {
      if(p.charAt(i ) == '*') {
        memoMatrix[0][i+1] = memoMatrix[0][Math.max(i-1,0)];
      }
      //System.out.println("before s "+i + " value " + memoMatrix[0][i+1]);
    }

    for(int pIndex = 0; pIndex < p.length(); ++pIndex) {
      for(int sIndex = 0; sIndex < s.length(); ++sIndex) {
          boolean isMatched = s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.';
          if(p.charAt(pIndex) == '*') {
            memoMatrix[sIndex +1][pIndex +1] = memoMatrix[sIndex +1][pIndex];
          }
          else if(pIndex +1 < p.length() && p.charAt(pIndex + 1) == '*') {
            memoMatrix[sIndex +1][pIndex +1] = (isMatched && (memoMatrix[sIndex][pIndex+1] || memoMatrix[sIndex][pIndex])) || memoMatrix[sIndex+1][pIndex];
          }else {
            memoMatrix[sIndex+1][pIndex+1] = isMatched && memoMatrix[sIndex][pIndex];
          }
        //System.out.println("s "+s.charAt(sIndex) + " sind "  +  sIndex +  " p "+ p.charAt(pIndex) + " pind "  + pIndex + " value "+memoMatrix[sIndex + 1][ pIndex + 1]);
      }

    }

    return memoMatrix[s.length()][p.length()];
  }



  public boolean isMatchNotFixed(String s, String p) {
    boolean[][] memoMatrix = new boolean[s.length() + 1][p.length() + 1];

    memoMatrix[0][0] = true;

    for (int i=0; i < p.length(); i++) {
      memoMatrix[0][i+1] = p.charAt(i) == '*' ? memoMatrix[0][Math.max(i - 1, 0)] : false;
      System.out.println("before s "+i + " value " + memoMatrix[0][i+1]);
    }

    for (int pIndex = 0; pIndex < p.length(); ++pIndex) {
      for (int sIndex = 0; sIndex < s.length(); ++sIndex) {

        if (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.')
          memoMatrix[sIndex + 1][pIndex + 1] = memoMatrix[sIndex][pIndex];

        if (pIndex +1 < p.length() && p.charAt(pIndex + 1) == '*') {
          memoMatrix[sIndex + 1][pIndex + 1] = memoMatrix[sIndex + 1][pIndex + 1] || memoMatrix[sIndex+1][pIndex];
        }
        else if(p.charAt(pIndex) == '*') {
          memoMatrix[sIndex + 1][pIndex + 1] = memoMatrix[sIndex+1][pIndex-1];
        }
        else if((pIndex -2 >=0) && p.charAt(pIndex -1) == '*' && (s.charAt(sIndex) == p.charAt(pIndex-2) || s.charAt(sIndex) == '.' )) {
          memoMatrix[sIndex+1][pIndex+1] = memoMatrix[sIndex][pIndex-1];
        }
        System.out.println("s "+s.charAt(sIndex) + " p "+ p.charAt(pIndex) + " value "+memoMatrix[sIndex + 1][ pIndex + 1]);
      }
    }

    return memoMatrix[s.length()][p.length()];
  }

  public static void main(String args[]) {
    RegularExpression matcher = new RegularExpression();
    System.out.println(matcher.isMatch("aa", "a"));
    System.out.println(matcher.isMatch("aa", "aa"));
    System.out.println(matcher.isMatch("aac", "aa"));
    System.out.println(matcher.isMatch("aadc", "aa.c"));
    System.out.println(matcher.isMatch("aa", "aa"));
    System.out.println(matcher.isMatch("aabc", "aabc"));
    System.out.println(matcher.isMatch("aabc", "aadc"));
    System.out.println(matcher.isMatch("aabc", "aa.c"));
    System.out.println(matcher.isMatch("aaaa", "aa*a"));
    System.out.println(matcher.isMatch("aac", "aab*c"));
    System.out.println(matcher.isMatch("aacdef", "aab*cdef"));
    System.out.println(matcher.isMatch("aacddef", "aab*cdef"));
    System.out.println(matcher.isMatch("aa", "a*"));

  }
}



