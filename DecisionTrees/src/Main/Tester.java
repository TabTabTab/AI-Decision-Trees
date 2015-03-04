package Main;

import java.io.File;
import java.io.FileNotFoundException;

import Parser.WekaParser;
import Structures.TrainingData;
import TreeMaker.Node;
import TreeMaker.TreeMaker;

public class Tester {
	public static void main(String[] args){
		WekaParser wParser=new WekaParser();
		File file=new File("TrainingSet.ARFF");
		try {
			TrainingData trainingData=wParser.parseFile(file);
			Node tree=TreeMaker.makeTree(trainingData);
			System.out.println(tree);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
