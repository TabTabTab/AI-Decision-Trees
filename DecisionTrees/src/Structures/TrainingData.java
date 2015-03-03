package Structures;

import java.util.ArrayList;

public class TrainingData {
	private ArrayList<Attribute> attributes;
	private ArrayList<String[]> datas;
	public TrainingData(){
		attributes=new ArrayList<Attribute>();
		datas=new ArrayList<String[]>();
	}
	/**
	 * Adds the next attribute. Attributes needs to be added in the order in which they are read
	 * @param attribute
	 */
	public void addNextAttribute(Attribute attribute){
		attributes.add(attribute);
	}
	public int nbrOfAttributes(){
		return attributes.size();
	}
	public void addData(String[] data){
		for(int i=0;i<data.length;i++){
			data[i]=data[i].trim();
		}
		this.datas.add(data);
	}
	public String toString(){
		String print="";
		print+=attributes.toString();
		print+="\n";
		for(int dataIndex=0;dataIndex<datas.size();dataIndex++){
			String[] currentData=datas.get(dataIndex);
			for(int i=0;i<currentData.length;i++){
				print+=currentData[i]+",";
			}
			print+="\n";
		}
		return print;
	}
}
