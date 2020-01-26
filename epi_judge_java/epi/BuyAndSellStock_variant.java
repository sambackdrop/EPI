package epi;

import java.util.*;

public class BuyAndSellStock_variant {
	/*
	 * Given array of integers, find the length of longest subarray of same
	 * elements..
	 */

	public static void main(String[] args) {

		Integer[] A = new Integer[] { 1, 1, 1, 2, 3, 3, 4, 4, 3, 2, 3, 3, 3, 3, 3, 4, 5, 6, 6, 6, 6, 6, 7 };

		int maxCnt = 0, value = -1;
		if (A.length > 0) {
			int prevVal = A[0], cnt = 1;
			maxCnt = 1;
			for (int i = 2; i < A.length; i++) {
				cnt = A[i] == prevVal ? cnt + 1 : 1;
				if (cnt >= maxCnt) {
					maxCnt = cnt;
					value = A[i];
				}
				// maxCnt = Math.max(maxCnt, cnt);
				prevVal = A[i];
			}
		}
		System.out.println(maxCnt + ":" + value);
	}

}
