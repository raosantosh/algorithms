package org.raosantosh.algorithms.leetcode.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by s.rao on 7/8/18. We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and
 * ("A", "B", ...) are locks.
 *
 * We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk
 * into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.
 *
 * For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that
 * there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same
 * order as the English alphabet.
 *
 * Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: ["@.a.#","###.#","b.A.B"] Output: 8 Example 2:
 *
 * Input: ["@..aA","..B#.","....b"] Output: 6
 *
 *
 * Note:
 *
 * 1 <= grid.length <= 30 1 <= grid[0].length <= 30 grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F' The number of keys is in [1, 6].  Each key has a
 * different letter and opens exactly one lock. Discuss
 */
public class SmallestPathAllKeys {

  public static class NodeIndex {

    int x;
    int y;
    Set<Character> chars;

    public NodeIndex(int x, int y, Set<Character> chars) {
      this.x = x;
      this.y = y;
      this.chars = chars;
    }

    @Override
    public boolean equals(Object obj) {

      NodeIndex toCheck = (NodeIndex) obj;

      return toCheck.x == x && toCheck.y == y && chars.equals(toCheck.chars);
    }

    @Override
    public String toString() {
      return "NI x : " + x + " y : " + y +  " set: " + chars;
    }

    @Override
    public int hashCode() {
      return x * 91 + y * 23 + chars.hashCode();
    }
  }

  public int shortestPathAllKeys(String[] grid) {

    char[][] matrix = new char[30][30];
    int startX = 0;
    int startY = 0;

    Set<Character> allKeys = new HashSet<>();

    for (int i = 0; i < grid.length; ++i) {
      String current = grid[i];
      for (int j = 0; j < current.length(); ++j) {
        matrix[i][j] = current.charAt(j);
        if (isLower(matrix[i][j])) {
          allKeys.add(matrix[i][j]);
        }
        if (current.charAt(j) == '@') {
          startX = i;
          startY = j;
        }
      }
    }

    Map<NodeIndex, Integer> visitedMap = new HashMap<>();

    int resul = findPath(startX, startY, new HashSet<Character>(), visitedMap, matrix, grid.length, grid[0].length(), allKeys, 0);

    return resul == Integer.MAX_VALUE ? -1 : resul;
  }

  public int findPath(int x, int y, Set<Character> currentSet, Map<NodeIndex, Integer> visited, char[][] matrix, int maxX, int maxY,
      Set<Character> allKeys, int current) {

    //System.out.println("x : " + x + " y: " + y + " c : " + currentSet + " v : " + visited);

    if (x > maxX || y > maxY || x < 0 || y < 0) {
      return Integer.MAX_VALUE;
    }

    if (matrix[x][y] == 0 || matrix[x][y] == '#') {
      return Integer.MAX_VALUE;
    }

    if (isUpper(matrix[x][y]) && !currentSet.contains(getLower(matrix[x][y]))) {
      return Integer.MAX_VALUE;
    }

    if (visited.containsKey(new NodeIndex(x, y, currentSet))) {
      if(current >= visited.get(new NodeIndex(x,y,currentSet)))
        return Integer.MAX_VALUE;
      current =  Math.min(current, visited.get(new NodeIndex(x,y,currentSet)));
      //return Integer.MAX_VALUE;
    }

    visited.put(new NodeIndex(x, y, new HashSet<>(currentSet)), current);

    if (isLower(matrix[x][y])) {
      currentSet.add(matrix[x][y]);
    }

    if (currentSet.equals(allKeys)) {
      System.out.println("current " + current);
      return current;
    }

    int a = findPath(x + 1, y, new HashSet<>(currentSet), visited, matrix, maxX, maxY, allKeys, current + 1);
    int b = findPath(x - 1, y, new HashSet<>(currentSet), visited, matrix, maxX, maxY, allKeys, current + 1);
    int c = findPath(x, y + 1, new HashSet<>(currentSet), visited, matrix, maxX, maxY, allKeys, current + 1);
    int d = findPath(x, y - 1, new HashSet<>(currentSet), visited, matrix, maxX, maxY, allKeys, current + 1);

    //if(isLower(matrix[x][y])) currentSet.remove(matrix[x][y]);

    //visited.remove(new NodeIndex(x, y, currentSet));

    int minValue = Math.min(a, Math.min(b, Math.min(c, d)));

    return minValue == Integer.MAX_VALUE ? Integer.MAX_VALUE : minValue;
  }

  char getLower(char c) {
    return (char) ('a' + c - 'A');
  }

  boolean isUpper(char c) {
    return c >= 'A' && c <= 'Z';
  }

  boolean isLower(char c) {
    return c >= 'a' && c <= 'z';
  }

  public static void main(String args[]) {
    SmallestPathAllKeys paths = new SmallestPathAllKeys();

    String[] grid = new String[3];
    grid[0] = "@.a.#";
    grid[1] = "###.#";
    grid[2] = "b.A.B";

    /*grid[0] = "@..aA";
    grid[1] = "..B#.";
    grid[2] = "....b";*/

    //["@..aA","..B#.","....b"]

    System.out.println(paths.shortestPathAllKeys(grid));
  }

}
