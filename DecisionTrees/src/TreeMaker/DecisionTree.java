package TreeMaker;

import Structures.Attribute;

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
		treeString="||||| RelationShipName: "+relationshipName+" |||||\n";	
		treeString+="    classifier: "+classsifier.getName() + "\n";
		treeString+="    possible classificatiovalues: "+classsifier.getPossibleStringValues() + "\n";
		return treeString+head.toString(0);
	}
	
}
