package com.yahoo.sample.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConcatenationWords {

	public static void main(String args[]) {
		String words = "cat,cats,catsdogcats,dog,dogcatsdog,hippopotamuses,rat,ratcatdogcat";

		ConcatenationWords concatenationWords = new ConcatenationWords();
		System.out.println(concatenationWords.findAllConcatenatedWordsInADict(splitWords(words)));
		System.out.println(concatenationWords.findAllConcatenatedWordsInADict(splitWords("a,b,ab,abc")));
	}

	private static String[] splitWords(String words) {
		return words.split(",");
	}

	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		Arrays.sort(words, new Comparator<String>() {
			public int compare(String a, String b) {
				return a.length() - b.length();
			}
		});

		Set<String> allWords = new HashSet<String>();
		List<String> result = new ArrayList<>();

		for (String word : words) {
			if (word.length() == 0)
				continue;
			if (exists(word, allWords))
				result.add(word);

			allWords.add(word);
		}

		return result;
	}

	private boolean exists(String word, Set<String> dict) {
		if (dict.isEmpty())
			return false;
		boolean[] dp = new boolean[word.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= word.length(); i++) {
			for (int j = 0; j < i; j++) {
				if (!dp[j])
					continue;
				if (dict.contains(word.substring(j, i))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[word.length()];
	}
}
