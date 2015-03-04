package TreeMaker;

import java.util.HashMap;



public class Node {

	private String name;
	private HashMap<String,Node> neighbours;
	public Node(String name){
		this.name=name;
		neighbours = new HashMap<String,Node>();
	}
	public void addNeighbour(String edgeValue, Node node){
		neighbours.put(edgeValue,node);
	}

	public String toString(){
		String stringTree ="";
		stringTree+=name;
		stringTree+="\n";
		for(String edge:neighbours.keySet()){
			stringTree+=edge+"\t";
		}
		stringTree+="\n";
		for(String edge:neighbours.keySet()){
			stringTree+=neighbours.get(edge);
		}
		return stringTree;
	}
}
