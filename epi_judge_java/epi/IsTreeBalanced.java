package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

	@EpiTest(testDataFile = "is_tree_balanced.tsv")

	public static boolean isBalanced(BinaryTreeNode<Integer> tree) {

		return isBalancedHelper(tree) != -1;
	}

	private static int isBalancedHelper(BinaryTreeNode<Integer> tree) {

		if (tree == null)
			return 0;
		int leftTreeHeight = isBalancedHelper(tree.left);
		int rightTreeHeight = isBalancedHelper(tree.right);
		if (leftTreeHeight == -1 || rightTreeHeight == -1 || Math.abs(leftTreeHeight - rightTreeHeight) > 1)
			return -1;
		return Math.max(leftTreeHeight, rightTreeHeight) + 1;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "IsTreeBalanced.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
