package com.yahoo.sample.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class CountTreeNodes {
	
	public static void main(String args[]) {
		System.out.println(Math.pow(2, 0) - 1);
		
//		System.out.println("Detect " + detectCapitalUse("Flag"));
//		System.out.println("Detect " + detectCapitalUse("FLAG"));
//		System.out.println("Detect " + detectCapitalUse("FLaG"));
//		System.out.println("Detect " + detectCapitalUse("flag"));
//		System.out.println("Detect " + detectCapitalUse("fLag"));
		
		int nums[] = new int[8];
//		nums[0] = 0;
//		nums[1] = 0;
//		nums[2] = 1;
//		nums[3] = 1;
//		nums[4] = 1;
//		nums[5] = 1;
//		nums[6] = 0;
//		nums[7] = 0;
//		nums[8] = 0;
//		nums[9] = 0;
		nums[0] = 0;
		nums[1] = 0;
		nums[2] = 1;
		nums[3] = 0;
		nums[4] = 0;
		nums[5] = 0;
		nums[6] = 1;
		nums[7] = 1;
		
		System.out.println("Max length "+ findMaxLength(nums));
		
	}
	
	 public static int findMaxLength(int[] nums) {
	  
		 if(nums.length == 0 )
			 return 0;
		 
		 int[] tempArray = new int[nums.length];
		 for(int i = 0 ;i < nums.length; ++i) {
			 if(nums[i] == 0) {
				 tempArray[i] = -1;
			 }else{
				 tempArray[i] = 1;
			 }
		 }
		 
		 int[] sum = new int[nums.length];
		 sum[0] = tempArray[0];
		 
		 for(int i = 1 ;i < nums.length; ++i) {
			 sum[i] = sum[i - 1] + tempArray[i];
		 }
		 
		 Map<Integer,TreeSet<Integer>> valueMap = new HashMap<>();
		 
		 for(int i = 0 ; i < sum.length ; ++i) {
			 if(valueMap.containsKey(sum[i])) {
				 valueMap.get(sum[i]).add(i);
			 } else {
				 TreeSet<Integer> mySet = new TreeSet<>();
				 mySet.add(i);
				 valueMap.put(sum[i], mySet);
			 }
		 }
		 int maxValue = 0;
		 
		 for(int i = 0 ; i < nums.length ; ++i) {
			 int value = 0;
			 if(i > 0)
			  value = sum[i-1];
			 
			 if(valueMap.containsKey(value)) {
				 TreeSet<Integer> myset = valueMap.get(value);
				 
				 int maxIndex = myset.last();
				 if(maxIndex > i && ( maxIndex - i) > maxValue) {
					 maxValue = ( maxIndex - i) + 1;
				 }
				 
			 }
		 }
		 
		 
		 
//		 for(int i = 0 ; i < nums.length ; ++i) {
//			 int value = 0;
//			 if(i > 0)
//			  value = sum[i-1];
//			 for(int j = i ; j < nums.length; ++j) {
//				 if(sum[j] == value && (j - i) > maxValue)
//					 maxValue = (j-i) + 1;
//			 }
//		 }
		 
		 return maxValue;
	  }
	
    public static boolean detectCapitalUse(String word) {
        
        boolean isFirstCaptial = false;
        boolean nonFirstNonCaptial = true;
        boolean nonFirstCaptial = true;
        
        for(int i = 0 ; i < word.length() ; ++i) {
            if(i == 0) {
            	if(Character.isUpperCase(word.charAt(i))) {
            	isFirstCaptial = true;
            	}
            }else {
            	boolean currentUpperCase = Character.isUpperCase(word.charAt(i));
            	
            	if(currentUpperCase) {
            		if(!(isFirstCaptial && nonFirstCaptial))
            		   return false;
            		nonFirstCaptial = true;
            		nonFirstNonCaptial = false;
            	}else {
            		
            		if(!nonFirstNonCaptial)
            			return false;
            		
            		nonFirstNonCaptial = true;
            		nonFirstCaptial= false;
            	}
            }
            
        }
        return true;
        
    }

	
	 public int countNodes(TreeNode root) {
	        
	        int count = 0 ;
	        while(root != null) {
	            root = root.left;
	            count ++;
	        }
	        
	        return (int)Math.pow(2, count);
	    }
	 
	 public static class TreeNode {
		    int val;
		    TreeNode left;
		    TreeNode right;
		    TreeNode(int x) { val = x; }
		}
	 
	 public void inorder(TreeNode node, List<Integer> nodeList) {
		   if(node.left != null) {
			   inorder(node.left, nodeList);
		   }
		   nodeList.add(node.val);
		   if(node.right != null) {
			   inorder(node.right, nodeList);
		   }
	 }
}
