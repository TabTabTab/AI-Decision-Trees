package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
			
			//Attribute classifier=trainingData.getAllAttributes().get(trainingData.getAllAttributes().size()-1);
			Attribute classifier=getUserChosenClassifier(trainingData.getAllAttributes());
			TreeMaker maker=new TreeMaker(classifier);
			Node tree=maker.makeTree(trainingData);
			System.out.println(tree);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Attribute getUserChosenClassifier(ArrayList<Attribute> attributes) {
	    String[] choices = new String[attributes.size()];//{ "A", "B", "C", "D", "E", "F" };
	    ArrayList<String> indexLookup=new ArrayList<>();
	    for(int i=0;i<choices.length;i++){
	    	choices[i]=attributes.get(i).getName();
	    	indexLookup.add(choices[i]);
	    }
	    String attributeName = (String) JOptionPane.showInputDialog(null, "Classifier",
	        "Pick your desired classifier", JOptionPane.QUESTION_MESSAGE, null, // Use
	                                                                        // default
	                                                                        // icon
	        choices, // Array of choices
	        choices[0]); // Initial choice
	    
	    if(attributeName==null){
	    	return getUserChosenClassifier(attributes);
	    }
	    int index=indexLookup.indexOf(attributeName);
	    Attribute chosenAttribute=attributes.get(index);
	    return chosenAttribute;
	}
}
