package TreeMaker;

import java.util.HashMap;



public class Node {

	private String name;
	private HashMap<String,Node> neighbours;
	private String relationshipName;
	public Node(String name){
		this.name=name;
		neighbours = new HashMap<String,Node>();
		relationshipName=null;
	}
	public void setRelationshipName(String name){
		relationshipName=name;
	}
	public void addNeighbour(String edgeValue, Node node){
		neighbours.put(edgeValue,node);
	}

	public String toString(){
		String treeString="";
		if(relationshipName!=null){
			treeString="||||| RelationShipName: "+relationshipName+" |||||\n";			
		}
		return treeString+toString(0);
	}
	private String toString(int nbrOfTabs){
		String tabs="";
		for(int i=0;i<nbrOfTabs;i++){
			tabs+="\t";
		}
		for(int i=0;i<nbrOfTabs;i++){
			tabs+="|";
		}
		tabs+=" ";
		String stringTree =tabs;
		stringTree+=name;
		stringTree+=("\n");
		for(String edge:neighbours.keySet()){
			stringTree+=tabs+edge+" = ";
			stringTree+=neighbours.get(edge).toString(nbrOfTabs+1);
		}
		stringTree+="\n";
//		for(String edge:neighbours.keySet()){
//			stringTree+=neighbours.get(edge).toString(nbrOfTabs+1);
//		}
		return stringTree;
	}
}
