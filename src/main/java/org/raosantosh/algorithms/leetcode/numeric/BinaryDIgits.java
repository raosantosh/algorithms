package org.raosantosh.algorithms.leetcode.numeric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by s.rao on 4/3/18. A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on
 * the bottom represent the minutes (0-59).
 *
 * Each LED represents a zero or one, with the least significant bit on the right.
 *
 *
 * For example, the above binary watch reads "3:25".
 *
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times
 * the watch could represent.
 *
 * Example:
 *
 * Input: n = 1 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 */

public class BinaryDIgits {

  private List<String> possibilities = Arrays
      .asList("1hr", "2hr", "4hr", "8hr", "1min", "2min", "4min", "8min", "16min", "32min");
  private Map<String, Integer> hourMap = new HashMap<String, Integer>();
  private Map<String, Integer> minuteMap = new HashMap<String, Integer>();

  public BinaryDIgits() {

    hourMap.put("1hr", 1);
    hourMap.put("2hr", 2);
    hourMap.put("4hr", 4);
    hourMap.put("8hr", 8);

    minuteMap.put("1min", 1);
    minuteMap.put("2min", 2);
    minuteMap.put("4min", 4);
    minuteMap.put("8min", 8);
    minuteMap.put("16min", 16);
    minuteMap.put("32min", 32);
  }

  public List<String> readBinaryWatch(int num) {
    List<List<String>> allcombinations = new ArrayList<List<String>>();
    if(num == 0 )
    {
      List<String> res = new ArrayList<String>();
      res.add("0:00");
      allcombinations.add(res);
    }else if(num > 8 )
      return new ArrayList<String>();
    else {
      allcombinations = createCombination(0, possibilities, num);
    }
    return validateAndSend(allcombinations);
  }

  private List<List<String>> createCombination(int currentIndex, List<String> elements, int leftElements) {

    List<List<String>> toreturn = new ArrayList<List<String>>();

    if (leftElements == 0 || currentIndex >= elements.size()) {
      return toreturn;
    }

    List<List<String>> combinationWithout = createCombination(currentIndex + 1, elements,
        leftElements);
    List<List<String>> combinationWith = createCombination(currentIndex + 1, elements,
        leftElements - 1);

    if(combinationWith.size() > 0)
    for (List<String> combiationWithElements : combinationWith) {
      combiationWithElements.add(elements.get(currentIndex));
    } else if(leftElements == 1){
      combinationWith = new ArrayList<List<String>>();
      List<String> currentList = new ArrayList<String>();
      currentList.add(elements.get(currentIndex));
      combinationWith.add(currentList);
    }

    combinationWith.addAll(combinationWithout);
    return combinationWith;
  }

  private List<String> validateAndSend(List<List<String>> completeSequence) {
    List<String> results = new ArrayList<String>();
    for (List<String> currentSequence : completeSequence) {
      String seq = isValid(currentSequence);
      if (seq != null) {
        results.add(seq);
      }
    }

    Collections.sort(results);

    return results;
  }


  private String isValid(List<String> sequence) {

    int hourTotal = 0;
    int minuteTotal = 0;

    for (String val : sequence) {
      if (hourMap.containsKey(val)) {
        hourTotal += hourMap.get(val);
      } else if (minuteMap.containsKey(val)) {
        minuteTotal += minuteMap.get(val);
      }
      if (hourTotal > 11 || minuteTotal > 59) {
        return null;
      }
    }

    String minute = "";
    if(minuteTotal < 10) {
      minute += "0" + minuteTotal;
    }else
    {
      minute = "" + minuteTotal;
    }

    return hourTotal + ":" + minute;
  }

  public static void main(String args[]) {

    BinaryDIgits digits = new BinaryDIgits();
    System.out.println(digits.readBinaryWatch(1));
    System.out.println(digits.readBinaryWatch(10));
    System.out.println(digits.readBinaryWatch(0));
    System.out.println(digits.readBinaryWatch(2));
  }

  private static void print(List<String> seq) {

  }


}
