package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ApplyPermutation {
	public static void applyPermutation1(List<Integer> perm, List<Integer> A) {

		for (int i = 0; i < A.size(); i++) {
			while (i != perm.get(i)) {
				Collections.swap(A, i, perm.get(i));
				Collections.swap(perm, i, perm.get(i));
			}
		}

		return;
	}

	public static void applyPermutation(List<Integer> perm, List<Integer> A) {
		// without altering perm
		System.out.println(perm + ",," + A);
		for (int i = 0; i < A.size(); i++) {

			if (perm.get(i) < 0) {
				// perm.set(i, -1 * perm.get(i));
				continue;
			}

			int currentPosition = i;

			while (perm.get(currentPosition) != i) {
				int target = perm.get(currentPosition);
				Collections.swap(A, currentPosition, target);

				perm.set(currentPosition, -1 - target);

				currentPosition = target;
				System.out.println(
						"vCP" + perm.get(currentPosition) + ",CP:" + currentPosition + ",P:" + perm + ",," + A);
			}

			// Mark last current position as swapped before moving on
			perm.set(currentPosition, -1 - perm.get(currentPosition));
			System.out.println("CP:" + currentPosition + ",P:" + perm + ",," + A);

		}
		for (int i = 0; i < perm.size(); ++i)
			perm.set(i, -1 - perm.get(i));

		System.out.println(perm + ",," + A);
		return;
	}

	public static void main1(String[] args) {

		applyPermutation(new ArrayList<>(List.of(2, 4, 3, 0, 1)), new ArrayList<>(List.of(1, 2, 3, 4, 5)));
	}

	@EpiTest(testDataFile = "apply_permutation.tsv")
	public static List<Integer> applyPermutationWrapper(List<Integer> perm, List<Integer> A) {
		applyPermutation(perm, A);
		return A;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "ApplyPermutation.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
