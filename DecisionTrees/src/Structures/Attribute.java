package Structures;

import java.util.HashSet;

public class Attribute {
	private String name;
	private HashSet<String> possibleValues;
	public Attribute(String name,HashSet<String> possibleValues){
		this.possibleValues=possibleValues;
		this.name=name;
		
	}
	public Attribute(String name,String[] possibleValues){
		HashSet<String> possibleValuesSet=new HashSet<String>();
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
	public HashSet<String> getPossibleValues(){
		return possibleValues;
	}
}
