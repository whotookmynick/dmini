package main;

import java.util.Set;

public class CatagoricalSplitPointDeterminator implements
		SplitPointDeterminator {

	private Set<Double> _values;
	
	public CatagoricalSplitPointDeterminator(Set<Double> currSet) {
		_values = currSet;
	}

	@Override
	public boolean isLeftOfSplit(double d, double currValue) {
		return _values.contains(d);
	}

}
