package main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public abstract class OurTreeNode {
	
	protected OurTreeNode _parent;
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
		if (indicesAlreadySplit != null)
		{
			_alreadySplit = new HashSet<Integer>(indicesAlreadySplit);
		}
		else
		{
			_alreadySplit = new HashSet<Integer>();
		}
		_alreadySplit.add(index);
		_left = null;
		_right = null;
	}
	
	public OurTreeNode(OurTreeNode parent)
	{
		_parent = parent;
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

	public boolean isLeaf() {
		return false;
	}

	public OurTreeNode waterDownCopy() {
		OurTreeNode ans = this.createShallowCopy();
		if (_left != null)
		{
			ans._left = _left.waterDownCopy();
		}
		if (_right != null)
		{
			ans._right = _right.waterDownCopy();
		}
		return ans;
	}

	public String toString() {
		if (isLeaf()) 
			return ((OurLeafNode)this).getClassification()+"";
		StringBuffer buffer = new StringBuffer();
		buffer.append("[" + OurData.indexToName.get(_splitIndex) + ", ");
		if (_left == null) 
			buffer.append("null");
		else 
			buffer.append(_left.toString());
		buffer.append(", ");
		if (_right == null) 
			buffer.append("null");    
		else 
			buffer.append(_right.toString());
		buffer.append("]");    
		return buffer.toString();
	}
	
	public void print() {
		print("                                                                  ");
	}
	
	private void print(String indent) {
		if (isLeaf())
		{
			System.out.println(((OurLeafNode)this).getClassification()+"");
			return;
		}
		System.out.println(indent + OurData.indexToName.get(_splitIndex));
		if (_left == null) {
			System.out.println(indent + "   " + "null");
		}
		else {
			_left.print(indent + "   ");
		}
		if (_right == null) {
			System.out.println(indent + "   " + "null");
		}
		else {
			_right.print(indent + "   ");
		}
	}

	
	abstract OurTreeNode createShallowCopy();
	
}
