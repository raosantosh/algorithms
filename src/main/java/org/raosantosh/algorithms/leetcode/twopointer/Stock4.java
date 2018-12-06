package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 4/17/18.
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 Credits:
 */
public class Stock4 {
  public int maxProfit(int k, int[] prices) {

    int memoMatrix[][] = new int[k+1][prices.length];

    if(k >= (prices.length/2)) {

      int total = 0;
      for(int i=1; i < prices.length; ++i) {
        total += Math.max(0, prices[i]- prices[i-1]);
      }
      return total;

    }

    for(int transaction=1; transaction <= k ; ++transaction) {
      int maxValue = - prices[0];
      for(int days=1; days < prices.length; ++ days) {
       memoMatrix[transaction][days] = Math.max(memoMatrix[transaction][days-1], prices[days] + maxValue);
       maxValue = Math.max(maxValue, memoMatrix[transaction-1][days-1]  - prices[days]);
       System.out.println("i " + transaction + " j "+ days + " val " + memoMatrix[transaction][days] );
      }
    }




    /*int [] hold = new int[prices.length+1];
    int [] sold = new int[prices.length+1];

    for(int price: prices) {
      for(int i=1; i <= k ; ++i) {
        hold[i] = Math.max(hold[i], sold[i-1] - price);
        sold[i] = Math.max(sold[i], hold[i] + price);
      }
    }*/


    return memoMatrix[k][prices.length-1];
  }


  public static void main(String args[]) {
    Stock4 finder = new Stock4();
    System.out.println(finder.maxProfit(2, new int[]{2,4,1}));
    System.out.println(finder.maxProfit(2, new int[]{3,2,6,5,0,3}));
    System.out.println(finder.maxProfit(2, new int[]{}));
  }


}
