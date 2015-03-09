package TreeMaker;

import ParserStructures.Attribute;

public class DecisionTree {
	private String relationshipName;
	private Node head;
	private Attribute classsifier;
	public DecisionTree(String relationshipName,Node head,Attribute classifier){
		this.relationshipName=relationshipName;
		this.head=head;
		this.classsifier=classifier;
	}
	
	public String toString(){
		String treeString="";
		treeString="||||| RelationShipName: "+relationshipName+" |||||"+System.lineSeparator();	
		treeString+="    classifier: "+classsifier.getName() +System.lineSeparator();
		treeString+="    possible classificatiovalues: "+classsifier.getPossibleStringValues() +System.lineSeparator();
		return treeString+head.toString(0);
	}
	
}
