package Main;

import java.io.File;
import java.io.FileNotFoundException;

import Parser.WekaParser;

public class Tester {
	public static void main(String[] args){
		WekaParser wParser=new WekaParser();
		File file=new File("TrainingSet.ARFF");
		try {
			System.out.println(wParser.parseFile(file));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
