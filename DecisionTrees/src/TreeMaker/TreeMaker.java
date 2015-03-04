package TreeMaker;

import java.util.ArrayList;

import Structures.Attribute;
import Structures.Classification;
import Structures.Example;
import Structures.TrainingData;
import Structures.Value;

public class TreeMaker {
	public static DecisionTree makeTree(TrainingData trainingData){
		return null;
	}
	private static Object DTL(int currentExampleIndex,ArrayList<Example> examples,ArrayList<Attribute> attributes){
		//returns all kinds of objects :D needs to be fixed
		if(examples.size()>=currentExampleIndex){
			return pluralityValue(examples);
		}else if (allHaveSameClassification(currentExampleIndex,examples)){
			return examples.get(currentExampleIndex).getClassificaiton();
		}else if (attributes.isEmpty()){
			return pluralityValue(examples);
		}else{
			
		}
		return null;
	}
	
	
	private static DecisionTree pluralityValue(ArrayList<Example> examples){
		//TODO: Implement and chenge type
		return null;
	}
	private static boolean allHaveSameClassification(int currentExampleIndex,ArrayList<Example> examples){
		Classification compareClassification=examples.get(currentExampleIndex).getClassificaiton();
		for(int i=currentExampleIndex+1;i<examples.size();i++){
			if (!examples.get(i).equals(compareClassification)){
				return false;
			}
		}
		return true;
	}
	
	private static double importance(Attribute attribute,ArrayList<Example> examples){
		double possitiveExamples=0;
		for(Example example:examples){
			if(example.getClassificaiton().getClassification()){
				possitiveExamples++;
			}
		}
		double q=possitiveExamples/examples.size();
		
		double B=B(q);
		
		double remainder=0;
		for(String attributeValue:attribute.getPossibleValues()){
			int hasAttributeValueCount = 0;
			int positiveExampleCount=0;
			Value v = new Value(attribute,attributeValue);
			for(Example e : examples){
				if (e.hasValue(v)){
					hasAttributeValueCount++;
					if(e.getClassificaiton().getClassification()){
						positiveExampleCount++;
					}
				}
			}
			double valuePercentage = ((double)(hasAttributeValueCount)) / examples.size();
			remainder += (valuePercentage * B(((double)positiveExampleCount)/hasAttributeValueCount));
		}
		
		return B-remainder;
	}
	private static double secondLog(double val){
		return Math.log(val)/Math.log(2);
	}
	
	private static double B(double q){
		return -(q*secondLog(q)+(1-q)*secondLog(1-q));
		
	}
}
