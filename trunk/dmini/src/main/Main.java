package main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

public class Main {

	/**
	D3 (Examples, Target_Attribute, Attributes)

	 * Create a root node for the tree
	 * If all examples are positive, Return the single-node tree Root, with label = +.
	 * If all examples are negative, Return the single-node tree Root, with label = -.
	 * If number of predicting attributes is empty, then Return the single node tree Root, with label = most common value of the target attribute in the examples.
	 * Otherwise Begin
          o A = The Attribute that best classifies examples.
          o Decision Tree attribute for Root = A.
          o For each possible value, vi, of A,
                + Add a new tree branch below Root, corresponding to the test A = vi.
                + Let Examples(vi), be the subset of examples that have the value vi for A
                + If Examples(vi) is empty
                      # Then below this new branch add a leaf node with label = most common target value in the examples
                + Else below this new branch add the subtree ID3 (Examples(vi), Target_Attribute, Attributes – {A})
	 * End
	 * Return Root
	 */

	
	/**
	 * Step 1: Start with root node (t = 1)
Step 2: Search for a split s* among the set if all
possible candidates s that gives the purest
decrease in impurity.
Step 3: Split node 1 (t = 1) into two nodes (t = 2, t = 3)
using the split s*.
Step 4: Repeat the split search process (t = 2, t = 3) as
indicated in steps 1-3 until the tree growing the
tree growing rules are met.
	 */
	public static void main(String[] args) {
		OurData d = new OurData("adult.data");
		//		d.printRawData();
		OurTreeNode root = RunAlgorithm(d);

	}

	private static OurTreeNode RunAlgorithm(OurData d) {

		OurTreeNode root = new OurTreeNode(null);
		Set<Integer> indicesAlreadySplit = new HashSet<Integer>();
		double []splitIndexAndVal = getSplitIndex(root.get_dataOfNode(),indicesAlreadySplit);


		return null;
	}

	/**
	 * This function goes over all the data it is given from the node and decides what is the best
	 * attribute to split on. Obviously we need to disregard any attribute that has already been split on.
	 * @param getDataOfNode
	 * @param inidicesAlreadySplit
	 * @return
	 */
	private static double[] getSplitIndex(Vector<double[]> data,
			Set<Integer> indicesAlreadySplit) {
		int indexOfBestSplit = -1;
		double valueOfBestSplit = -1;
		double maxValue = -1;
		for (int i = 0; i < data.get(0).length; i++)//for each attribute
		{
			if (!indicesAlreadySplit.contains(i))//making sure we didn't split on this already.
			{
				double []currGini = calcBestGini(data,i);
				if (currGini[0] > valueOfBestSplit)
				{
					valueOfBestSplit = currGini[0];
					indexOfBestSplit = i;
					maxValue = currGini[1];
				}
			}
		}
		double []ans = {valueOfBestSplit,indexOfBestSplit,maxValue};
		return ans;
	}

	private static double[] calcBestGini(Vector<double[]> data, int index) {
		Set<Double> usedValues = new HashSet<Double>();
		double maxValue = -1;
		double maxGini = -1;
		for (int i = 0; i < data.size(); i++)
		{
			double currValue = data.get(i)[index];
			if (!usedValues.contains(currValue))
			{
				int D1 = 0,D2 = 0,D1under = 0,D1over = 0,D2under = 0,D2over = 0;
				for (int j = 0; j < data.size(); j++) //This loop is in order to count D1,D2
				{
					if (data.get(j)[index] <= currValue)
					{
						D1++;
						if (data.get(j)[data.get(i).length-1] == 0)
						{
							D1under++;
						}
						else
						{
							D1over++;
						}
					}
					else
					{
						D2++;
						if (data.get(j)[data.get(i).length-1] == 0)
						{
							D2under++;
						}
						else
						{
							D2over++;
						}
					}
				}
				int D = D1+D2;
				double giniD1,giniD2;
				giniD1 = 1 - (Math.pow(((double)D1under)/D,2) + Math.pow(((double)D1over)/D,2));
				giniD2 = 1 - (Math.pow(((double)D2under)/D,2) + Math.pow(((double)D2over)/D,2));
				double giniAd;
				giniAd = (((double)D1)/D) * giniD1 + (((double)D2)/D) * giniD2;
				if (giniAd > maxGini)
				{
					maxGini = giniAd;
					maxValue = currValue;
				}
				usedValues.add(currValue);
			}
		}
		double []ans = {maxGini,maxValue};
		return ans;
	}

}

