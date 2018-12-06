package org.raosantosh.algorithms.leetcode.twopointer;

/**
 * Created by s.rao on 7/11/18.
 */
public class RotateList {

  static int[] rotLeft(int[] a, int d) {

    int temp = 0;
    int toPutIndex = getIndex(0, a.length, d);
    int fromElement = a[0];
    while (toPutIndex != 0) {
      temp = a[toPutIndex];
      a[toPutIndex] = fromElement;
      fromElement = temp;

      toPutIndex = getIndex(toPutIndex, a.length , d) ;
    }

    a[0] = temp;

    return a;
  }

   static int getIndex(int current, int length, int size) {
    while(size -- > 0) {

      if(current > 0) current -- ;
      else {
        current = length - 1;
      }
    }

    System.out.println(current);

    return current;
  }

   int[] rotRight(int[] a, int d) {

     int temp = 0;
     int toPutIndex = d;
     int fromElement = a[0];
     while (toPutIndex != 0) {
        temp = a[toPutIndex];
       a[toPutIndex] = fromElement;
       fromElement = temp;
       toPutIndex += d;

       if(toPutIndex >= a.length) {
         toPutIndex = toPutIndex - a.length;
       }
     }

     a[0] = temp;

  return a;
  }

  public static void main(String args[]) {

    String list = "41 73 89 7 10 1 59 58 84 77 77 97 58 1 86 58 26 10 86 51";

    //1 2 3 4 - 2
    //3 4 1 2
    //3    1


    int nums[] = create(list);
    RotateList rotater = new RotateList();

    rotater.rotLeft(nums, 10);

    for(int i = 0 ; i < nums.length ; ++i)
      System.out.println("res " + nums[i]);

  }

  public static int[] create(String list) {

    String [] splitList = list.split(" ");
    int [] result = new int[splitList.length];
    for(int i=0 ; i < result.length; ++i) {
      result[i] = Integer.valueOf(splitList[i]);
    }

    return result;
  }
}
