package Structures;

import java.util.ArrayList;

public class TrainingData {
	private ArrayList<Attribute> attributes;
	private ArrayList<String[]> datas;
	private ArrayList<Example> examples;
	public TrainingData(){
		attributes=new ArrayList<Attribute>();
		datas=new ArrayList<String[]>();
		examples=new ArrayList<Example>();
	}
	/**
	 * Adds the next attribute. Attributes needs to be added in the order in which they are read
	 * @param attribute
	 */
	public void addNextAttribute(Attribute attribute){
		attributes.add(attribute);
	}
	public int nbrOfAttributes(){
		return attributes.size();
	}
	public void addExample(String[] data){
		//we always assume the last value is the classification
		ArrayList<Value> values=new ArrayList<Value>();
		for(int i=0;i<data.length-1;i++){
			data[i]=data[i].trim();
			Value value=new Value(attributes.get(i), data[i]);
		}
		Classification newClassificaiton=new Classification(data[data.length-1].trim());
		Example newExample=new Example(values,newClassificaiton);
		this.examples.add(newExample);
		this.datas.add(data);
	}
	public String toString(){
		String print="";
		print+=attributes.toString();
		print+="\n";
		for(int dataIndex=0;dataIndex<datas.size();dataIndex++){
			String[] currentData=datas.get(dataIndex);
			for(int i=0;i<currentData.length;i++){
				print+=currentData[i]+",";
			}
			print+="\n";
		}
		return print;
	}
}
