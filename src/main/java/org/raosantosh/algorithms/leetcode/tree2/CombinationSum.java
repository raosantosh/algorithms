package org.raosantosh.algorithms.leetcode.tree2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by s.rao on 6/27/18.
 */
public class CombinationSum {

  public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int
      target) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    if(candidates == null || candidates.length == 0) return result;
    ArrayList<Integer> current = new ArrayList<Integer>();
    Arrays.sort(candidates);
    combinationSum(candidates, target, 0, current, result);
    return result;
  }
  public void combinationSum(int[] candidates, int target, int j,
      ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> result){
    System.out.println(curr);
    if(target == 0){
      ArrayList<Integer> temp = new ArrayList<Integer>(curr);
      result.add(temp);
      System.out.println(temp + " result ");
      return;
    }
    for(int i=j; i<candidates.length; i++){
      if(target < candidates[i])
        return;
      curr.add(candidates[i]);
      combinationSum(candidates, target - candidates[i], i, curr, result);
      curr.remove(curr.size()-1);
    } }

    public static void main(String args[]) {

    CombinationSum sum = new CombinationSum();
    //sum.combinationSum2(new int[]{1,3, 5 ,7}, 10);
      System.out.println(sum.isPalindrome("abddba"));

    }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    List<Integer> curr = new ArrayList<Integer>();
    Arrays.sort(candidates);
    helper(result, curr, 0, target, candidates);
    return result;
  }
  public void helper(List<List<Integer>> result, List<Integer> curr, int start,
      int target, int[] candidates){

    System.out.println(curr + " index " + start);

    if(target==0){
      result.add(new ArrayList<Integer>(curr));
      return;
    }
    if(target<0){
      return; }
    int prev=-1;
    for(int i=start; i<candidates.length; i++){
      if(prev!=candidates[i]){ // each time start from different element
        curr.add(candidates[i]);
        helper(result, curr, i+1, target-candidates[i], candidates); // and
        curr.remove(curr.size()-1);
        prev=candidates[i];
      }
    } }


    public static boolean isPalindrome(String s) {

    int start = 0 ;
    int end = s.length() -1;

    while (end > start) {
      if(s.charAt(start ++) != s.charAt(end --))
        return false;
    }


    return true;
    }

}
