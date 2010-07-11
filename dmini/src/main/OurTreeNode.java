package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public abstract class OurTreeNode {


        static int _nodeCount = 0; // for tree drawing
        static int _leavesCount = 0; 

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

        void drawTree() {
            String nodeCurr = "node"+(new Integer(_nodeCount++)).toString();

            String tree = "strict digraph {\nnode [style = filled, color=lightpink];\n"
                    +drawTree("",nodeCurr) + "\n}";
            try{

                FileWriter fstream = new FileWriter("tree.txt");
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(tree);

                out.close();
            }catch (Exception e){//Catch exception if any
              System.err.println("Error: " + e.getMessage());
            }

            // print report
            System.out.println("number of nodes: " + _nodeCount);
            System.out.println("number of leaves: " + _leavesCount);
        }

        String drawTree (String currStr, String nodeCurr) {

            String appendLeft;
            String appendRight;

            if (isLeaf()) {
                return currStr; 
            }
            double splitPoint = _valsOfSplit.iterator().next();
            String nodeL = "node"+(new Integer(_nodeCount++)).toString();
            String nodeR = "node"+(new Integer(_nodeCount++)).toString();

            currStr += nodeCurr + "[label = \""+ OurData.indexToName.get(_splitIndex) +"\"];\n";

            if (_left.isLeaf()){
                appendLeft = nodeL + "[label = \""+ OurData._indexToclass.get(((OurLeafNode)_left).getClassification())+"\"];\n";
                _leavesCount++; 
            }else 
                appendLeft = nodeL + "[label = \""+ OurData.indexToName.get(_left._splitIndex) +"\"];\n";
            if (_right.isLeaf()) {
                appendRight = nodeR + "[label = \""+ OurData._indexToclass.get(((OurLeafNode)_right).getClassification())+"\"];\n";
                _leavesCount++;
            } else
                appendRight = nodeR + "[label = \""+ OurData.indexToName.get(_right._splitIndex) +"\"];\n";

            currStr += appendLeft + appendRight
                + nodeCurr + "->" + nodeL + "[label = \"<" +splitPoint+"\"];\n"
                + nodeCurr + "->" + nodeR + "[label = \"=>" +splitPoint+"\"];\n";
                    
            return _left.drawTree(_right.drawTree(currStr,nodeR),nodeL);



        }

	
	abstract OurTreeNode createShallowCopy();
	
}
