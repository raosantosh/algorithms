package org.raosantosh.algorithms.leetcode.numerical;

public class DivideWithout {

	public static void main(String args[]) {
		DivideWithout divideWithout = new DivideWithout();
//		System.out.println("10 and 2: " + divideWithout.divide(10, 2));
//		System.out.println("-10 and 2: " + divideWithout.divide(-10, 2));
//		System.out.println("10 and -2: " + divideWithout.divide(10, -2));
//		System.out.println("11 and 2: " + divideWithout.divide(11, 2));
//		System.out.println("-11 and -2: " + divideWithout.divide(-11, -2));
//		System.out.println("11 and 0: " + divideWithout.divide(11, 0));
//		System.out.println("0 and 11: " + divideWithout.divide(0, 11));
//		System.out.println("11 and 1: " + divideWithout.divide(11, 1));
//		System.out.println("11 and 1: " + divideWithout.divide(-2147483648, -1));
//		System.out.println("11 and 1: " + divideWithout.divide(-2147483648, 1));
//		System.out.println("74 and 2: " + divideWithout.divide(75, 2));
//		System.out.println("2147483647 and 2: " + divideWithout.divide(2147483647, 2));
//		System.out.println("2147483648 and -2: " + divideWithout.divide(2147483647, -2));
//		System.out.println("-2147483648 and 2: " + divideWithout.divide(-2147483648, 2));
		System.out.println("-2147483648 and-1: " + divideWithout.divide(-2147483648, -1));
		
		
//		-1
//		-2147483648
//		2
		
	}

	public int divide(int idividend, int idivisor) {
		
		long dividend = idividend;
		long divisor = idivisor;
		
		boolean divisorNegative = false;
		boolean dividentNegative = false;

		if (divisor == 0)
			return Integer.MAX_VALUE;

		if (dividend == 0)
			return 0;

		if (divisor < 0) {
			divisorNegative = true;
			divisor = -divisor;
		}
		if (dividend < 0) {
			dividentNegative = true;
//			if(dividend == Integer.MIN_VALUE) {
//				dividend = Integer.MAX_VALUE;
//			}else {
			dividend = -dividend;
//			}
		}
		long result = 0;
		
		if (divisor == 1) {
			result = dividend;
		} else {
			long tempDivisor = divisor;
			long currentMultiplier = divisor;
			while ((dividend - tempDivisor) >= 0) {
				tempDivisor += currentMultiplier;
				System.out.println("cur:" + currentMultiplier + " temp: " + tempDivisor + "result:" + result + "divisor:"+ divisor + " divident:"+ dividend);
				if((currentMultiplier + currentMultiplier + tempDivisor) <= dividend) {
					currentMultiplier = currentMultiplier + currentMultiplier;
					result += (currentMultiplier / divisor);
				} else {
					result++;
					currentMultiplier = divisor;
				}
			}
		}
		
		if(result == Integer.MAX_VALUE && (dividentNegative ^ divisorNegative))
			return Integer.MIN_VALUE;

		System.out.println(result);
		
		if(dividentNegative ^ divisorNegative){
			return (int)-result;
		}else {
			if(result > Integer.MAX_VALUE)
				return Integer.MAX_VALUE;
			return (int)result;
		}
	}
}
