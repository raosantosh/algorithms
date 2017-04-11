package com.yahoo.sample.numerical;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LonelyPixel2 {
	
	public static void main(String args[]) {
		char [][] picture = new char[4][6];
		
//		[['W', 'B', 'W', 'B', 'B', 'W'],    
//		 ['W', 'B', 'W', 'B', 'B', 'W'],    
//		 ['W', 'B', 'W', 'B', 'B', 'W'],    
//		 ['W', 'W', 'B', 'W', 'B', 'W']] 
		
		picture[0][0] = 'W';
		picture[0][1] = 'B';
		picture[0][2] = 'W';
		picture[0][3] = 'B';
		picture[0][4] = 'B';
		picture[0][5] = 'W';
		picture[1][0] = 'W';
		picture[1][1] = 'B';
		picture[1][2] = 'W';
		picture[1][3] = 'B';
		picture[1][4] = 'B';
		picture[1][5] = 'W';
		picture[2][0] = 'W';
		picture[2][1] = 'B';
		picture[2][2] = 'W';
		picture[2][3] = 'B';
		picture[2][4] = 'B';
		picture[2][5] = 'W';
		picture[3][0] = 'W';
		picture[3][1] = 'W';
		picture[3][2] = 'W';
		picture[3][3] = 'W';
		picture[3][4] = 'B';
		picture[3][5] = 'W';
		
		
		LonelyPixel2 lonelyPixel2 = new LonelyPixel2();
		
		System.out.println(lonelyPixel2.findBlackPixel(picture, 3));
		
	}

	public int findBlackPixel(char[][] picture, int N) {
		int count = 0;

		Map<Integer, Set<Integer>> rowsCountMap = new HashMap<>();
		Map<Integer, Set<Integer>> colCountMap = new HashMap<>();

		for (int row = 0; row < picture.length; ++row) {
			Set<Integer> rowCounts = new HashSet<>();
			for (int column = 0; column < picture[row].length; ++column) {
				if (picture[row][column] == 'B')
					rowCounts.add(column);
			}

			if (rowCounts.size() == N) {
				rowsCountMap.put(row, rowCounts);
			}
		}

		for (int column = 0; column < picture[0].length; ++column) {
			Set<Integer> colCounts = new HashSet<>();
			for (int row = 0; row < picture.length; ++row) {
				if (picture[row][column] == 'B')
					colCounts.add(row);
			}
			
			if (colCounts.size() == N) {
				colCountMap.put(column, colCounts);
			}
		}
		
		for (Map.Entry<Integer, Set<Integer>> rowEntry : rowsCountMap.entrySet()) {
			for (Map.Entry<Integer, Set<Integer>> colEntry : colCountMap.entrySet()) {
				boolean allIsSame = true;
				for (Integer col : colEntry.getValue()) {
					if (!compareRows(picture, rowEntry.getKey(), col)) {
						allIsSame = false;
						break;
					}
				}

				if (allIsSame)
					count++;
			}
		}

		return count;
	}

	private boolean compareRows(char[][] picture, int i, int j) {
		for (int k = 0; k < picture[i].length; k++) {
			if (picture[i][k] != picture[j][k])
				return false;
		}

		return true;
	}

}
