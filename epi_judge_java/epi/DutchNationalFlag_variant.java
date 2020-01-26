package epi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import epi.DutchNationalFlag.Color;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

public class DutchNationalFlag_variant {
	public enum Color {
		RED, WHITE, BLUE, GRAY
	}

	public static void dutchFlagPartition(List<Color> A) {
		boolean debug = true;
		int one = 0, two = 0, three = 0, four = A.size() - 1;

		if (debug) {
			System.out.println("Size:" + A.size());
			for (int j = 0; j < A.size(); j++) {
				System.out.print(" " + A.get(j).ordinal());
			}
		}
		while (three <= four) {
			int c = A.get(three).ordinal();
			if (c == 0) {
				Collections.swap(A, three, one);
				one = one + 1;
				if (two < one)
					two++;
				if (three < one)
					three++;
			} else if (c == 1) {
				Collections.swap(A, three, two);
				two++;
				if (three < two)
					three++;
			} else if (c == 2) {
				three++;
			} else {
				Collections.swap(A, three, four);
				four--;
			}
			if (debug) {
				System.out.println("");
				System.out.print(String.format("One:%d, two=%d, thr=%d, four=%d, c = %d::", one, two, three, four, c));
				for (int j = 0; j < A.size(); j++) {
					System.out.print(" " + A.get(j).ordinal());
				}
			}
		}
		if (debug) {
			System.out.println("");
			for (int i = 0; i < A.size(); i++) {
				System.out.print(" " + A.get(i).ordinal());
			}
		}
		return;
	}

	public static void main(String[] args) {
		dutchFlagPartition(Arrays.asList(Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.WHITE,
				Color.RED, Color.WHITE, Color.GRAY, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.WHITE,
				Color.RED, Color.WHITE, Color.BLUE, Color.GRAY, Color.RED, Color.RED, Color.BLUE, Color.WHITE));
	}

}
