package org.raosantosh.algorithms.leetcode.tree2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s.rao on 7/2/18.
 */
public class AllNodesDistanceK {

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

    // Step 1 find path between root and target

    List<TreeNode> path = new ArrayList<>();
    List<Integer> result = new ArrayList<>();

    if (K == 0 && target != null) {
      result.add(target.val);
    }

    findNodeAtLength(target, K, target, result, path);

    if (DFS(root, target, path)) {
      for (int i = 0; i < path.size(); ++i) {
        int pathLength = path.size() - i;
        findNodeAtLength(path.get(i), K - pathLength, target, result, path);
      }
    }

    return result;
  }

  public void findNodeAtLength(TreeNode root, int length, TreeNode target, List<Integer> nodeList, List<TreeNode> path) {

    if (root == null) {
      return;
    }

    if (length == 0 ) {
      if (root.val != target.val)
        nodeList.add(root.val);
      return;
    }

    if (!path.contains(root.left)) {
      if(root.left !=null && root.left.val != target.val)
      findNodeAtLength(root.left, length - 1, target, nodeList, path);
    }
    if (!path.contains(root.right)) {
      if(root.right !=null && root.right.val != target.val)
      findNodeAtLength(root.right, length - 1, target, nodeList, path);
    }

    return;
  }


  private boolean DFS(TreeNode node, TreeNode target, List<TreeNode> path) {
    if (node == null) {
      return false;
    }
    if (target.val == node.val) {
      return true;
    }

    path.add(node);
    if (DFS(node.left, target, path)) {
      return true;
    }
    if (DFS(node.right, target, path)) {
      return true;
    }
    path.remove(node);

    return false;
  }

  public static void main(String args[]) {
   TreeNode node = new TreeNode(3);
    node.left = new TreeNode(5);
    node.right = new TreeNode(1);
    node.left.left = new TreeNode(6);
    node.left.right = new TreeNode(2);
    node.left.right.left = new TreeNode(7);
    node.left.right.right = new TreeNode(4);

    node.right.left = new TreeNode(0);
    node.right.right = new TreeNode(8);


   /*TreeNode node = new TreeNode(0);
   node.left = new TreeNode(2);
   node.right = new TreeNode(1);
   node.right.left = new TreeNode(3);*/

   /*
   [0,1,2,null,3,null,5,4]
3
3
    */

   /*
                 0
         1            2
           3              5
         4
    */

  /*  TreeNode node = new TreeNode(0);
    node.left = new TreeNode(1);
    node.right = new TreeNode(2);
    node.left.right = new TreeNode(3);
    node.right.right = new TreeNode(5);
    node.left.right.left = new TreeNode(4);*/

    AllNodesDistanceK finder = new AllNodesDistanceK();
    List<Integer> result = finder.distanceK(node, node.left, 2);
    for (Integer res : result) {
      System.out.println("res :" + res);
    }
  }
}
