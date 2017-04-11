package com.yahoo.sample.dynamic;

public class NumberIslands {

	public static void main(String args[]) {
		NumberIslands islands = new NumberIslands();

		char[][] grid = new char[4][5];
		grid[0][0] = '1';
		grid[0][1] = '1';
		grid[0][2] = '1';
		grid[0][3] = '1';
		grid[0][4] = '0';

		grid[1][0] = '1';
		grid[1][1] = '1';
		grid[1][2] = '0';
		grid[1][3] = '1';
		grid[1][4] = '0';

		grid[2][0] = '1';
		grid[2][1] = '1';
		grid[2][2] = '0';
		grid[2][3] = '0';
		grid[2][4] = '0';

		grid[3][0] = '0';
		grid[3][1] = '0';
		grid[3][2] = '0';
		grid[3][3] = '0';
		grid[3][4] = '1';

		 System.out.println(islands.numIslands(grid));

		grid = new char[1][7];
		grid[0][0] = '1';
		grid[0][1] = '0';
		grid[0][2] = '1';
		grid[0][3] = '1';
		grid[0][4] = '0';
		grid[0][5] = '1';
		grid[0][6] = '1';

		 System.out.println(islands.numIslands(grid));

		grid = new char[3][3];
		grid[0][0] = '1';
		grid[0][1] = '1';
		grid[0][2] = '1';
		grid[1][0] = '0';
		grid[1][1] = '1';
		grid[1][2] = '0';
		grid[2][0] = '1';
		grid[2][1] = '1';
		grid[2][2] = '1';

		System.out.println(islands.numIslands(grid));
	}

	public int numIslands(char[][] grid) {
		int count = 0;

		for (int row = 0; row < grid.length; ++row) {
			for (int col = 0; col < grid[row].length; ++col) {
//				System.out.println("start row:" + row + " col:" + col + " value : " + grid[row][col]);
				if (grid[row][col] == '1') {
//					System.out.println("from ehre");
					move(grid, ++count, row, col);
				}
			}
		}

		return count;
	}

	private void move(char[][] grid, int num, int row, int col) {
//		System.out.println("row:" + row + " col:" + col);
		grid[row][col] = 'x';
		if ((row +1) < grid.length && grid[row + 1][col] == '1') {
			move(grid, num, row + 1, col);
		}

		if ((col + 1) < grid[row].length && grid[row][col + 1] == '1') {
			move(grid, num, row, col + 1);
		}
		
		if ((row -1) >= 0  && grid[row - 1][col] == '1') {
			move(grid, num, row - 1, col);
		}

		if ((col - 1) >= 0  && grid[row][col - 1] == '1') {
			move(grid, num, row, col - 1);
		}
	}

	public int numIslands1(char[][] grid) {

		int max = 0;
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[i].length; ++j) {
				if (grid[i][j] == '1') {
					boolean hasLeft = false, hasTop = false;

					if (max == 0) {
						max++;
						continue;
					}

					if (i - 1 >= 0 && grid[i - 1][j] == '1') {
						hasLeft = true;
					}

					if (j - 1 >= 0 && grid[i][j - 1] == '1') {
						hasTop = true;
					}

					if (!(hasLeft || hasTop)) {
						System.out.println("i: " + i + " j : " + j);
						max++;
					}
				}
			}
		}

		return max;
	}
}
