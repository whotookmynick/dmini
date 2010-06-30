package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class OurData {

	//	double [][] _rawData;//The raw data encoded into double.
	Vector<double[]> _rawData;
	Map<String,Double> _classes; //<= 50K : 0 , >50K : 1

	Map<Integer, Map<String, Double>> _encodingMap; 
	Map<String, Double> _workclass;
	Map<String, Double> _education;
	Map<String, Double> _marital_status;
	Map<String, Double> _occupation;
	Map<String, Double> _relationship;
	Map<String, Double> _race;
	Map<String, Double> _sex;
	Map<String, Double> _native_country;

	public OurData(String fileName)
	{
		initMaps();
		_rawData = new Vector<double[]>();
		try { 
			BufferedReader in = new BufferedReader(new FileReader(fileName)); 
			String instr = null;
			while ((instr = in.readLine()) != null) 
			{ 
				if (instr.contains("?") | instr.isEmpty())
				{
					continue;
				}
				String attributes[] = instr.split(",");
				double encattributes[] = new double[attributes.length];
				for (int i = 0; i < attributes.length-1;i++)
				{
					double currAttr = encodeAttribute(i,attributes[i]);
					encattributes[i] = currAttr;
				}
				//Now we need to add the class itself.
				String classStr = attributes[attributes.length-1].trim();
				Double temp = _classes.get(classStr);
				if (temp == null)
				{
					System.out.println("problem");
				}
				encattributes[encattributes.length-1] = _classes.get(attributes[attributes.length-1].trim());
				
				_rawData.add(encattributes);
			} 
			in.close(); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace();
		} 
	}

	private void initMaps() {
		_classes = new HashMap<String, Double>();
		_classes.put("<=50K",0.0);
		_classes.put(">50K",1.0);
		
		_workclass = new HashMap<String, Double>();
		_education = new HashMap<String, Double>();
		_marital_status = new HashMap<String, Double>();
		_occupation = new HashMap<String, Double>();
		_relationship = new HashMap<String, Double>();
		_race = new HashMap<String, Double>();
		_sex = new HashMap<String, Double>();
		_native_country = new HashMap<String, Double>();
		_encodingMap = new HashMap<Integer, Map<String,Double>>();
		_encodingMap.put(1, _workclass);
		_encodingMap.put(3,_education);
		_encodingMap.put(5,_marital_status);
		_encodingMap.put(6,_occupation);
		_encodingMap.put(7,_relationship);
		_encodingMap.put(8,_race);
		_encodingMap.put(9,_sex);
		_encodingMap.put(13,_native_country);
	}

	private double encodeAttribute(int i, String attr) {
		double ans;
		Map<String,Double> currMap = _encodingMap.get(i);
		if (currMap == null)//The variable is contiuous and given in double format
		{
			ans = Double.parseDouble(attr);
		}
		else //Need to get the encoding from the relevant Map
		{
			Double currd = currMap.get(attr);
			if (currd == null)
			{
				ans = addToMap(currMap,attr);
			}
			else
			{
				ans = currd.doubleValue();
			}
		}
		return ans;

	}

	/**
	 * Note that this method is called only if attr is not in currMap so there is no need to check this again.
	 * @param currMap
	 * @param attr
	 * @return
	 */
	private double addToMap(Map<String, Double> currMap, String attr) {
		double ans = 1;
		if (!currMap.isEmpty())
		{
			Collection<Double> vals = currMap.values();
			ans = Collections.max(vals)+1;
		}
		currMap.put(attr,ans);

		return ans;
	}

	public void printRawData()
	{
		for (int i = 0; i < _rawData.size();i++)
		{
			System.out.print(i + ")\t");
			double []currRow = _rawData.get(i);
			for (int j = 0; j < currRow.length;j++)
			{
				System.out.print(currRow[j] + "\t , ");
			}
			System.out.println();
		}
	}

}
