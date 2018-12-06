package org.raosantosh.algorithms.leetcode.string2;

/**
 * Created by s.rao on 3/24/18.
 */
public class LongestPalindrome {

  public int longestPalindrome(String newString) {

    int[] counters = new int[52];

    for (int i = 0; i < newString.length(); ++i) {
      if (newString.charAt(i) < 'a') {
        counters[newString.charAt(i) - 'A']++;
      } else {
        counters[(newString.charAt(i) - 'a') + 26]++;
      }

    }

    int maxOdd = 0;
    int totalEven = 0;
    boolean negativeAdded = false;

    for (int i = 0; i < counters.length; ++i) {
      if (counters[i] % 2 == 0) {
        totalEven += counters[i];
      } else {
        maxOdd = Math.max(maxOdd, counters[i]);
        totalEven += counters[i] - 1;
        if(!negativeAdded) {
          negativeAdded = true;
          totalEven++;
        }

      }
    }

    return totalEven;
  }

  public static void main(String args[]) {
    LongestPalindrome finder = new LongestPalindrome();
    System.out.println(finder.longestPalindrome(
        "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreat"
            + "battlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavethei"
            + "livesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedi"
            + "catewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditf"
            + "araboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforg"
            + "etwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfa"
            + "rsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetake"
            + "increaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshal"
            + "lnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
  }


}
