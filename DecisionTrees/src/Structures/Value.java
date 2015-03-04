package Structures;

public class Value {
	private Attribute attribute;
	private String attributeValue;
	public Value(Attribute attribute,String attributeValue){
		this.attribute=attribute;
		this.attributeValue=attributeValue;
	}
	
	public boolean equals(Object obj){
		Value other=(Value)obj;
		return other.attribute.equals(this.attribute) && other.attributeValue.equals(this.attributeValue);
	}
	
	public Attribute getAttribute(){
		return attribute;
	}
	public String getAttributeValue(){
		return attributeValue;
	}
	
	public int hashCode(){
		return attribute.hashCode()+attributeValue.hashCode();
	}
}