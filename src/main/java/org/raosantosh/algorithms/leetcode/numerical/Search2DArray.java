package com.yahoo.sample.numerical;

public class Search2DArray {

	public static void main(String args[]) {
		Search2DArray search2dArray = new Search2DArray();

		int[][] matrix = new int[3][4];

		matrix[0][0] = 1;
		matrix[0][1] = 3;
		matrix[0][2] = 5;
		matrix[0][3] = 7;
		matrix[1][0] = 10;
		matrix[1][1] = 11;
		matrix[1][2] = 16;
		matrix[1][3] = 20;
		matrix[2][0] = 23;
		matrix[2][1] = 30;
		matrix[2][2] = 34;
		matrix[2][3] = 50;

		System.out.println("23 : " + search2dArray.searchMatrix(matrix, 23));
		System.out.println("24 : " + search2dArray.searchMatrix(matrix, 24));

		matrix = new int[1][2];
		matrix[0][0] = 6;
		matrix[0][1] = 8;

		System.out.println("7 : " + search2dArray.searchMatrix(matrix, 7));
		System.out.println("8 : " + search2dArray.searchMatrix(matrix, 8));
	}

	public boolean searchMatrix(int[][] matrix, int target) {

		if (matrix.length == 0 || matrix[0].length == 0)
			return false;

		int length = matrix.length * matrix[0].length;
		return binarySearch(matrix, target, 0, length - 1, matrix[0].length);
	}

	private boolean binarySearch(int[][] matrix, int target, int start, int end, int rowSize) {

		if (start > end)
			return false;

		int mid = (start + end) / 2;

		int[] midInd = getIndexes(mid, rowSize);

		if (matrix[midInd[0]][midInd[1]] == target)
			return true;

		if (target > matrix[midInd[0]][midInd[1]]) {
			return binarySearch(matrix, target, mid + 1, end, rowSize);
		}

		return binarySearch(matrix, target, start, mid - 2, rowSize);

	}

	private int[] getIndexes(int size, int rows) {
		int row = size / rows;
		int col = size % rows;

		int[] res = new int[2];
		res[0] = row;
		res[1] = col;
		return res;
	}
}
