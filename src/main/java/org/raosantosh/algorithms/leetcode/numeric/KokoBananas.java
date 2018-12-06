package org.raosantosh.algorithms.leetcode.numeric;

/**
 *
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.

 Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.

 Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

 Return the minimum integer K such that she can eat all the bananas within H hours.



 Example 1:

 Input: piles = [3,6,7,11], H = 8
 Output: 4
 Example 2:

 Input: piles = [30,11,23,4,20], H = 5
 Output: 30
 Example 3:

 Input: piles = [30,11,23,4,20], H = 6
 Output: 23


 Note:

 1 <= piles.length <= 10^4
 piles.length <= H <= 10^9
 1 <= piles[i] <= 10^9

 */
public class KokoBananas {

  public int minEatingSpeed(int[] piles, int H) {

    if(piles.length > H) return -1;

    int maxSpeed = Integer.MIN_VALUE;

    for(int i = 0 ; i < piles.length ; ++i) {
      maxSpeed = Math.max(piles[i], maxSpeed);
    }

    int minSpeed = 1;


    while(minSpeed < maxSpeed) {
      int testSpeed = (minSpeed + maxSpeed ) / 2;

      if(doesWork(testSpeed, piles, H)) {
        maxSpeed = testSpeed;
      } else {
        minSpeed = testSpeed + 1;
      }
    }


    return minSpeed;
  }

  private boolean doesWork(int speed, int[] piles, int H) {

    int currentTotal = 0;

    for(int i =0 ; i < piles.length; ++i) {
      currentTotal += Math.ceil( (piles[i] * 1.0 )/ speed ) ;
      if(currentTotal > H) return false;
    }

    return true;
  }

  public static void main(String args[]) {
    KokoBananas bananas = new KokoBananas();

    System.out.println(bananas.minEatingSpeed(intFromString("3,6,7,11"), 8));
    System.out.println(bananas.minEatingSpeed(intFromString("30,11,23,4,20"), 5));
    System.out.println(bananas.minEatingSpeed(intFromString("30,11,23,4,20"), 6));
  }

  private static int[] intFromString(String s) {

    String[] splitString = s.split(",");
    int[] result = new int[splitString.length];

    for(int i = 0 ; i < splitString.length; ++i) {
      result[i] = Integer.valueOf(splitString[i]);
    }

    return result;
  }


}
