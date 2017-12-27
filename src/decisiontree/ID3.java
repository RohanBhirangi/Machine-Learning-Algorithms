package decisiontree;
import java.util.*;

public class ID3 {
	public static int NUM_ATTRS = 10;
	public static ArrayList<String> attrMap;
	public static ArrayList<Integer> usedAttributes = new ArrayList<Integer>();

	public static void main(String[] args) {
		populateAttrMap();

		Tree t = new Tree();
		ArrayList<Record> records;
		LearningSet learningSet = new LearningSet();
		
		// read in all our data
		records = FileReader.buildRecords();
		
		Node root = new Node();
		
		for(Record record : records) {
			root.getData().add(record);
		}
		
		t.buildTree(records, root, learningSet);
		traverseTree(records.get(9), root);
		return;
	}
	
	public static void traverseTree(Record r, Node root) {
		while(root.children != null) {
			double nodeValue = 0;
			for(int i = 0; i < r.getAttributes().size(); i++) {
				if(r.getAttributes().get(i).getName().equalsIgnoreCase(root.getTestAttribute().getName())) {
					nodeValue = r.getAttributes().get(i).getValue();
					break;
				}
			}
			for(int i = 0; i < root.getChildren().length; i++) {
				if(nodeValue == root.children[i].getTestAttribute().getValue()) {
					traverseTree(r, root.children[i]);
				}
			}
		}
		
		System.out.print("The country voted for Democrat: ");
		if(root.getTestAttribute().getValue() == 0) {
			System.out.println("No");
		}
		else if(root.getTestAttribute().getValue() == 1) {
			System.out.println("Yes");
		}

		return;
	}
	
	public static boolean isAttributeUsed(int attribute) {
		if(usedAttributes.contains(attribute)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static int setSize(String set) {
		if(set.equalsIgnoreCase("population")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("population_change")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("age65plus")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("Black")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("Hispanic")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("Edu_bachelors")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("income")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("Poverty")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("Density")) {
			return 3;
		}
		else if(set.equalsIgnoreCase("Democrat")) {
			return 2;
		}
		return 0;
	}
	
	public static String getLeafNames(int attributeNum, int valueNum) {
		if(attributeNum == 0) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		if(attributeNum == 1) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		if(attributeNum == 2) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		if(attributeNum == 3) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		if(attributeNum == 4) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		if(attributeNum == 5) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		if(attributeNum == 6) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		if(attributeNum == 7) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		if(attributeNum == 8) {
			if(valueNum == 0) {
				return "zero_to_three";
			}
			else if(valueNum == 1) {
				return "three_to_seven";
			}
			else if(valueNum == 2) {
				return "seven_to_ten";
			}
		}
		
		return null;
	}
	
	public static void populateAttrMap() {
		attrMap = new ArrayList<String>();
		attrMap.add("population");
		attrMap.add("population_change");
		attrMap.add("age65plus");
		attrMap.add("Black");
		attrMap.add("Hispanic");
		attrMap.add("Edu_bachelors");
		attrMap.add("income");
		attrMap.add("Poverty");
		attrMap.add("Density");
		attrMap.add("Democrat");
	}
}
