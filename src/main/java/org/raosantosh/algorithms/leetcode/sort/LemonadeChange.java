package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s.rao on 7/3/18.
 * At a lemonade stand, each lemonade costs $5.

 Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).

 Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.

 Note that you don't have any change in hand at first.

 Return true if and only if you can provide every customer with correct change.



 Example 1:

 Input: [5,5,5,10,20]
 Output: true
 Explanation:
 From the first 3 customers, we collect three $5 bills in order.
 From the fourth customer, we collect a $10 bill and give back a $5.
 From the fifth customer, we give a $10 bill and a $5 bill.
 Since all customers got correct change, we output true.
 */
public class LemonadeChange {

  public boolean lemonadeChange(int[] bills) {

    int [] counter = new int[3];


    for(int bill: bills) {
      if(bill == 5) {
        counter[0]++;
      }
      else if(bill == 10) {
        if(counter[0] > 0) {
          counter[0] --;
          counter[1] ++;
        } else {
          return false;
        }
      }else if(bill == 20) {
        int changeNeeded = 15;
        if(counter[1] > 0) {
          counter[1] --;
          changeNeeded-=10;
        }

        while(changeNeeded > 0) {
          if(counter[0] > 0) {
            counter[0] -- ;
            changeNeeded-=5;
          } else
          {
            return false;
          }
        }
      }

    }

    return true;

  }

  public static void main(String args[]) {
    LemonadeChange change = new LemonadeChange();
    System.out.println(change.lemonadeChange(new int[] {5,5,5,10,20}));
    System.out.println(change.lemonadeChange(new int[] {5,5,10,10,20}));
  }

}
