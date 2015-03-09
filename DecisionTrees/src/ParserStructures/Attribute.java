package ParserStructures;

import java.util.ArrayList;

public class Attribute {
	private String name;
	private ArrayList<String> possibleValues;
	public Attribute(String name,String[] possibleValues){
		ArrayList<String> possibleValuesSet=new ArrayList<String>();
		for(String value:possibleValues){
			value=value.trim();
			possibleValuesSet.add(value);
		}
		this.possibleValues=possibleValuesSet;
		this.name=name;
	}
	
	public int hashCode(){
		return possibleValues.hashCode()+name.hashCode();
	}
	public String toString(){
		return name;
	}
	public String getName(){
		return name;
	}
	public boolean equals(Object obj){
		
		Attribute other=(Attribute)obj;
		return other.name.equals(this.name) && other.possibleValues.equals(this.possibleValues);
	}
	public int nbrOfPossibleValues(){
		return possibleValues.size();
	}
	public ArrayList<String> getPossibleStringValues(){
		return possibleValues;
	}
	public ArrayList<Value> getPossibleValues(){
		ArrayList<Value> values=new ArrayList<Value>();
		for(String value:possibleValues){
			Value v=new Value(this,value);
			values.add(v);
		}
		return values;
	}
}
