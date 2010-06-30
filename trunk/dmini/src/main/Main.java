package main;

import java.util.Collection;
import java.util.Collections;
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
		Set<Set<Double> > temp = getAllSubsets(d.getCategoricalValues(8));
		for (Set<Double> t : temp)
			System.out.println(t);
		System.out.println("size of subset = " + temp.size());

		System.out.println("finished");
		OurTreeNode root = RunAlgorithm(d);

	}

	private static OurTreeNode RunAlgorithm(OurData d) {

		Set<Integer> indicesAlreadySplit = new HashSet<Integer>();
		SplitIndexReturnVal splitIndex = getSplitIndex(d.get_rawData(),indicesAlreadySplit,d);
		indicesAlreadySplit.add(splitIndex.indexOfBestSplit);
		OurTreeNode root;
		if (d.isContiuous(splitIndex.indexOfBestSplit))
		{
			root = new ContinuousTreeNode(null, d.get_rawData(), splitIndex.valueOfBestSplit);
		}
		boolean stop = false;
		while (!stop)
		{

		}
		
		return null;
	}

	/**
	 * This function goes over all the data it is given from the node and decides what is the best
	 * attribute to split on. Obviously we need to disregard any attribute that has already been split on.
	 * @param getDataOfNode
	 * @param inidicesAlreadySplit
	 * @return
	 */
	private static SplitIndexReturnVal getSplitIndex(Vector<double[]> data,
			Set<Integer> indicesAlreadySplit,OurData originalData) {
		double maxGini = -1;
		SplitIndexReturnVal ans = null;

		for (int i = 0; i < data.get(0).length; i++)//for each attribute
		{
			if (!indicesAlreadySplit.contains(i))//making sure we didn't split on this already.
			{
				SplitIndexReturnVal currGini;
				if (originalData.isContiuous(i))
				{
					currGini = calcBestGiniContinuous(data,i);
				}
				else
				{
					currGini = calcBestGiniCatagorical(data,i,originalData);
				}

				if (currGini.gini > maxGini)//updating max
				{
					maxGini = currGini.gini;
					ans = currGini;
				}

			}
		}


		return ans;
	}

	private static SplitIndexReturnVal calcBestGiniContinuous(Vector<double[]> data, int index) {
		Set<Double> usedValues = new HashSet<Double>();
		double maxValue = -1;
		double maxGini = -1;
		SplitPointDeterminator spd = new ContinuousSplitPointDeterminator();
		for (int i = 0; i < data.size(); i++)
		{
			double currValue = data.get(i)[index];
			if (!usedValues.contains(currValue))
			{
				double giniAd = calcGiniAD(data, index,  currValue,spd);
				if (giniAd > maxGini)
				{
					maxGini = giniAd;
					maxValue = currValue;
				}
				usedValues.add(currValue);
			}
		}
		Set<Double> valSet = new HashSet<Double>();
		valSet.add(maxValue);
		SplitIndexReturnVal ans = new SplitIndexReturnVal(valSet,index,maxGini);
		return ans;
	}
	
	private static SplitIndexReturnVal calcBestGiniCatagorical(
			Vector<double[]> data, int index, OurData originalData) {

		Set<Double> maxValue = new HashSet<Double>();
		double maxGini = -1;
		Collection<Double> catagoricalValues = originalData.getCategoricalValues(index);
		Set<Set<Double> > subsets = getAllSubsets(catagoricalValues);
		for (Set<Double> currSet : subsets)
		{
			if ( !currSet.isEmpty() & currSet.size() < catagoricalValues.size())
			{
				SplitPointDeterminator spd = new CatagoricalSplitPointDeterminator(currSet);
				double giniAd = calcGiniAD(data, index, -1,spd);
				if (giniAd > maxGini)
				{
					maxGini = giniAd;
					maxValue = currSet;
				}
				
			}
		}
		SplitIndexReturnVal ans = new SplitIndexReturnVal(maxValue,index,maxGini);
		return ans;
	}


	private static double calcGiniAD(Vector<double[]> data, int index, 
			double currValue,SplitPointDeterminator spd) {
		int D1 = 0,D2 = 0,D1under = 0,D1over = 0,D2under = 0,D2over = 0;
		for (int j = 0; j < data.size(); j++) //This loop is in order to count D1,D2
		{
			if (spd.isLeftOfSplit(data.get(j)[index],currValue))
//			if (data.get(j)[index] <= currValue)
			{
				D1++;
				if (data.get(j)[data.get(0).length-1] == 0)
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
				if (data.get(j)[data.get(0).length-1] == 0)
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
		return giniAd;
	}

	private static Set<Set<Double>> getAllSubsets(Collection<Double> originalSet) {
		Set<Set<Double> > ans = new HashSet<Set<Double>>();
		if (originalSet.size() == 1)
		{
			Set<Double> emptySet = new HashSet<Double>();
			Set<Double> lastSet = new HashSet<Double>(originalSet);
			ans.add(lastSet);
			ans.add(emptySet);
		}
		else
		{
			Double e = originalSet.iterator().next();
			Collection<Double> tempSet = new LinkedList<Double>(originalSet);
			tempSet.remove(e);
			Set<Set<Double>> temp = getAllSubsets(tempSet);
			tempSet.add(e);
			for (Set<Double> currSet : temp)
			{
				ans.add(currSet);
				Set<Double> newCurrSet = new HashSet<Double>(currSet);
				newCurrSet.add(e);
				ans.add(newCurrSet);
			}
		}
		return ans;
	}

	private static class SplitIndexReturnVal
	{
		public Set<Double> valueOfBestSplit;
		public int indexOfBestSplit;
		public double gini;

		public SplitIndexReturnVal(Set<Double> v,int ind,double g)
		{
			valueOfBestSplit = v; indexOfBestSplit = ind; gini = g;
		}
	}

}

