package com.yahoo.sample;

import java.util.ArrayList;
import java.util.List;

public class Common {

	public static int[] toIntArray(String value) {
		String[] vals = value.split(",");
		int[] result = new int[vals.length];

		for (int i = 0; i < vals.length; ++i) {
			result[i] = Integer.valueOf(vals[i].trim());
		}

		return result;
	}

	public static List<String> toStringList(String value) {
		String[] vals = value.split(",");
		List<String> result = new ArrayList<>();

		for (String val : vals) {
			result.add(val);
		}

		return result;
	}
	
	public double[] getNums(String value) {
		String[] strNums = value.split(",");
		double[] nums = new double[strNums.length];

		for (int i = 0; i < strNums.length; ++i) {
			nums[i] = Double.valueOf(strNums[i]);
		}

		return nums;
	}
}
