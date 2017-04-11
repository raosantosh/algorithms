package com.yahoo.sample.numerical;

public class NumMatrix {

	public static void main(String args[]) {

		// Given matrix = [
		// [3, 0, 1, 4, 2],
		// [5, 6, 3, 2, 1],
		// [1, 2, 0, 1, 5],
		// [4, 1, 0, 1, 7],
		// [1, 0, 3, 0, 5]
		// ]

		int matrix[][] = new int[5][5];
		matrix[0][0] = 3;
		matrix[0][1] = 0;
		matrix[0][2] = 1;
		matrix[0][3] = 4;
		matrix[0][4] = 2;

		matrix[1][0] = 5;
		matrix[1][1] = 6;
		matrix[1][2] = 3;
		matrix[1][3] = 2;
		matrix[1][4] = 1;

		matrix[2][0] = 1;
		matrix[2][1] = 2;
		matrix[2][2] = 0;
		matrix[2][3] = 1;
		matrix[2][4] = 5;

		matrix[3][0] = 4;
		matrix[3][1] = 1;
		matrix[3][2] = 0;
		matrix[3][3] = 1;
		matrix[3][4] = 7;

		matrix[4][0] = 1;
		matrix[4][1] = 0;
		matrix[4][2] = 3;
		matrix[4][3] = 0;
		matrix[4][4] = 5;

		NumMatrix numMatrix = new NumMatrix(matrix);

		System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
		System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
		System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
	}

	private int[][] sumMatrix;

	public NumMatrix(int[][] matrix) {
		
		if(matrix.length == 0 || matrix[0].length == 0) return;
		
		sumMatrix = new int[matrix.length + 1][matrix[0].length + 1];

		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[i].length; ++j) {
				sumMatrix[i + 1][j + 1] = matrix[i][j] - sumMatrix[i][j] + sumMatrix[i+1][j] + sumMatrix[i][j+1];
			}
		}
	}

	public int sumRegion(int srow, int scol, int erow, int ecol) {
		
		if(sumMatrix == null) return 0;
		
		return sumMatrix[erow+1][ecol+1] - (sumMatrix[srow][ecol+1] + sumMatrix[erow+1][scol]) + sumMatrix[srow][scol];
	}
}
