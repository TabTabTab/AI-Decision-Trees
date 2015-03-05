package TreeMaker;

public class DecisionTree {
	private String relationshipName;
	private Node head;
	public DecisionTree(String relationshipName,Node head){
		this.relationshipName=relationshipName;
		this.head=head;
	}
	
	public String toString(){
		String treeString="";
		treeString="||||| RelationShipName: "+relationshipName+" |||||\n";			
		return treeString+head.toString(0);
	}
}
