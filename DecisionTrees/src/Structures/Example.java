package Structures;

import java.util.ArrayList;

public class Example {
	private ArrayList<Value> values;
	private Classification classification;
	public Example(ArrayList<Value> values,Classification classification){
		this.values=values;
		this.classification=classification;
	}
	public boolean getClassificaitonValue(){
		return classification.getBooleanValue();
	}
	
	public ArrayList<Value> getValues(){
		return values;
	}
	public boolean hasValue(Value v){
		boolean hasValue=values.indexOf(v)!=-1;
		return hasValue;
	}
}
