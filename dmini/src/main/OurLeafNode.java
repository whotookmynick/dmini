package main;

import java.util.Set;
import java.util.Vector;

public class OurLeafNode extends OurTreeNode {

	protected int _classification;
	
	public OurLeafNode(OurTreeNode parent, int classification)
	{
		super(parent);
		_classification = classification;
	}
	
	@Override
	boolean goLeft(double d) {
		return false;
	}

	@Override
	OurTreeNode traverseByVal(double val) {
		return null;
	}
	
	@Override
	public boolean isLeaf()
	{
		return true;
	}
	
	public int getClassification()
	{
		return _classification;
	}

	@Override
	OurTreeNode createShallowCopy() {
		return new OurLeafNode(_parent, _classification);
	}
	
}
