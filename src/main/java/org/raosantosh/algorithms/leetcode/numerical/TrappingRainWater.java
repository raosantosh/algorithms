package org.raosantosh.algorithms.leetcode.numerical;


import org.raosantosh.algorithms.leetcode.Common;

public class TrappingRainWater {

	public static void main(String args[]) {
		TrappingRainWater rainWater = new TrappingRainWater();
		System.out.println(rainWater.trap(Common.toIntArray("0,1,0,2,1,0,1,3,2,1,2,1")));
		System.out.println(rainWater.trap(Common.toIntArray("3,0,0,2")));
	}

	public int trap(int[] height) {
		int trappedWater = 0;
		int currentPosition = 0;
		while(currentPosition < height.length) {
			int maxPosition = getHighest(height, currentPosition, height[currentPosition]);
			if(maxPosition == currentPosition) {
				currentPosition++;
				continue;
			}
			int maxValue = Math.min(height[maxPosition], height[currentPosition]);
			while(currentPosition < height.length && maxPosition > currentPosition) {
				if(maxValue > height[currentPosition])
				trappedWater += (maxValue-height[currentPosition]);
				currentPosition++;
			}
		}

		return trappedWater;
	}

	public int getHighest(int[] height, int position, int size) {
		int maxPosition = position;
		int maxValue = 0;
		for (int i = position + 1; i < height.length; ++i) {
			if (height[i] >= size) {
				maxPosition = i;
				break;
			}
			if(height[i] > maxValue) {
				maxValue = height[i];
				maxPosition = i;
			}
		}
		return maxPosition;
	}
}
