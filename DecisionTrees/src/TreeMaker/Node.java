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
	protected String toString(int nbrOfSpaces){
		String spaces="";
		for(int i=0;i<nbrOfSpaces;i++){
			spaces+=" ";
		}
		String stringTree =System.lineSeparator();
		for(String edge:neighbours.keySet()){
			stringTree+=spaces+name +" = "+edge+" : ";
			stringTree+=neighbours.get(edge).toString(nbrOfSpaces+1);
		}
		stringTree+="\n";
		return stringTree;
	}
}
