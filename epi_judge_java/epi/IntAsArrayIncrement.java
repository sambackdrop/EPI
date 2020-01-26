package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;

public class IntAsArrayIncrement {
	@EpiTest(testDataFile = "int_as_array_increment.tsv")
	public static List<Integer> plusOne(List<Integer> A) {
		int i = A.size() - 1;
		while (i >= 0) {
			if (A.get(i) + 1 > 9) {
				A.set(i, 0);
				i--;
				if (i < 0) {
					A.add(0, 1);
					break;
				}
			} else {
				A.set(i, A.get(i) + 1);
				break;
			}

		}
		return A;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IntAsArrayIncrement.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
