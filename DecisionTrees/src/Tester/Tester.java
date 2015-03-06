package Tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Parser.WekaParser;
import Structures.Attribute;
import Structures.TrainingData;
import TreeMaker.DecisionTree;
import TreeMaker.TreeMaker;

public class Tester {
	public static void main(String[] args){
		WekaParser wParser=new WekaParser();
		File file=new File("TrainingSet.ARFF");
		file=getFile();
		try {
			TrainingData trainingData=wParser.parseFile(file);
			
			//Attribute classifier=trainingData.getAllAttributes().get(trainingData.getAllAttributes().size()-1);
			Attribute classifier=getUserChosenClassifier(trainingData.getAllAttributes());
			TreeMaker maker=new TreeMaker(classifier);
			DecisionTree tree=maker.makeTree(trainingData);
			System.out.println(tree);
			checkPrintToFileRequest(tree);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Attribute getUserChosenClassifier(ArrayList<Attribute> attributes) {
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
	    	displayExitOption();
	    	return getUserChosenClassifier(attributes);
	    }
	    int index=indexLookup.indexOf(attributeName);
	    Attribute chosenAttribute=attributes.get(index);
	    return chosenAttribute;
	}
	private static File getFile(){
		File file=new File("TrainingSet.ARFF");
		int answer=JOptionPane.showConfirmDialog(null, "Want to use a custom file?(no results in TrainingSet.ARFF being used)");
		if(answer==1){
			return file;
		}else if(answer!=0){
			displayExitOption();
			return getFile();
		}
		
//		JFileChooser chooser = new JFileChooser();
//	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//	        "arff files", "arff", "ARFF");
//	    chooser.setFileFilter(filter);
//	    int returnVal = chooser.showOpenDialog(null);
//	    if(returnVal != JFileChooser.APPROVE_OPTION) {
//	    	displayExitOption();
//	    	return getFile();
//	    }
	    return getFile("Select a training file","arff files","arff", "ARFF");
	}
	private static File getFile(String dialogTitle,String filetypeNames, String... extentions){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	    		filetypeNames, extentions);
	    chooser.setFileFilter(filter);
	    chooser.setDialogTitle(dialogTitle);
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal != JFileChooser.APPROVE_OPTION) {
	    	displayExitOption();
	    	return getFile(dialogTitle,filetypeNames,extentions);
	    }
	    return chooser.getSelectedFile();
	}
	private static File getOutputFile() throws IOException{
		File outputFile= getFile("Select or create an output file","txt files","txt");
		if(!outputFile.exists()){
			outputFile.createNewFile();
		}
		return outputFile;
	} 
	private static void displayExitOption(){
		int answer=JOptionPane.showConfirmDialog(null, "Do you want to exit?");
		if(answer==0){
			System.exit(0);
		}
	}
	private static void checkPrintToFileRequest(DecisionTree tree) throws IOException{
		int answer=JOptionPane.showConfirmDialog(null, "Do you want to print the tree to a file?");
		if(answer==0){
			File file=getOutputFile();
			try {
				OutputStream fos=getFileStream(file);
				printToStream(fos, tree.toString());
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.exit(0);
		}
	}
	private static OutputStream getFileStream(File file) throws IOException{
		
		FileOutputStream fos=new FileOutputStream(file);
		return fos;
		
	}
	private static void printToStream(OutputStream os,String info) throws IOException{
		os.write(info.getBytes());
	}
}
