package Structures;

import java.util.ArrayList;

public class TrainingData {
	private ArrayList<Attribute> attributes;
	private ArrayList<Example> examples;
	public TrainingData(){
		attributes=new ArrayList<Attribute>();
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
		for(int i=0;i<data.length;i++){
			data[i]=data[i].trim();
			Value value=new Value(attributes.get(i), data[i]);
			values.add(value);
		}
		Example newExample=new Example(values);
		this.examples.add(newExample);
	}
	public ArrayList<Attribute> getAllAttributes(){
		ArrayList<Attribute> clone = (ArrayList<Attribute>)attributes.clone();
		return clone;
	}
	public ArrayList<Example> getExamples(){
		return examples;
	}
}
