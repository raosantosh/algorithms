package org.raosantosh.algorithms.leetcode.dynamic;

public class MineSweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
    	
    	
    	if(board[click[0]][click[1]] == 'B') {
    		
    	}else if (board[click[0]][click[1]] == 'M') {
    		
    	}else {
    		
    	}
    	
    	
        return board;
    }
    
    private void copyBoard(char[][] original, char[][] copied) {
    	
    	for(int i = 0; i < original.length ; ++i) {
    		for (int j=0 ; i< original[0].length; ++j) {
    			copied[i][i] = original[i][j];
    		}
    	}
    }
    
    
    
    

}
