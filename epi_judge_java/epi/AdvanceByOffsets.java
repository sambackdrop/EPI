package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;

public class AdvanceByOffsets {
	@EpiTest(testDataFile = "advance_by_offsets.tsv")
	public static boolean canReachEnd(List<Integer> maxAdvanceSteps) {

		// {3,3,0,0,1,2,1}
		int stepsSoFar = 0, stepsCnt = 0;
		for (int i = 0; i <= stepsSoFar && stepsSoFar < maxAdvanceSteps.size(); i++) {
			if ((i + maxAdvanceSteps.get(i)) > stepsSoFar) {
				stepsSoFar = i + maxAdvanceSteps.get(i);
				stepsCnt++;
			}
		}
		System.out.println("I:" + maxAdvanceSteps.size() + ";cnt:" + stepsCnt);
		return stepsSoFar >= maxAdvanceSteps.size() - 1;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "AdvanceByOffsets.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
