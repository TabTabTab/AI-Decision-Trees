package Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Structures.Attribute;
import Structures.TrainingData;

public class WekaParser {
	public WekaParser(){
		
	}
	public TrainingData parseFile(File file) throws FileNotFoundException{
		TrainingData trainingData=new TrainingData();
		Scanner scanner=new Scanner(file);
		
		Pattern attributePattern=Pattern.compile("@ATTRIBUTE");
		//get all attributes
		while(scanner.hasNext(attributePattern)){
			String attributeLine=scanner.nextLine();
			//System.out.println(attributeLine);
			Pattern pattern = Pattern.compile("@ATTRIBUTE(.*)\\{(.*)\\}");
			//System.out.println(pattern);
			Matcher matcher = pattern.matcher(attributeLine);
			if(matcher.find()){
				String attributeName=matcher.group(1).trim();
				String[] possibleValues=matcher.group(2).split(",");
				Attribute attribute=new Attribute(attributeName, possibleValues);
				trainingData.addNextAttribute(attribute);
			}
		}
		//now look for the data
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			if(line.trim().equals("@DATA")){
				break;
			}
		}
		int nbrOfAttributes=trainingData.nbrOfAttributes();
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			String[] data=line.split(",");
			if (data.length==nbrOfAttributes){
				trainingData.addExample(data);
			}
		}
		scanner.close();
		return trainingData;
	}
	
	
	
}
