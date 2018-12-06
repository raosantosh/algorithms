package org.raosantosh.algorithms.leetcode.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Permutation {

	public static void main(String args[]) {
		Permutation permutation = new Permutation();
		List<Integer> elements = new ArrayList<>();
		elements.add(1);
		elements.add(10);
		elements.add(7);
		elements.add(6);

		System.out.println(permutation.createPermutations(elements));
	}

	public List<List<Integer>> createPermutations(List<Integer> elements) {
		List<List<Integer>> result = new ArrayList<>();
		permute(new ArrayList<>(), elements, result);
		return result;
	}

	private void permute(List<Integer> currentElements, List<Integer> elements, List<List<Integer>> result) {
		
		if(currentElements.size() == (elements.size() -1)) {
			int finalElement = minus(currentElements, elements).get(0);
			List<Integer> permutation = new ArrayList<>(currentElements);
			permutation.add(finalElement);
			result.add(permutation);
			return;
		}
		
		List<Integer> pendingElements = minus(currentElements, elements);
		for(int pending: pendingElements) {
			List<Integer> current = new ArrayList<>(currentElements);
			current.add(pending);
			permute(current, elements, result);
		}
	}
	
	private List<Integer> minus(List<Integer> set, List<Integer> list) {
		List<Integer> minusResult = new ArrayList<>();
		
		for(int val: list) {
			if(!set.contains(val))
				minusResult.add(val);
		}
		
		return minusResult;
	}
}
