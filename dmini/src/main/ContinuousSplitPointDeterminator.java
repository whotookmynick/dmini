package main;

public class ContinuousSplitPointDeterminator implements SplitPointDeterminator {

	@Override
	public boolean isLeftOfSplit(double d, double currValue) {
		if (d <= currValue)
			return true;
		else
			return false;
	}

}
