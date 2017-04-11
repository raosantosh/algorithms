package com.yahoo.sample.dynamic;

import java.util.HashMap;
import java.util.Map;

public class ClimbingChairs {

	public ClimbingChairs() {
	}

	public static void main(String args[]) {
		ClimbingChairs chairs = new ClimbingChairs();

		System.out.println(chairs.climbStairs(4));
	}

	public int climbStairs(int n) {
		Map<Integer, Integer> stepCountMap = new HashMap<>();
		return climb(n, stepCountMap);

	}

	public int climb(int n, Map<Integer, Integer> stepCountMap) {

		if (stepCountMap.containsKey(n))
			return stepCountMap.get(n);

		if (n == 0)
			return 1;
		if (n < 0)
			return 0;

		int total = climb(n - 1, stepCountMap) + climb(n - 2, stepCountMap);
		stepCountMap.put(n, total);

		return total;
	}
}
