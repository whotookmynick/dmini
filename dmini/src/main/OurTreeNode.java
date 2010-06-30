package main;

import java.util.Set;
import java.util.Vector;

public abstract class OurTreeNode {
	
	private OurTreeNode _parent;
	protected OurTreeNode _left;
	protected OurTreeNode _right;
	
	private Vector<double[]> _dataOfNode;
	
	protected Set<Double> _valsOfSplit;
	
	public String _name;	
	
	public OurTreeNode(OurTreeNode parent,Vector<double[]> data,Set<Double> vals)
	{
		_parent = parent;
		_dataOfNode = data;
		_valsOfSplit = vals;
		_left = null;
		_right = null;
	}
	
	public OurTreeNode(OurTreeNode left,OurTreeNode right)
	{
		_left = left;
		_right = right;
	}

	abstract OurTreeNode traverseByVal(double val);
	
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
	
	
}
