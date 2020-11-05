package br.svcdev.githubclient.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Model {

	private static final int MAX_COUNT_OF_COUNTERS = 3;

	private List<Integer> counters = new ArrayList<>(Arrays.asList(0, 0, 0));

	public int getCurrentValue(int index) {
		if (index >= MAX_COUNT_OF_COUNTERS) {
			throw new RuntimeException("Wrong index value");
		}
		return counters.get(index);
	}

	public int incrementValue(int index) {
		int newValue = counters.get(index);
		counters.set(index, ++newValue);
		return newValue;
	}

	public void setCounterValue(int index, int value) {
		counters.set(index, value);
	}

}
