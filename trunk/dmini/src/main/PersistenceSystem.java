package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * This needs a lot of work, I don't think that Xstream is the correct tool for this application.
 * Since every node keeps also a lot of data with it the data file can become very very big which will make
 * running time extremly long, longer than just re-computing everything. There is no need for persistence if
 * the run-time is longe.
 * Maybe we should create a "watered down" version of the tree to save since for the testing and usage of the tree
 * most of the data is irrelivant. 
 * 
 * I tried to water it down I still got a file that is about 60M big, that's way to big.
 * @author Noam
 *
 */
public class PersistenceSystem {
	
	public static void saveToFile(String fileName,OurTreeNode root)
	{
		OurTreeNode wateredDownRoot = root.waterDownCopy();
		try
		{
			String serializedTree = serializeObject(wateredDownRoot);
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(serializedTree); 
			out.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static OurTreeNode loadFromFile(String fileName)
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(fileName)); 
			String serializedRoot = "";
			String instr = null;
			while ((instr = in.readLine()) != null)
			{
				serializedRoot += instr;
			}
			in.close();
			OurTreeNode loadedRoot = (OurTreeNode)deserializeObject(serializedRoot);
			return loadedRoot;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static String serializeObject(Object o)
	{
		XStream xstream = new XStream(new DomDriver());
		return xstream.toXML(o);
	}

	public static Object deserializeObject(String s)
	{
		XStream xstream = new XStream(new DomDriver());
		return xstream.fromXML(s);
	}
}
