package main;

public class OurTreeNode {
	
	private OurTreeNode _left;
	private OurTreeNode _right;
	
	private int splitAttribute;//The index of the attribute this node is split on.
	
		
	
	public OurTreeNode()
	{
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
	
	
}
