package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public abstract class OurTreeNode {
	
	private OurTreeNode _parent;
	protected OurTreeNode _left;
	protected OurTreeNode _right;
	
	private Vector<double[]> _dataOfNode;
	
	protected Set<Double> _valsOfSplit;
	protected int _splitIndex;
	protected Set<Integer> _alreadySplit;
	
	public String _name;	
	
	public OurTreeNode(OurTreeNode parent,Vector<double[]> data,Set<Double> vals,int index, Set<Integer> indicesAlreadySplit)
	{
		_parent = parent;
		_dataOfNode = data;
		_valsOfSplit = vals;
		_splitIndex = index;
		_alreadySplit = new HashSet<Integer>(indicesAlreadySplit);
		_alreadySplit.add(index);
		_left = null;
		_right = null;
	}
	
	public OurTreeNode(OurTreeNode left,OurTreeNode right)
	{
		_left = left;
		_right = right;
	}

	abstract OurTreeNode traverseByVal(double val);
	

	abstract boolean goLeft(double d);
	
	public OurTreeNode get_left() {
		return _left;
	}

	public void set_left(OurTreeNode left) {
		_left = left;
	}

	public OurTreeNode get_right() {
		return _right;
	}

	public void set_right(OurTreeNode right) {
		_right = right;
	}

	public OurTreeNode get_parent() {
		return _parent;
	}

	public Vector<double[]> get_dataOfNode() {
		return _dataOfNode;
	}

	public void set_dataOfNode(Vector<double[]> dataOfNode) {
		_dataOfNode = dataOfNode;
	}

	public int get_splitIndex() {
		return _splitIndex;
	}

	public Set<Integer> get_alreadySplit() {
		return _alreadySplit;
	}
	
}
