package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class DutchNationalFlag {
	public enum Color {
		RED, WHITE, BLUE
	}

	public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
		boolean debug = false;
		int s = 0, l = A.size() - 1, i = 0, e = 0, unc = A.size() - 1;
		int p = A.get(pivotIndex).ordinal();
		if (debug) {
			System.out.println("P:" + p + ",Size:" + A.size());
			for (int j = 0; j < A.size(); j++) {
				System.out.print(" " + A.get(j).ordinal());
			}
		}
		while (i <= l) {
			int c = A.get(i).ordinal();
			if (c > p) {
				Collections.swap(A, i, l--);
			} else {
				if (c < p)
					Collections.swap(A, i, s++);
				i++;
			}
			if (debug) {
				System.out.println("");
				System.out.print(String.format("I:%d, c = %d, s=%d, l=%d, unc=%d::", i, c, s, l, unc));
				for (int j = 0; j < A.size(); j++) {
					System.out.print(" " + A.get(j).ordinal());
				}
			}
		}
		if (debug) {
			System.out.println("");
			for (i = 0; i < A.size(); i++) {
				System.out.print(" " + A.get(i).ordinal());
			}
		}
		return;
	}

	@EpiTest(testDataFile = "dutch_national_flag.tsv")
	public static void dutchFlagPartitionWrapper(TimedExecutor executor, List<Integer> A, int pivotIdx)
			throws Exception {
		List<Color> colors = new ArrayList<>();
		int[] count = new int[3];

		Color[] C = Color.values();
		for (int i = 0; i < A.size(); i++) {
			count[A.get(i)]++;
			colors.add(C[A.get(i)]);
		}

		Color pivot = colors.get(pivotIdx);
		executor.run(() -> dutchFlagPartition(pivotIdx, colors));

		int i = 0;
		while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
			count[colors.get(i).ordinal()]--;
			++i;
		}

		while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
			count[colors.get(i).ordinal()]--;
			++i;
		}

		while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
			count[colors.get(i).ordinal()]--;
			++i;
		}

		if (i != colors.size()) {
			throw new TestFailure("Not partitioned after " + Integer.toString(i) + "th element");
		} else if (count[0] != 0 || count[1] != 0 || count[2] != 0) {
			throw new TestFailure("Some elements are missing from original array");
		}
	}

	public static void main2(String[] args) {
		dutchFlagPartition(2,
				Arrays.asList(Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.WHITE,
						Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.WHITE, Color.RED,
						Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.WHITE));
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "DutchNationalFlag.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
