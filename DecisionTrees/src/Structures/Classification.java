package Structures;

public class Classification {
	private boolean value;
	public static final String TRUEVALUE="yes";
	public static final String FALSEVALUE="no";
	public Classification(String value){
		this.value=value.equals(TRUEVALUE);
	}
	
	public boolean equals(Object obj){
		Classification other=(Classification)obj;
		return other.value==this.value;
	}
	
	public boolean getClassification(){
		return value;
	}
}
