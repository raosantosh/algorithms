package org.raosantosh.algorithms.leetcode.dp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by s.rao on 6/23/18.
 */
public class CoinExchange {

  static long getWays(long n, long[] c) {

    Map<Long, Long> exchange = new HashMap<Long,Long>();
    exchange.put(0L,0L);

    for(long i=0; i < c.length; ++i) {
      if(!exchange.containsKey(i))
        continue;
      for(int j = 0 ; j < n ; ++j) {
       if(!exchange.containsKey(i + c[j])) {
         exchange.put(i + c[j], exchange.get(i) + 1);
       }else {
         if(exchange.get(i + c[j]) > exchange.get(i) + 1) {
           exchange.put(i + c[j], exchange.get(i) + 1);
         }
       }
      }
    }

    return exchange.get(n);
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] nm = scanner.nextLine().split(" ");

    int n = Integer.parseInt(nm[0]);

    int m = Integer.parseInt(nm[1]);

    long[] c = new long[m];

    String[] cItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < m; i++) {
      long cItem = Long.parseLong(cItems[i]);
      c[i] = cItem;
    }

    // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

    long ways = getWays(n, c);

    bufferedWriter.close();

    scanner.close();
  }

}
