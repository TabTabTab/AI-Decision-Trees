package Main;

import java.io.File;
import java.io.FileNotFoundException;

import Parser.WekaParser;
import Structures.Attribute;
import Structures.TrainingData;
import TreeMaker.Node;
import TreeMaker.TreeMaker;

public class Tester {
	public static void main(String[] args){
		WekaParser wParser=new WekaParser();
		File file=new File("TrainingSet.ARFF");
		try {
			TrainingData trainingData=wParser.parseFile(file);
			Attribute classifier=trainingData.getAllAttributes().get(trainingData.getAllAttributes().size()-1);
			TreeMaker maker=new TreeMaker(classifier);
			Node tree=maker.makeTree(trainingData);
			System.out.println(tree);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
