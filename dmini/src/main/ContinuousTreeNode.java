package main;

import java.util.Set;
import java.util.Vector;

public class ContinuousTreeNode extends OurTreeNode {

	public ContinuousTreeNode(OurTreeNode parent, Vector<double[]> data,Set<Double> vals,int index, Set<Integer> indicesAlreadySplit)
	{
		super(parent,data,vals,index,indicesAlreadySplit);
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

	@Override
	boolean goLeft(double val) {
		double splitVal = _valsOfSplit.iterator().next();//It should have only one value;
		if (val <= splitVal)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	OurTreeNode createShallowCopy() {
		return new ContinuousTreeNode(_parent, null, _valsOfSplit, _splitIndex, null);
	}
	
}
