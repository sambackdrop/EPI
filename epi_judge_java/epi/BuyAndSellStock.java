package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;

public class BuyAndSellStock {
	@EpiTest(testDataFile = "buy_and_sell_stock.tsv")
	public static double computeMaxProfit(List<Double> prices) {

		double minSoFar = 0, profitSoFar = 0.0;
		if (prices.size() > 0) {
			minSoFar = prices.get(0);
			for (int i = 1; i < prices.size(); i++) {
				double val = prices.get(i);
				profitSoFar = Math.max(profitSoFar, val - minSoFar);
				minSoFar = Math.min(minSoFar, val);
			}
		}
		return profitSoFar;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "BuyAndSellStock.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
