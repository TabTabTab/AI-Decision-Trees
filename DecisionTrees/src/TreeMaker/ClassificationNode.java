package TreeMaker;

public class ClassificationNode extends Node {
	
	public ClassificationNode(String name){
		super(name);
	}
	protected String toString(int nbrOfSpaces){
		String spaces="";
		for(int i=0;i<nbrOfSpaces;i++){
			spaces+=" ";
		}

		String stringTree = spaces + name +System.lineSeparator();

		return stringTree;
	}
}
