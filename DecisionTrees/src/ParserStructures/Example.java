package ParserStructures;

import java.util.ArrayList;

public class Example {
	private ArrayList<Value> values;
	private Value classifierValue;
	public Example(ArrayList<Value> values){
		this.values=values;
		this.classifierValue=null;
	}
	public ArrayList<Value> getValues(){
		return values;
	}
	public boolean hasValue(Value v){
		return values.indexOf(v)!=-1;
	}
	public void setClassifier(Attribute classifier){
		for(Value v:values){
			if(v.getAttribute().equals(classifier)){
				classifierValue=v;
				break;
			}
		}
		values.remove(classifierValue);
	}
	public Value getClassificationValue(){
		return classifierValue;
	}
}
