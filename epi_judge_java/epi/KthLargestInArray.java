package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class KthLargestInArray {
	// The numbering starts from one, i.e., if A = [3,1,-1,2] then
	// findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
	// findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
	@EpiTest(testDataFile = "kth_largest_in_array.tsv")
	public static int findKthLargest(int k, List<Integer> A) {
		int l = 0, r = A.size() - 1;
		int nP = 0;
		while (l <= r) {
			nP = partition(l, r, A);
			if (nP == k - 1) {
				// System.out.println("Yipee:" + nP + ":" + A.get(nP));
				break;
			} else if (nP > k - 1) {
				r = nP - 1;
			} else {
				l = nP + 1;
			}
			// System.out.println("Reg:" + nP);
		}
		return A.get(nP);
	}

	private static int partition(int l, int r, List<Integer> A) {
		Comparator<Integer> cmp = (a, b) -> Integer.compare(b, a);
		int pValue = A.get(r);
		int newP = l;
		for (int i = l; i < r; i++) {
			if (cmp.compare(A.get(i), pValue) < 0) {
				Collections.swap(A, i, newP);
				newP++;
			}
		}
		Collections.swap(A, r, newP);
		return newP;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "KthLargestInArray.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
