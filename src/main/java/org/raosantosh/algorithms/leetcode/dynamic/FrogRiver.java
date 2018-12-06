package org.raosantosh.algorithms.leetcode.dynamic;

import java.util.HashMap;
import java.util.Map;

public class FrogRiver {

	public static void main(String[] args) {

		FrogRiver frog = new FrogRiver();

		int[] stones = new int[8];
		stones[0] = 0;
		stones[1] = 1;
		stones[2] = 3;
		stones[3] = 5;
		stones[4] = 6;
		stones[5] = 8;
		stones[6] = 12;
		stones[7] = 17;
		System.out.println(frog.canCross(stones));
		stones = new int[5];
		stones[0] = 0;
		stones[1] = 1;
		stones[2] = 3;
		stones[3] = 6;
		stones[4] = 7;
		System.out.println(frog.canCross(stones));
		
	}

	public boolean canCross(int[] stones) {
		Map<Integer, Map<Integer, Boolean>> memoryMap = new HashMap<>();
		return pathExists(stones, stones[0], 1, memoryMap);
	}

	public boolean pathExists(int[] stones, int currentStone, int currentSpeed,
			Map<Integer, Map<Integer, Boolean>> memoryMap) {

		if (currentSpeed <= 0)
			return false;

		if (memoryMap.containsKey(currentStone) && memoryMap.get(currentStone).containsKey(currentSpeed))
			return memoryMap.get(currentStone).get(currentSpeed);

		if (currentStone == stones[stones.length - 1]) {
			return true;
		}

		if (!exists(stones, 0, stones.length - 1, currentStone)) {
			return false;
		}

		boolean result = pathExists(stones, currentStone + currentSpeed, currentSpeed + 1, memoryMap)
				|| pathExists(stones, currentStone + currentSpeed, currentSpeed - 1, memoryMap)
				|| pathExists(stones, currentStone + currentSpeed, currentSpeed, memoryMap);

		Map<Integer, Boolean> currentSpeedMap = new HashMap<>();
		if (memoryMap.containsKey(currentStone)) {
			currentSpeedMap = memoryMap.get(currentStone);
		}

		currentSpeedMap.put(currentSpeed, result);
		memoryMap.put(currentStone, currentSpeedMap);

		return result;
	}

	public boolean exists(int[] stones, int start, int end, int element) {

		if (start > end)
			return false;

		int mid = (start + end) / 2;

		if (element == stones[mid]) {
			return true;
		}

		if (element < stones[mid]) {
			return exists(stones, start, mid - 1, element);
		}

		return exists(stones, mid + 1, end, element);
	}
}
