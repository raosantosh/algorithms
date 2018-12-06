package org.raosantosh.algorithms.leetcode.dynamic;

import java.util.HashMap;
import java.util.Map;

public class CoinExchange {

	int count = 0;

	public int coinChange(int[] coins, int amount) {
		count = 0;

		Map<Integer, Map<Integer, Integer>> amountMinMap = new HashMap<>();

		int exchange = minExchange(coins, coins.length - 1, amount, amountMinMap);

		if (exchange == Integer.MAX_VALUE)
			exchange = -1;

		return exchange;
	}

	private int minExchange(int[] coins, int index, int amount, Map<Integer, Map<Integer, Integer>> amountMinMap) {

		count++;
		if (amountMinMap.containsKey(amount) && amountMinMap.get(amount).containsKey(index))
			return amountMinMap.get(amount).get(index);

		if (amount == 0)
			return 0;

		if (amount < 0 || index < 0)
			return Integer.MAX_VALUE;

		int minWithValue = minExchange(coins, index, amount - coins[index], amountMinMap);
		if (minWithValue != Integer.MAX_VALUE) {
			minWithValue = minWithValue + 1;
		}

		int minVal = Math.min(minWithValue, minExchange(coins, index - 1, amount, amountMinMap));

		if (amountMinMap.containsKey(amount)) {
			amountMinMap.get(amount).put(index, minVal);
		} else {
			Map<Integer, Integer> map = new HashMap<>();
			map.put(index, minVal);
			amountMinMap.put(amount, map);
		}

		return minVal;
	}

	public static void main(String args[]) {
		String anotherVal = "19,28,176,112,30,260,491,128,70,137,253";
		String anotherVal1 = "370,417,408,156,143,434,168,83,177,280,117";
		String anotherVal2 = "3,7,405,436";

		CoinExchange exchange = new CoinExchange();

		System.out.println(
				"Result is " + exchange.coinChange(toIntArray(anotherVal2), 8839) + " and count is " + exchange.count);
		System.out.println(
				"Result is " + exchange.coinChange(toIntArray(anotherVal), 5615) + " and count is " + exchange.count);
		System.out.println(
				"Result is " + exchange.coinChange(toIntArray(anotherVal1), 9953) + " and count is " + exchange.count);

	}

	private static int[] toIntArray(String value) {
		String[] vals = value.split(",");
		int[] result = new int[vals.length];
		int i = 0;
		for (String val : vals) {
			result[i++] = Integer.valueOf(val);
		}
		return result;
	}
}
