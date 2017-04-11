package com.yahoo.sample.string;

import java.util.List;

import com.yahoo.sample.Common;

public class LongestDictionary {
	
	public static void main(String args[]) {
		LongestDictionary dictionary = new LongestDictionary();
		
		System.out.println(dictionary.findLongestWord("abpcplea", Common.toStringList("abpcp,ale,apple,monkey,plea")));
		
	}

	public String findLongestWord(String s, List<String> d) {

		String result = "";
		for (String myString : d) {
			int distance = findDistance(myString, s);
			if(distance > result.length()) {
				result = myString;
			} else if(distance == result.length()) {
				if(result.compareTo(myString) > 0) {
					result = myString;
				}
			}
		}

		return result;
	}

	private int findDistance(String left, String right) {

		int nextPosition = 0;

		for (int i = 0; i < right.length(); ++i) {
			if (right.charAt(i) == left.charAt(nextPosition)) {
				nextPosition++;
				if (nextPosition == left.length()) {
					return left.length();
				}
			}
		}

		return 0;
	}

}
