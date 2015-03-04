package TreeMaker;

import java.util.ArrayList;

import Structures.Attribute;
import Structures.Classification;
import Structures.Example;
import Structures.TrainingData;
import Structures.Value;

public class TreeMaker {
	public static Node makeTree(TrainingData trainingData){
		
		return DTL(trainingData.getExamples(),trainingData.getValueAttributes(),new ArrayList<Example>());
	}
	private static Node DTL(ArrayList<Example> examples,ArrayList<Attribute> attributes,ArrayList<Example> parentExamples){

		if(examples.isEmpty()){
			return pluralityValue(parentExamples);
		}else if (allHaveSameClassification(examples)){
			return makeClassificationNode(examples.get(0).getClassificaiton());
		}else if (attributes.isEmpty()){
			return pluralityValue(examples);
		}else{
			Attribute mostImportantAttribute = null;
			double maxAttributeImportance=-1.0;
			//System.out.println("nbr of attributes "+attributes.size());
			for(Attribute a : attributes){
				double currentAttributeImportance = importance(a, examples);
				//System.out.println("current attribute importance: "+currentAttributeImportance);
				if(currentAttributeImportance>maxAttributeImportance){
					maxAttributeImportance=currentAttributeImportance;
					mostImportantAttribute = a;
				}
			}
			//System.out.println(maxAttributeImportance);
			//System.out.println("===================NAME: "+ mostImportantAttribute.getName()+"=============");
			//System.out.println("===================IMPORTANCE: "+ maxAttributeImportance+"=============");
			//System.out.println("nbr of attributes "+attributes.size());
			for(Attribute a : attributes){
				double currentAttributeImportance = importance(a, examples);
				//System.out.println("current attribute importance: "+currentAttributeImportance);
				if(currentAttributeImportance==maxAttributeImportance){
					System.out.println("===================NAME: "+ a.getName()+"=============");
					System.out.println("===================IMPORTANCE: "+ maxAttributeImportance+"=============");
				}
			}
			Node tree = new Node(mostImportantAttribute.getName());
			for(String attributeValue : mostImportantAttribute.getPossibleValues()){
				ArrayList<Example> childExamples=new ArrayList<Example>();
				Value v = new Value(mostImportantAttribute,attributeValue);
				for(Example e : examples){
					if (e.hasValue(v)){
						childExamples.add(e);
					}
				}
				attributes.remove(mostImportantAttribute);
				Node subtree = DTL(childExamples,attributes,examples);
				tree.addNeighbour(attributeValue, subtree);
				
				
			}


			return tree;
		}
	}


	private static Node pluralityValue(ArrayList<Example> examples){
		int positiveCount=0;
		int negativeCount=0;
		for(Example e:examples){
			if(e.getClassificaiton().getClassification()){
				positiveCount++;
			}else{
				negativeCount++;
			}
		}
		if(positiveCount>negativeCount){
			return new Node("Yes");
		}else if(positiveCount<negativeCount){
			return new Node("No");
		}else{
			double rand=Math.random();
			if(rand<0.5){
				return new Node("Yes");
			}else {
				return new Node("No");
			}
		}

	}
	private static boolean allHaveSameClassification(ArrayList<Example> examples){
		Classification compareClassification=examples.get(0).getClassificaiton();
		for(int i=1;i<examples.size();i++){
			//if (!examples.get(i).equals(compareClassification)){
			if(!(examples.get(i).getClassificaiton().getClassification()==compareClassification.getClassification())){
				return false;
			}
		}
		return true;
	}

	private static double importance(Attribute attribute,ArrayList<Example> examples){
		//System.out.println("KALL TILL IMPORTANCE");
		//System.out.println("antal exempel "+examples.size());
		int possitiveExamples=0;
		for(Example example:examples){
			if(example.getClassificaiton().getClassification()){
				possitiveExamples++;
			}
		}
		//System.out.println("positiva exmpel: "+possitiveExamples);
		double q=(possitiveExamples+0.0)/examples.size();
		//System.out.println("q: "+q);
		double B=B(q);
		//System.out.println("B: "+B);
		double remainder=0.0;
		for(String attributeValue:attribute.getPossibleValues()){
			//System.out.println("attributevalue: "+attributeValue);
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
			/*System.out.println("valuepercentage: "+valuePercentage);
			System.out.println("posExampleCount: "+positiveExampleCount);
			System.out.println("pos doubleExampleCount "+(double)positiveExampleCount);
			System.out.println(((double)positiveExampleCount)/hasAttributeValueCount);
			System.out.println("Bvalue: "+B(((double)positiveExampleCount)/hasAttributeValueCount));
			System.out.println("hasAttrPercentage: "+hasAttributeValueCount);*/
			if(valuePercentage!=0.0 && positiveExampleCount!=0){
				//System.out.println("i den koola ifsatsen");
				remainder += (valuePercentage * B(((double)positiveExampleCount)/hasAttributeValueCount));				
			}
			//System.out.println("remainder is: "+remainder);
		}
		//System.out.println("remainder is: "+remainder);
		return B-remainder;
	}
	private static double secondLog(double val){
		return Math.log(val)/Math.log(2);
	}

	private static double B(double q){
		if(q==0.0){
			return 0;
		}
		if(q==1.0){
			return 0;
		}
		//q = 0
		//q = 1
		
		//System.out.println("q in B "+q);
		double leftPart=q*secondLog(q);
		double rightpart=(1.0-q)*secondLog(1.0-q);
		//System.out.println("left: "+leftPart);
		//System.out.println("right: "+rightpart);
		return -(q*secondLog(q)+(1.0-q)*secondLog(1.0-q));

	}
	private static Node makeClassificationNode(Classification classification){
		if(classification.getClassification()){
			return new Node("Yes");
		}else {
			return new Node("No");
		}
	}
}
