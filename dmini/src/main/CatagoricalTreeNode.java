package main;

import java.util.Set;
import java.util.Vector;

public class CatagoricalTreeNode extends OurTreeNode {

	public CatagoricalTreeNode(OurTreeNode parent, Vector<double[]> data,Set<Double> vals)
	{
		super(parent,data,vals);
	}
	@Override
	OurTreeNode traverseByVal(double val) {
		if (_valsOfSplit.contains(val))
		{
			return this._left;
		}
		else
		{
			return this._right;
		}
	}

}
