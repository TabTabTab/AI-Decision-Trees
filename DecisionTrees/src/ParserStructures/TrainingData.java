package ParserStructures;

import java.util.ArrayList;

public class TrainingData {
	private ArrayList<Attribute> attributes;
	private ArrayList<Example> examples;
	private String relationshipName;
	public TrainingData(String name){
		this.relationshipName=name;
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
		@SuppressWarnings("unchecked")
		ArrayList<Attribute> clone = (ArrayList<Attribute>)attributes.clone();
		return clone;
	}
	public ArrayList<Example> getExamples(){
		return examples;
	}
	public String getRelationshipName(){
		return relationshipName;
	}
}
