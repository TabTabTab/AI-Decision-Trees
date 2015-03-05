package TreeMaker;

public class EndNode extends Node {
	
	public EndNode(String name){
		super(name);
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
		String stringTree =tabs + name +"\n";
//		for(String edge:neighbours.keySet()){
//			stringTree+=neighbours.get(edge).toString(nbrOfTabs+1);
//		}
		return stringTree;
	}
}
