package Structures;

public class Classification {
	private boolean value;
	public static final String TRUEVALUE="Yes";
	public static final String FALSEVALUE="No";
	public Classification(String value){
		this.value=value.equals(TRUEVALUE);
	}
	
	public boolean equals(Object obj){
		
		Classification other=(Classification)obj;
		return other.value==this.value;
	}
	
	public boolean getBooleanValue(){
		return value;
	}
}
