package main;

import java.util.Set;
import java.util.Vector;

public class CatagoricalTreeNode extends OurTreeNode {

	public CatagoricalTreeNode(OurTreeNode parent, Vector<double[]> data,Set<Double> vals,int index, Set<Integer> indicesAlreadySplit)
	{
		super(parent,data,vals,index,indicesAlreadySplit);
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
	@Override
	boolean goLeft(double val) {
		if (_valsOfSplit.contains(val))
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
		return new CatagoricalTreeNode(_parent, null, _valsOfSplit, _splitIndex, null);
	}
	
}
