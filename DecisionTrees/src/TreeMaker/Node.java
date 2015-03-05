package TreeMaker;

import java.util.HashMap;



public class Node {

	protected String name;
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
	protected String toString(int nbrOfTabs){
		String tabs="";
		for(int i=0;i<nbrOfTabs;i++){
			tabs+=" ";
		}
//		for(int i=0;i<nbrOfTabs;i++){
//			tabs+="|";
//		}
		//tabs+=" ";
		String stringTree ="\n";
		for(String edge:neighbours.keySet()){
			stringTree+=tabs+name +" = "+edge+" : ";
			stringTree+=neighbours.get(edge).toString(nbrOfTabs+1);
		}
		stringTree+="\n";
//		for(String edge:neighbours.keySet()){
//			stringTree+=neighbours.get(edge).toString(nbrOfTabs+1);
//		}
		return stringTree;
	}
	protected boolean isEndNode(){
		return false;
	}
}
