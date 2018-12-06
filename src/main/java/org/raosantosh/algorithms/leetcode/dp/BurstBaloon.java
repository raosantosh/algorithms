package org.raosantosh.algorithms.leetcode.dp;

/**
 * Created by s.rao on 9/29/18.
 */
public class BurstBaloon {

  public int maxCoins(int[] nums) {


    int memoResult[][] = new int[nums.length + 1][nums.length + 1];


    for(int i=0 ; i < nums.length; ++i) {
      for(int j=i; j < nums.length;  j++) {

        for(int k=i; k <= j; ++k) {
          int numi = i > 1 ? nums[i-1] : 1;
          int memoK = k +2 > memoResult.length - 1? 1 : memoResult[k+2][j+1];
          memoResult[i+1][j+1] = Math.max(memoResult[i+1][j+1], (numi * nums[j] * nums[k]) + memoResult[i+1][k] + memoK);
        }

      }
    }

    return memoResult[nums.length][nums.length];
  }


  public static void main(String args[]) {

    BurstBaloon baloon = new BurstBaloon();
    System.out.println(baloon.maxCoins(fromString("3,1,5,8")));

  }

  private static int[] fromString(String s) {
    String [] splitString = s.split(",");

    int[] result = new int[splitString.length];

    for(int i=0; i < splitString.length; ++i) {
      result[i] = Integer.valueOf(splitString[i]);
    }


    return result;
  }

}
