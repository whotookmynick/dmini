package main;

import java.util.Vector;

public class OurTreeNode {
	
	private OurTreeNode _parent;
	private OurTreeNode _left;
	private OurTreeNode _right;
	
	private Vector<double[]> _dataOfNode;
	
	private int splitAttribute;//The index of the attribute this node is split on.
	
	public String _name;	
	
	public OurTreeNode(OurTreeNode parent)
	{
		_parent = parent;
		_left = null;
		_right = null;
	}
	
	public OurTreeNode(OurTreeNode left,OurTreeNode right)
	{
		_left = left;
		_right = right;
	}

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

	public int getSplitAttribute() {
		return splitAttribute;
	}

	public void setSplitAttribute(int splitAttribute) {
		this.splitAttribute = splitAttribute;
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
