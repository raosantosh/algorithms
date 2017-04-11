package com.yahoo.sample.numerical;

import com.yahoo.sample.Common;

public class MoveZeros {

	public static void main(String args[]) {
		MoveZeros moveZeros = new MoveZeros();
		int nums[] = Common.toIntArray("0,1,0,3,12");
		moveZeros.moveZeros(nums);
		for (int i : nums) {
			System.out.println(i);
		}
		
		nums= Common.toIntArray("1,2,5,6");
		moveZeros.moveZeros(nums);
		for (int i : nums) {
			System.out.println(i);
		}
		
		nums= Common.toIntArray("1,2,5,4,0,0");
		moveZeros.moveZeros(nums);
		for (int i : nums) {
			System.out.println(i);
		}
		
		nums= Common.toIntArray("1,0,7,0,6");
		moveZeros.moveZeros(nums);
		for (int i : nums) {
			System.out.println(i);
		}
	}

	public void moveZeros(int[] nums) {
		int zeroPointer = -1;
		for (int i = 0; i < nums.length; ++i) {
			if(zeroPointer == -1 && nums[i] == 0) zeroPointer = i;
			if (nums[i] != 0 && zeroPointer !=-1) {
				int temp = nums[zeroPointer];
				nums[zeroPointer] = nums[i];
				nums[i] = temp;
				for (int j = zeroPointer; j <= i; j++) {
					if (nums[j] == 0) {
						zeroPointer = j;
						break;
					}
				}
			}
		}
	}

}
