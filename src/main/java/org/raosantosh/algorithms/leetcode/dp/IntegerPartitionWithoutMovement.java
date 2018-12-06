package org.raosantosh.algorithms.leetcode.dp;

/**
 * Created by s.rao on 9/29/18.
 */
public class IntegerPartitionWithoutMovement {

  public int partition(int nums[], int K) {

    int minSum = Integer.MAX_VALUE;

    int memoResults[][] = new int [K] [nums.length];



    int runningSum[] = new int[nums.length];
    runningSum[0] = nums[0];

    for(int i=1; i < nums.length; ++i) {
      runningSum[i]= runningSum[i-1] + nums[i];
    }

    for(int i=0;i < nums.length; ++i) {
       memoResults[0][i] = runningSum[i];
    }

    for(int i=0; i < K; ++i) {
      memoResults[i][0] = 0;
    }

    for(int i=1; i < K; ++i) {
      for(int j=1; j < nums.length; ++j) {
        memoResults[i][j] = Integer.MAX_VALUE;
        for(int m= 0 ; m < i; ++m) {
          memoResults[i][j] = Math.min(memoResults[i][j], memoResults[i-1][m] + ( runningSum[j] - runningSum[m+1]));
        }

      }
    }


    return memoResults[K-1][nums.length-1];
  }

  public static void main(String args[]) {

    IntegerPartitionWithoutMovement partition = new IntegerPartitionWithoutMovement();
    System.out.println(partition.partition(fromString("1,2,3,5"), 2));

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
