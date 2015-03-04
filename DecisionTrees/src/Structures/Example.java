package Structures;

import java.util.ArrayList;

public class Example {
	ArrayList<Value> values;
	Classification classification;
	public Example(ArrayList<Value> values,Classification classification){
		this.values=values;
		this.classification=classification;
	}
	public Classification getClassificaiton(){
		return classification;
	}
	
	public ArrayList<Value> getValues(){
		return values;
	}
	
	
	public boolean hasValue(Value v){
		boolean hasValue=values.indexOf(v)!=-1;
		return hasValue;
	}
}
