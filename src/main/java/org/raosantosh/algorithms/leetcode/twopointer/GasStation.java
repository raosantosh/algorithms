package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 4/3/18. There are N gas stations along a circular route, where the amount of gas at station i is
 * gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station
 * (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 * Note: The solution is guaranteed to be unique.
 */
public class GasStation {

  public int canCompleteCircuit(int[] gas, int[] cost) {

    // A-> B -> C
    // If i cannot go A->C, then B->C also not possible

    int remainingGas = 0;
    int startGasStation = 0;

    int total = 0;



    for (int i = 0; i < gas.length; ++i) {
      if(remainingGas <0) {
        startGasStation = i;
        remainingGas = 0;
      }
      remainingGas += gas[i];
      remainingGas -= cost[i];
      total += gas[i] - cost[i];
    }

    if(total >= 0) return startGasStation;
    return -1;
  }
}
