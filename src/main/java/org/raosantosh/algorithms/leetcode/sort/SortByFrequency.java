package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by s.rao on 3/25/18.
 */
public class SortByFrequency {


  public static class Element implements Comparable<Element> {
    public int count;
    public Character c;

    public Element(Character c, int count) {
      this.count = count;
      this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
      return ((Element) obj).c.equals(c);
    }

    @Override
    public int hashCode() {
      return c.hashCode();
    }

    @Override
    public int compareTo(Element o) {
      return count - o.count;
    }
  }

  public String frequencySort(String s) {

     Map<Character,Element> elementMap = new HashMap<Character, Element>();

      for(int i=0; i < s.length(); ++i) {
          if(elementMap.containsKey(s.charAt(i))) {
            int count = elementMap.get(s.charAt(i)).count;
            elementMap.put(s.charAt(i), new Element(s.charAt(i), count + 1));
          }
          else
            elementMap.put(s.charAt(i), new Element(s.charAt(i),1));
      }

      List<Element> elements = new ArrayList(elementMap.values());

      Collections.sort(elements);

      StringBuffer buffer = new StringBuffer();
      for(int i =0; i <elements.size(); ++i) {
        for(int j=0 ; j < elements.get(i).count; ++j) {
          buffer.append(elements.get(i).c);
        }
    }

    return buffer.toString();
  }

  public  static void main(String args[]) {
    SortByFrequency sorter = new SortByFrequency();
    System.out.println(sorter.frequencySort("tree"));
  }

}
