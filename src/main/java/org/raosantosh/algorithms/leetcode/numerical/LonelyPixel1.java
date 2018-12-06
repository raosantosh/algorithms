package org.raosantosh.algorithms.leetcode.numerical;

public class LonelyPixel1 {

	public static void main(String args[]) {

		char[][] board = new char[3][3];

		board[0][0] = 'W';
		board[0][1] = 'W';
		board[0][2] = 'B';
		board[1][0] = 'W';
		board[1][1] = 'B';
		board[1][2] = 'W';
		board[2][0] = 'B';
		board[2][1] = 'W';
		board[2][2] = 'W';

		LonelyPixel1 lonelyPixel1 = new LonelyPixel1();

		System.out.println(lonelyPixel1.findLonelyPixel(board));

		board = new char[1][3];
		board[0][0] = 'W';
		board[0][1] = 'W';
		board[0][2] = 'W';

		System.out.println(lonelyPixel1.findLonelyPixel(board));

	}

	public int findLonelyPixel(char[][] picture) {
		int lonely = 0;
		for (int i = 0; i < picture.length; ++i) {
			for (int j = 0; j < picture[i].length; ++j) {
				if (picture[i][j] == 'B') {
					boolean isLonely = true;
					
					//rows for column j
					for (int z = 0; z < picture.length; z++) {
						if (z == i)
							continue;
						if (picture[z][j] == 'B') {
							isLonely = false;
							break;
						}
					}
					if (isLonely) {
						// all column for row i
						for (int x = 0; x < picture[i].length; x++) {
							if (x == j)
								continue;
							if (picture[i][x] == 'B') {
								isLonely = false;
								break;
							}
						}
					}

					if (isLonely)
						lonely++;
				}
			}
		}

		return lonely;
	}

}
