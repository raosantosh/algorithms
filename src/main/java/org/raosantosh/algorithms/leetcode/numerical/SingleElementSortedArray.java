package com.yahoo.sample.numerical;

import java.util.HashSet;
import java.util.Set;

import com.yahoo.sample.Common;

public class SingleElementSortedArray {

	public static void main(String args[]) {
		SingleElementSortedArray single = new SingleElementSortedArray();
		
//		System.out.println(single.singleNumber(Common.toIntArray("1,2,2,3,3")));
//		System.out.println(single.singleNumber(Common.toIntArray("2,2,4,3,3")));
//		System.out.println(single.singleNumber(Common.toIntArray("2,2,3,3,4")));
//		System.out.println(single.singleNumber(Common.toIntArray("2")));
//		System.out.println(single.singleNumber(new int[0]));
		
//		System.out.println(single.singleNonDuplicate(Common.toIntArray("1,1,2,3,3,4,4,8,8")));
		System.out.println(single.singleNonDuplicate(Common.toIntArray("3,3,7,7,10,11,11")));
		System.out.println(single.singleNonDuplicate(Common.toIntArray("3,3,7,7,10,10,11,11,12")));
		System.out.println(single.singleNonDuplicate(Common.toIntArray("1,3,3,7,7,10,10,11,11")));
	}
	
    public int singleNonDuplicate(int[] nums) {
        
    	int left = 0;
    	int right = nums.length -1;
    	
    	while(left <= right) {
    		 int mid = (left + right) /2;
    		 
    		int midNumber = nums[mid];
    		if(mid + 1 > nums.length - 1 || mid -1 < 0 || left == right) {
    			return midNumber;
    		}
    		
    		if(nums[mid +1] == midNumber) {
    			if(mid % 2 == 0) left = mid + 1;
    			else
    				right = mid -1;
    		}else if(nums[mid -1] == midNumber) {
    			if( (mid - 1) %2 == 0 ) left = mid + 1;
    			else
    				right = mid -1;
    		}else {
    			return midNumber;
    		}
    		
    	}
    	
    	return -1;
    }
	
public int[] singleNumber(int[] nums) {
	
	if(nums.length == 0 ) return new int[0];
	
	Set<Integer> numSet = new HashSet<>();
	
	for(int num: nums) {
		if(numSet.contains(num)) {
			numSet.remove(num);
		}else {
			numSet.add(num);
		}
	}
	
	int[] result = new int[numSet.size()];
	int index = 0;
	
	for(Integer val: numSet) {
		result[index++] = val;
	}
	
	return result;
}
	
	public int singleNumber3(int[] nums) {
		
		int mask = 0;
		
		for(int num: nums) {
			mask ^= num;
		}
		
		return mask;
	}
	
	public int singleNumber2(int[] nums) {
		
		if(nums.length == 0 ) return -1;
		
		Set<Integer> numSet = new HashSet<>();
		
		for(int num: nums) {
			if(numSet.contains(num)) {
				numSet.remove(num);
			}else {
				numSet.add(num);
			}
		}
		
		return numSet.iterator().next();
	}
	

}
