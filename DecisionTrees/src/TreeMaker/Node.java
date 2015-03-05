package TreeMaker;

import java.util.HashMap;



public class Node {

	protected String name;
	private HashMap<String,Node> neighbours;
	public Node(String name){
		this.name=name;
		neighbours = new HashMap<String,Node>();
	}
	public void addNeighbour(String edgeValue, Node node){
		neighbours.put(edgeValue,node);
	}
	protected String toString(int nbrOfTabs){
		String tabs="";
		for(int i=0;i<nbrOfTabs;i++){
			tabs+=" ";
		}
		String stringTree ="\n";
		for(String edge:neighbours.keySet()){
			stringTree+=tabs+name +" = "+edge+" : ";
			stringTree+=neighbours.get(edge).toString(nbrOfTabs+1);
		}
		stringTree+="\n";
		return stringTree;
	}
}
