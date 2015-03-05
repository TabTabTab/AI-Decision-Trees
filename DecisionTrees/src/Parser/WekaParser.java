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
		Scanner scanner=new Scanner(file);
		
		Pattern relationPattern=Pattern.compile("@RELATION (.*)",Pattern.CASE_INSENSITIVE);
		//get the relationsnip text and throw it away
		String relationName="undefined";
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			Matcher matcher=relationPattern.matcher(line);
			if(matcher.find()){
				relationName=matcher.group(1).trim();
				break;
			}
		}
		
		TrainingData trainingData=new TrainingData(relationName);
		
		
		Pattern attributePattern=Pattern.compile("@ATTRIBUTE.*",Pattern.CASE_INSENSITIVE);
		//now get to the attributes
		while(!scanner.hasNext(attributePattern)){
			scanner.nextLine();
		}
		
		
		//get all attributes
		Pattern pattern = Pattern.compile("@ATTRIBUTE(.*)\\{(.*)\\}",Pattern.CASE_INSENSITIVE);
		while(scanner.hasNext(attributePattern)){
			String attributeLine=scanner.nextLine();
			//System.out.println(attributeLine);
			//System.out.println(pattern);
			Matcher matcher = pattern.matcher(attributeLine);
			if(matcher.find()){
				String attributeName=matcher.group(1).trim();
				String[] possibleValues=matcher.group(2).split(",");
				Attribute attribute=new Attribute(attributeName, possibleValues);
				trainingData.addNextAttribute(attribute);
			}
		}
		//now look for the examples
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			if(line.trim().equals("@DATA") || line.trim().equals("@data")){
				break;
			}
		}
		int nbrOfAttributes=trainingData.nbrOfAttributes();
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			String[] exampleData=line.split(",");
			if (exampleData.length==nbrOfAttributes){
				trainingData.addExample(exampleData);
			}
		}
		scanner.close();
		return trainingData;
	}
	
	
	
}
