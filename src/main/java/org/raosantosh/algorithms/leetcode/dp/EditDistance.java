package org.raosantosh.algorithms.leetcode.dp;

/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:

 a) Insert a character
 b) Delete a character
 c) Replace a character
 */

public class EditDistance {

  // recursive way
  public int minDistanceRecursive(String word1, String word2) {
    int[][] memoArray = new int[word1.length() + 1 ][word2.length() + 1];

    for(int i=0; i < word1.length() + 1;++i) {
      for(int j=0; j < word2.length() + 1; ++j) {
        memoArray[i][j] = Integer.MAX_VALUE;
      }
    }

    return editDistance(word1, word2, 0, 0, memoArray);
  }

  private int editDistance(String word1, String word2, int word1Position, int word2Position, int [][] memoArray) {

    if(!isDone(word1, word1Position) && !isDone(word2, word2Position) && memoArray[word1Position][word2Position] != Integer.MAX_VALUE)
      return memoArray[word1Position][word2Position];

    while(!isDone(word2, word2Position) && !isDone(word1, word1Position)) {
      if(word1.charAt(word1Position) == word2.charAt(word2Position)) {
        word1Position++;
        word2Position++;
      }else {
        break;
      }
    }

    if((word1Position >= word1.length()) && (word2Position >= word2.length()))
      return 0;

    int substitueDistance = Integer.MAX_VALUE;
    if(!isDone(word1, word1Position) && !isDone(word2, word2Position))
      substitueDistance = editDistance(word1,word2, word1Position + 1, word2Position +1, memoArray) + 1;

    int deleteDistance = Integer.MAX_VALUE;
    if(!isDone(word2, word2Position))
      deleteDistance = editDistance(word1,word2, word1Position, word2Position +1, memoArray) +1 ;

    int insertDistance = Integer.MAX_VALUE;
    if(!isDone(word1, word1Position))
      insertDistance = editDistance(word1,word2, word1Position + 1, word2Position, memoArray) + 1;

    memoArray[word1Position][word2Position] = Math.min(Math.min(substitueDistance,deleteDistance), insertDistance);
    return memoArray[word1Position][word2Position];
  }

  private boolean isDone(String word, int offset) {
    return offset >= word.length() ;
  }
  /// recursive ends



  public int minDistance(String word1, String word2) {
    int memoMatrix[][] = new int[word1.length() + 1][word2.length() + 1];

    for(int i =0; i <= word1.length() ; ++i)
      memoMatrix[i][0] = i;

    for(int i=0;i <= word2.length(); ++i)
      memoMatrix[0][i] = i;

    for(int i=1; i <= word1.length(); ++i) {
      for(int j=1; j<=word2.length(); ++j) {
        if(word1.charAt(i-1) == word2.charAt(j-1)) {
          memoMatrix[i][j] = memoMatrix[i-1][j-1];
        } else {
          memoMatrix[i][j] = Math.min(Math.min(memoMatrix[i-1][j-1], memoMatrix[i-1][j]), memoMatrix[i][j-1]) + 1;
        }
      }
    }

    return memoMatrix[word1.length()][word2.length()];
  }



  public static void main(String args[]) {

    EditDistance distanceFinder = new EditDistance();
    System.out.println(distanceFinder.minDistance("santsh", "santosh"));

  }

}
