package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 4/17/18.
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 *
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class Stock3 {

  public int maxProfit(int [] prices) {
    int maxProfit[][] = new int[2][prices.length];

    if(prices.length == 0 ) return 0;

    int currentMin = prices.length > 0 ? prices[0] : Integer.MAX_VALUE;
    maxProfit[0][0] = 0;
    maxProfit[1][prices.length-1] = 0;
    int currentMax = 0;

    for(int i=1; i < prices.length; ++i) {
      currentMax = Math.max(prices[i] - currentMin, currentMax);
      currentMin = Math.min(currentMin, prices[i]);
      maxProfit[0][i] = currentMax;
    }


    currentMax = prices[prices.length-1];
    for(int i=prices.length -1; i > 1 ; i--) {
      int currentDiff =  currentMax - prices[i-1];
      maxProfit[1][i-1] = Math.max(currentDiff, maxProfit[1][i]);
      currentMax = Math.max(currentMax, prices[i-1]);
    }

    int maxReturn = 0;
    for(int i=0 ;i < prices.length; ++i) {
      maxReturn = Math.max(maxReturn, maxProfit[0][i] + maxProfit[1][i]);
      //System.out.println(" 0 " + i + " " + maxProfit[0][i]);
      //System.out.println(" 1 " + i + " " + maxProfit[1][i]);
    }


    return maxReturn;
  }

  public int maxProfitBF(int[] prices) {
    int maxProfit[][] = new int[2][prices.length];
    int max = 0;

    for (int i = 0; i < prices.length; ++i) {
      maxProfit[0][i] = maxProfit(maxProfit, prices, 0, i);
      maxProfit[1][i] = maxProfit(maxProfit, prices, i, prices.length);
      max = Math.max(maxProfit[0][i] + maxProfit[1][i], max);
    }

    /*for(int i=0 ; i  <prices.length; ++i) {
      System.out.println(" 0 " + i + " " + maxProfit[0][i]);
      System.out.println(" 1 " + i + " " + maxProfit[1][i]);
    }*/
    return max;
  }

  private int maxProfit(int[][] maxMatrixMemo, int[] prices, int start, int end) {
    int minSoFar = Integer.MAX_VALUE;
    int maxSoFar = 0;

    for (int i = start; i < end && i < prices.length - 1; ++i) {
      minSoFar = Math.min(minSoFar, prices[i]);
      maxSoFar = Math.max(maxSoFar, prices[i + 1] - minSoFar);
    }

    //System.out.println(start + " " + end + " " + maxSoFar);
    return maxSoFar;
  }

  public static void main(String args[]) {
    Stock3 stockFinder = new Stock3();
    System.out.println(stockFinder.maxProfit(new int[]{}));
    System.out.println(stockFinder.maxProfit(new int[]{1}));
    System.out.println(stockFinder.maxProfit(new int[]{1, 2}));
    System.out.println(stockFinder.maxProfit(new int[]{1,2,3}));
    System.out.println(stockFinder.maxProfit(new int[]{1,2,3,4,5}));
    System.out.println(stockFinder.maxProfit(new int[]{3,2,1}));
    System.out.println(stockFinder.maxProfit(new int[]{3, 3, 5,0,0,3,1,4}));
    System.out.println(stockFinder.maxProfit(new int[]{2,1,4,5,2,9,7}));
    System.out.println(stockFinder.maxProfit(new int[] {6,1,3,2,4,7}));

  }
}
