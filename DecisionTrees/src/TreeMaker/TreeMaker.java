package TreeMaker;

import java.util.ArrayList;
import java.util.Collections;

import Structures.Attribute;
import Structures.Classification;
import Structures.Example;
import Structures.TrainingData;
import Structures.Value;

public class TreeMaker {
	private Attribute classifier;
	public TreeMaker(Attribute classifier){
		this.classifier=classifier;
	}
	public Node makeTree(TrainingData trainingData){
		ArrayList<Attribute> attributes=trainingData.getAllAttributes();
		ArrayList<Example> examples=trainingData.getExamples();
		for(Example e:examples){
			e.setClassifier(classifier);
		}
		attributes.remove(classifier);
		Node tree=DTL(examples,attributes,new ArrayList<Example>());
		tree.setRelationshipName(trainingData.getRelationshipName());
		return tree;
	}
	private Node DTL(ArrayList<Example> examples,ArrayList<Attribute> attributes,ArrayList<Example> parentExamples){

		if(examples.isEmpty()){
			return pluralityValue(parentExamples);
		}else if (allHaveSameClassification(examples)){
			return makeClassificationNode(examples.get(0).getClassificationValue());
		}else if (attributes.isEmpty()){
			return pluralityValue(examples);
		}else{
			Attribute mostImportantAttribute = null;
			double maxAttributeImportance=Double.NEGATIVE_INFINITY;
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
			for(Value attributeValue : mostImportantAttribute.getPossibleValues()){
				ArrayList<Example> childExamples=new ArrayList<Example>();
				for(Example e : examples){
					if (e.hasValue(attributeValue)){
						childExamples.add(e);
					}
				}
				attributes.remove(mostImportantAttribute);
				Node subtree = DTL(childExamples,attributes,examples);
				tree.addNeighbour(attributeValue.getAttributeValue(), subtree);
				
				
			}


			return tree;
		}
	}


	private Node pluralityValue(ArrayList<Example> examples){
		ArrayList<Value> possibleClassifications=classifier.getPossibleValues();
		ArrayList<Integer> classificationCount=new ArrayList<Integer>();
		for(int i=0;i<possibleClassifications.size();i++){
			classificationCount.add(0);
		}
		for(Example e:examples){
			int index=possibleClassifications.indexOf(e.getClassificationValue());
			int oldCount=classificationCount.get(index);
			classificationCount.set(index,oldCount+1);
		}
		
		int maxCount=Integer.MIN_VALUE;
		for(int i=0;i<classificationCount.size();i++){
			if(classificationCount.get(i)>maxCount){
				maxCount=classificationCount.get(i);
			}
		}
		//check if there are multiple max indices, if so, choose one at random		
		ArrayList<Integer> maxIndices=new ArrayList<Integer>();
		for(int i=0;i<classificationCount.size();i++){
			if(classificationCount.get(i)==maxCount){
				maxIndices.add(i);
			}
		}
		int indice=(int)(Math.random()*maxIndices.size());
		
		return makeClassificationNode(possibleClassifications.get(indice));

	}
	private boolean allHaveSameClassification(ArrayList<Example> examples){
		Value firstClassificationValue=examples.get(0).getClassificationValue();
		for(int i=1;i<examples.size();i++){
			//if (!examples.get(i).equals(compareClassification)){
			if(!(examples.get(i).getClassificationValue().equals(firstClassificationValue))){
				return false;
			}
		}
		return true;
	}

	private double importance(Attribute attribute,ArrayList<Example> examples){
		//System.out.println("KALL TILL IMPORTANCE");
		//System.out.println("antal exempel "+examples.size());
//		int possitiveExamples=0;
//		for(Example example:examples){
//			if(example.getClassificaitonValue()){
//				possitiveExamples++;
//			}
//		}
		ArrayList<Value> possibleClassifications=classifier.getPossibleValues();
		ArrayList<Integer> classificationCount=new ArrayList<Integer>();
		for(int i=0;i<possibleClassifications.size();i++){
			classificationCount.add(0);
		}
		for(Example e:examples){
			int index=possibleClassifications.indexOf(e.getClassificationValue());
			int oldCount=classificationCount.get(index);
			classificationCount.set(index,oldCount+1);
		}
			
		//System.out.println("q: "+q);
		double B=B(classificationCount);
		//System.out.println("B: "+B);
		double remainder=0.0;
		ArrayList<Value> possibleValues=attribute.getPossibleValues();
		for(int vIndex=0;vIndex<possibleValues.size();vIndex++){
			Value attributeValue=possibleValues.get(vIndex);
			//System.out.println("attributevalue: "+attributeValue);
			classificationCount=new ArrayList<Integer>();
			for(int i=0;i<possibleClassifications.size();i++){
				classificationCount.add(0);
			}
			int hasAttributeValueCount=0;
			for(Example e : examples){
				if (e.hasValue(attributeValue)){
					hasAttributeValueCount++;
					int index=possibleClassifications.indexOf(e.getClassificationValue());
					int oldCount=classificationCount.get(index);
					classificationCount.set(index,oldCount+1);
				}
			}
			double valuePercentage = ((double)(hasAttributeValueCount)) / examples.size();
			/*System.out.println("valuepercentage: "+valuePercentage);
			System.out.println("posExampleCount: "+positiveExampleCount);
			System.out.println("pos doubleExampleCount "+(double)positiveExampleCount);
			System.out.println(((double)positiveExampleCount)/hasAttributeValueCount);
			System.out.println("Bvalue: "+B(((double)positiveExampleCount)/hasAttributeValueCount));
			System.out.println("hasAttrPercentage: "+hasAttributeValueCount);*/

			remainder += (valuePercentage * B(classificationCount));				
			//System.out.println("remainder is: "+remainder);
		}
		//System.out.println("remainder is: "+remainder);
		return B-remainder;
	}
	private double secondLog(double val){
		return Math.log(val)/Math.log(2);
	}

	private double B(ArrayList<Integer> classificationValueCount){
		int n=0;
		for(Integer count:classificationValueCount){
			n+=count;
		}
		double sum=0.0;
		for(Integer count:classificationValueCount){
			if(count>0){
				double probability=(count+0.0)/n;
				sum+=probability*secondLog(probability);
			}
		}
		return -sum;

	}
	private Node makeClassificationNode(Value classificationValue){
		return new Node(classificationValue.getAttributeValue());
	}
}
