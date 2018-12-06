package org.raosantosh.algorithms.leetcode.string;


import java.util.ArrayList;
import java.util.List;

public class TextJustification {

	public static void main(String args[]) {

		String text = "This,is,an,example,of,text,justification.";

		TextJustification justification = new TextJustification();

		System.out.println(justification.fullJustify(text.split(","), 16));
		System.out.println(justification.fullJustify("".split(","), 16));
		System.out.println(justification.fullJustify("What,must,be,shall,be.".split(","), 12));

		System.out.println(justification.fullJustify("Listen,to,many,speak,to,a,few.".split(","), 6));
	}

	public List<String> fullJustify(String[] words, int maxWidth) {
		if (words.length == 0)
			return new ArrayList<>();

		List<String> result = new ArrayList<>();

		List<String> currentWords = new ArrayList<>();

		int currentSize = 0;

		for (int i = 0; i < words.length; ++i) {
			String word = words[i];
			if (word.length() + currentSize > maxWidth) {
				result.add(formatString(currentWords, maxWidth));

				currentWords.clear();
				currentSize = 0;
			}

			currentWords.add(word);
			currentSize += word.length() + 1;
		}

		if (currentWords.size() > 0) {
			result.add(formatLastLine(currentWords, maxWidth));
		}

		return result;
	}

	private String formatLastLine(List<String> words, int maxWidth) {
		StringBuffer result = new StringBuffer();
		int currentSize = 0;
		for (String word : words) {
			result.append(word);
			currentSize += word.length();
			if (currentSize < maxWidth) {
				currentSize++;
				result.append(" ");
			}
		}

		for (int i = currentSize; i < maxWidth; ++i) {
			result.append(" ");
		}

		return result.toString();

	}

	private String formatString(List<String> words, int maxWidth) {

		int totalWordSize = 0;
		List<StringBuffer> buffers = new ArrayList<>();

		for (String word : words) {
			totalWordSize += word.length();
			buffers.add(new StringBuffer(word));
		}

		for (int i = 0; i < (maxWidth - totalWordSize); ++i) {
			int indexToUse = 0;
			if (words.size() > 1) {
				indexToUse = (i % (words.size() - 1));
			}
			buffers.get(indexToUse).append(" ");
		}

		StringBuffer result = new StringBuffer();

		for (StringBuffer buff : buffers) {
			result.append(buff.toString());
		}

		return result.toString();
	}
}
