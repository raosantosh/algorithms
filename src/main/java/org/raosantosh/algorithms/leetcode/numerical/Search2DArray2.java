package com.yahoo.sample.numerical;

public class Search2DArray2 {

	public static void main(String args[]) {

		int[][] matrix = new int[5][5];
		matrix[0][0] = 1;
		matrix[0][1] = 4;
		matrix[0][2] = 7;
		matrix[0][3] = 11;
		matrix[0][4] = 15;
		matrix[1][0] = 2;
		matrix[1][1] = 5;
		matrix[1][2] = 8;
		matrix[1][3] = 12;
		matrix[1][4] = 19;
		matrix[2][0] = 3;
		matrix[2][1] = 6;
		matrix[2][2] = 9;
		matrix[2][3] = 16;
		matrix[2][4] = 22;
		matrix[3][0] = 10;
		matrix[3][1] = 13;
		matrix[3][2] = 14;
		matrix[3][3] = 17;
		matrix[3][4] = 24;
		matrix[4][0] = 18;
		matrix[4][1] = 21;
		matrix[4][2] = 23;
		matrix[4][3] = 26;
		matrix[4][4] = 30;

		Search2DArray2 array = new Search2DArray2();

//		 System.out.println("20: " + array.searchMatrix(matrix, 20));
//		 System.out.println("5 : " + array.searchMatrix(matrix, 5));

		matrix = new int[1][2];
		matrix[0][0] = 1;
		matrix[0][1] = 1;
//		 System.out.println("1: " + array.searchMatrix(matrix, 1));

		matrix = new int[1][2];
		matrix[0][0] = -1;
		matrix[0][1] = 3;
//		 System.out.println("3: " + array.searchMatrix(matrix, 3));

		matrix = new int[2][2];
		matrix[0][0] = 1;
		matrix[0][1] = 4;
		matrix[1][0] = 2;
		matrix[1][1] = 5;
//		 System.out.println("4: " + array.searchMatrix(matrix, 4));

		matrix = new int[5][5];
		matrix[0][0] = 1;
		matrix[0][1] = 2;
		matrix[0][2] = 3;
		matrix[0][3] = 4;
		matrix[0][4] = 5;
		matrix[1][0] = 6;
		matrix[1][1] = 7;
		matrix[1][2] = 8;
		matrix[1][3] = 9;
		matrix[1][4] = 10;
		matrix[2][0] = 11;
		matrix[2][1] = 12;
		matrix[2][2] = 13;
		matrix[2][3] = 14;
		matrix[2][4] = 15;
		matrix[3][0] = 16;
		matrix[3][1] = 17;
		matrix[3][2] = 18;
		matrix[3][3] = 19;
		matrix[3][4] = 20;
		matrix[4][0] = 21;
		matrix[4][1] = 22;
		matrix[4][2] = 23;
		matrix[4][3] = 24;
		matrix[4][4] = 25;

//		System.out.println("15: " + array.searchMatrix(matrix, 15));

		
		matrix = new int[5][1];
		matrix[0][0] = 1;
		matrix[1][0] = 2;
		matrix[2][0] = 3;
		matrix[3][0] = 4;
		matrix[4][0] = 5;
		
		System.out.println("5: " + array.searchMatrix(matrix, 5));
		
	}

	public boolean searchMatrix(int[][] matrix, int target) {

		if (matrix == null || matrix.length == 0)
			return false;
		return searchRectangle(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, target);
	}

	public boolean searchRectangle(int[][] matrix, int srow, int scol, int erow, int ecol, int target) {

		int midRow = (srow + erow) / 2;
		int midCol = (scol + ecol) / 2;
		
		if (erow < srow || ecol < scol )
			return false;
		
		if (matrix[midRow][midCol] == target)
			return true;

		if (target < matrix[srow][scol] || target > matrix[erow][ecol])
			return false;

		return searchRectangle(matrix, srow, scol, midRow, midCol, target)
				|| searchRectangle(matrix, srow, midCol + 1, midRow, ecol, target)
				|| searchRectangle(matrix, midRow + 1, scol, erow, midCol, target)
				|| searchRectangle(matrix, midRow + 1, midCol + 1, erow, ecol, target);

	}

	public boolean searchMatrixIncorrect(int[][] matrix, int target) {

		if (matrix == null || matrix.length == 0)
			return false;

		return search(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, target);
	}

	private boolean search(int[][] matrix, int srow, int scol, int erow, int ecol, int target) {

		int midRow = (srow + erow) / 2;
		int midCol = (scol + ecol) / 2;

		if (erow < srow || ecol < scol || erow >= matrix.length - 1 || ecol >= matrix[erow].length - 1)
			return false;

		if (matrix[midRow][midCol] == target)
			return true;

		if (srow == erow || scol == ecol) {
			boolean found = false;
			if (scol == ecol)
				found = searchCol(matrix, scol, srow, erow, target);

			if (!found && srow == erow)
				found = searchRow(matrix, srow, scol, ecol, target);

			return found;
		}

		if (target < matrix[midRow][midCol]) {
			return search(matrix, srow, scol, midRow, midCol, target);
		} else {
			return search(matrix, midRow + 1, midCol + 1, erow, ecol, target);
		}
	}

	private boolean searchRow(int[][] matrix, int row, int scol, int ecol, int target) {
		for (int i = 0; i <= ecol; i++) {
			if (matrix[row][i] == target)
				return true;
		}
		return false;
	}

	private boolean searchCol(int[][] matrix, int col, int srow, int erow, int target) {

		for (int i = 0; i <= erow; i++) {
			if (matrix[i][col] == target)
				return true;
		}
		return false;
	}

}
