package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 4/17/18.
 * Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

 Example 1:
 Input: [7, 1, 5, 3, 6, 4]
 Output: 5

 max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 Example 2:
 Input: [7, 6, 4, 3, 1]
 Output: 0

 In this case, no transaction is done, i.e. max profit = 0.
 */
public class Stock1 {
  public int maxProfit(int[] prices) {


    int minSoFar = Integer.MAX_VALUE;
    int maxProfit =0;

    for(int i=0 ; i< prices.length - 1; ++i) {
      minSoFar = Math.min(minSoFar, prices[i]);

      if(prices[i+1] - minSoFar > maxProfit) {
        maxProfit = prices[i+1] - minSoFar;
      }

    }

    return maxProfit;
  }


  public static void main(String argsp[]) {

    Stock1 stock = new Stock1();
    System.out.println(stock.maxProfit(new int[] {7,1,5,3,6,4}));
    System.out.println(stock.maxProfit(new int[] {7,6,5,3,1}));
    System.out.println(stock.maxProfit(new int[] {}));
    System.out.println(stock.maxProfit(new int[] {1,3}));

  }

}
