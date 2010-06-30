package main;

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

	public static void main(String[] args) {
		OurData d = new OurData("adult.data");
		d.printRawData();
	}

}
