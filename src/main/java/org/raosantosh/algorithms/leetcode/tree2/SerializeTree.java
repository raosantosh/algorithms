package org.raosantosh.algorithms.leetcode.tree2;

/**
 * Created by s.rao on 3/24/18.
 */
public class SerializeTree {

  private int currentIndex = 0;

  public static class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;


    public TreeNode() {

    }

    public TreeNode(int value) {
      this.val = value;
    }

    @Override
    public String toString() {
      return String.valueOf(val);
    }
  }

  public String serialize(TreeNode node) {
    return getStringValue(node);
  }

  private String getStringValue(TreeNode node) {
    if(node == null) return "null";
    return node.val + "," + getStringValue(node.left) + "," + getStringValue(node.right);
  }

  public TreeNode deSerialize(String treeString) {
      String splitNodes[] = treeString.split(",");
    return getTreeNode(splitNodes);

  }

  private TreeNode getTreeNode(String [] splitNodes) {
    if(splitNodes[currentIndex].equals("null") || currentIndex >= splitNodes.length) {
      currentIndex++;
      return null;
    }

    TreeNode node = new TreeNode(Integer.valueOf(splitNodes[currentIndex++]));
    node.left = getTreeNode(splitNodes);
    node.right = getTreeNode(splitNodes);

   return node;
  }

  public static void main(String args[]) {

    SerializeTree tree = new SerializeTree();

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(5);
    String result = tree.serialize(root);
    System.out.println(result);
    root = tree.deSerialize(result);
    tree = new SerializeTree();
    System.out.println(tree.serialize(root));



  }

}
