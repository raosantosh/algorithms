package org.raosantosh.algorithms.leetcode.twopointer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by s.rao on 7/3/18.
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 *
 * If there is no non-empty subarray with sum at least K, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1], K = 1
 * Output: 1
 * Example 2:
 *
 * Input: A = [1,2], K = 4
 * Output: -1
 * Example 3:
 *
 * Input: A = [2,-1,2], K = 3
 * Output: 3
 */
public class ShortSubarraySum {

  public int shortestSubarray(int []A ,int K) {


    int [] sums = new int[A.length + 1];

    for(int i =1 ; i <= A.length; ++i) {
      sums[i] += sums[i - 1] + A[i-1];
    }

    Deque<Integer> myQueue = new LinkedList<>();
    int minLengh = Integer.MAX_VALUE;

    for(int i=0; i < sums.length; ++i) {

      while(!myQueue.isEmpty() && sums[i] <= sums[myQueue.getLast()])
        myQueue.removeLast();
      while (!myQueue.isEmpty() && (sums[i] - sums[myQueue.getFirst()]) >= K)
        minLengh = Math.min(minLengh, i - myQueue.removeFirst());

      myQueue.add(i);

    }

    return (minLengh == Integer.MAX_VALUE) ? -1 : minLengh ;

  }


  public int shortestSubarrayXY(int []A ,int K) {

    int minLength = Integer.MAX_VALUE;

    for(int i = A.length -1 ;i >= 0 ; --i) {

      int currentStart = i ;
      int currentSum = A[currentStart];

      while(currentSum < K && currentStart > 0) {
        currentSum += A[--currentStart];
      }

      if(currentSum >= K)
        minLength = Math.min(minLength, (i - currentStart + 1));
    }


    return (minLength == Integer.MAX_VALUE)  ? - 1 : minLength;
  }



  public int shortestSubarrayFail(int []A, int K) {

    int minStart = 0;
    int minEnd = -2;

    int currentStart = 0;
    int currentSum = 0;

    for(int i = 0 ; i < A.length; ++i) {

      int currentNumber = A[i];

      currentSum += currentNumber;

      if(currentSum < K) continue;
      else {
        int currentDistance = i - currentStart ;
        if(currentDistance < (minEnd-minStart) || minEnd == -2) {
          minStart = currentStart;
          minEnd = i;
        }


        currentSum -= A[currentStart++];
        int tempSum = currentSum;
        int tempStart = currentStart;

        while(i >= tempStart) {
          if (tempSum >= K) {
            int distance = (i - tempStart);
            if (distance < (minEnd - minStart)) {
              minStart = tempStart;
              currentStart = tempStart;
              currentSum = tempSum;
              minEnd = i;
            }
          }
          tempSum -= A[tempStart++];
        }
      }

    }

    return (minEnd - minStart + 1);
  }

  public int shortestSubarrayX(int[] A, int K) {

    int currentSum = 0;
    int currentStart = 0;

    int minStart = 0;
    int minEnd = -1;

    for (int i = 0; i < A.length; ++i) {

      if (currentSum + A[i] < K) {
        currentSum += A[i];
      } else {
        currentSum += A[i];

        while (currentStart < i) {
          if ((((i - currentStart) < (minEnd - minStart)) || minEnd == -1 ) && (currentSum >= K) ) {
            minEnd = i + 1;
            minStart = currentStart;
          }
          currentSum -= A[currentStart++];
        }
      }
    }

    return (minEnd - minStart);
  }

  public int shortestSubarrayNew(int[] A, int K) {

    int currentSum = 0;
    int currentStart = 0;

    int minStart = 0;
    int minEnd = -1;

    for (int i = 0; i < A.length; ++i) {

      if (currentSum + A[i] < K) {
        currentSum += A[i];
      } else {
        currentSum += A[i];

        while (currentSum >= K) {
          if (((i - currentStart) < (minEnd - minStart)) || minEnd == -1) {
            minEnd = i + 1;
            minStart = currentStart;
          }
          currentSum -= A[currentStart++];
        }
      }
    }

    return (minEnd - minStart);
  }



  public int shortestSubarrayBF(int[] A, int K) {

    int currentSum = 0;

    int minStart = 0;
    int minEnd = -1;

    for (int i = 0; i < A.length; ++i) {
      currentSum = 0;
      for (int j = i + 1; j <= A.length; ++j) {
        currentSum += A[j - 1];
        if (currentSum >= K) {
          if (((j - i) < (minEnd - minStart)) || minEnd == -1) {
            minEnd = j;
            minStart = i;
          }
          continue;
        }
      }
    }

    return (minEnd - minStart);
  }

  public static void main(String args[]) {

    ShortSubarraySum summer = new ShortSubarraySum();
    int[] num = new int[1];
    num[0] = 1;
    System.out.println(summer.shortestSubarray(arrayFromString("1"), 1));
    System.out.println(summer.shortestSubarray(arrayFromString("1,2"), 4));
    System.out.println(summer.shortestSubarray(arrayFromString("2,-1,2"), 3));

    System.out.println(summer.shortestSubarray(arrayFromString("56,-21,56,35,-9"), 61));
    System.out.println(summer.shortestSubarray(arrayFromString("-28,81,-20,28,-29"), 89));


  }

  public static int[] arrayFromString(String s) {

    String [] splits = s.split(",");

    int [] result = new int[splits.length];

    for(int i = 0; i < splits.length; ++i) {
      result[i] = Integer.valueOf(splits[i]);
    }
    return result;
  }

}
