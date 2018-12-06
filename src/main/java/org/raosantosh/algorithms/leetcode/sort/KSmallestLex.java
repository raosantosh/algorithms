package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by s.rao on 6/30/18.
 * Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.

 Note: 1 ≤ k ≤ n ≤ 109.

 Example:

 Input:
 n: 13   k: 2

 Output:
 10

 Explanation:
 The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 */
public class KSmallestLex {

  public int findKthNumberSlow(int n, int k) {

    List<String> elements = new ArrayList<String>();

    for(int i =1 ; i <= n; ++i) {
      elements.add(String.valueOf(i));
    }

    Collections.sort(elements);

    return Integer.valueOf(elements.get(k-1));
  }

  public int findMyKth(int n, int k) {

    int result = 1;
    int pending = k-1;

    while (pending > 0) {
      //System.out.println("k="+pending+"result="+result);
      if((result * 10 ) <= n) {
        result *=10;
      }
      else if(result +1 > n) {
        result = result/10;
        result++;
      }
      else {
        result++;
        while(result % 10 == 0)
          result = result/10;
        //result ++;
      }
      pending--;
    }


    return result;
  }

  public int findKthNumber(int n, int k) {

    // 112 15
    // starting with 0, then 1, then 2, then 3 .. so on
    //

    int result = 1;
    --k;

    while (k > 0) {

      long step = 0;
      long first = result;
      long last = result + 1;

      while( first <=n) {
        step += Math.min(n + 1, last) - first;
        first *=10;
        last *=10;
      }

      if(step <= k) {
        ++result;
         k -=step;
      } else {
        result *=10;
        --k;
      }
    }

    return result;
  }

  public static void main(String args[]) {
    KSmallestLex finder = new KSmallestLex();

    //System.out.println(finder.findMyKth(66,65));
    System.out.println(finder.findMyKth(681692778,351251360));
    System.out.println(finder.findKthNumber(681692778,351251360));
    //416126219
  }

}
