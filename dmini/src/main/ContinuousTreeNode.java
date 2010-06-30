package main;

import java.util.Set;
import java.util.Vector;

public class ContinuousTreeNode extends OurTreeNode {

	public ContinuousTreeNode(OurTreeNode parent, Vector<double[]> data,Set<Double> vals)
	{
		super(parent,data,vals);
	}

	@Override
	OurTreeNode traverseByVal(double val) {
		double splitVal = _valsOfSplit.iterator().next();//It should have only one value;
		if (val <= splitVal)
		{
			return this.get_left();
		}
		else
		{
			return this.get_right();
		}
	}
}
