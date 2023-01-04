package com.openi40.scheduler.common.utils;

import java.util.ArrayList;
import java.util.List;

public class SubsetGenerator {
	public static <T> List<List<T>> generateSubsets(List<T> input, int n) {
		List<List<T>> subsets = new ArrayList<>();
		generateSubsets(input, n, 0, new ArrayList<>(), subsets);
		return subsets;
	}

	private static <T> void generateSubsets(List<T> input, int n, int index, List<T> current, List<List<T>> subsets) {
		if (current.size() == n) {
			subsets.add(new ArrayList<>(current));
			return;
		}
		if (index == input.size()) {
			return;
		}

		// Generate subsets including the current element
		current.add(input.get(index));
		generateSubsets(input, n, index + 1, current, subsets);
		current.remove(current.size() - 1);

		// Generate subsets excluding the current element
		generateSubsets(input, n, index + 1, current, subsets);
	}
}
