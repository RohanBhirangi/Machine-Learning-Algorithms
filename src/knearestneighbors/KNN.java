package knearestneighbors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class KNN {
	private static final String SEPARATOR = ",";

	public static ArrayList<String[]> readData(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		ArrayList<String[]> data = new ArrayList<String[]>();
		br.readLine();
		while ((line = br.readLine()) != null) {
			data.add(line.split(SEPARATOR));
		}
		br.close();
		// for (int i = 0; i < data.size(); i++)
		// for (int j = 0; j < data.get(i).length; j++)
		// System.out.println(data.get(i)[j]);
		return data;
	}

	public static ArrayList<String[]> getDistanceFromPoints(String[] input_array, ArrayList<String[]> data) {
		ArrayList<String[]> distance = new ArrayList<String[]>();
		for (int i = 0; i < data.size(); i++) {
			Double sum = 0.0;
			Double difference = 0.0;
			Double temp_Distance = 0.0;
			for (int j = 1; j < input_array.length - 1; j++) {
				difference = Double.parseDouble(data.get(i)[j]) - Double.parseDouble(input_array[j]);
				sum = sum + Math.pow(difference, 2);
			}
			temp_Distance = Math.sqrt(sum);
			String[] temp_string = { Double.toString(temp_Distance), data.get(i)[0] };
			distance.add(temp_string);
		}
		// for (int i = 0; i < distance.size(); i++)
		// for (int j = 0; j < distance.get(i).length; j++)
		// System.out.println(distance.get(i)[j]);
		return distance;
	}

	public static ArrayList<String[]> getMinimumDistancePoints(ArrayList<String[]> distance) {
		Double min_Distance = Double.MAX_VALUE;
		ArrayList<String[]> minimum_Distance_List = new ArrayList<String[]>();
		for (int i = 0; i < distance.size(); i++) {
			if (Double.parseDouble(distance.get(i)[0]) < min_Distance) {
				min_Distance = Double.parseDouble(distance.get(i)[0]);
			}
		}
		for (int i = 0; i < distance.size(); i++) {
			if (Double.parseDouble(distance.get(i)[0]) == min_Distance) {
				String[] temp_string = { distance.get(i)[0], distance.get(i)[1] };
				minimum_Distance_List.add(temp_string);
			}
		}
		// for (int i = 0; i < minimum_Distance_List.size(); i++)
		// for (int j = 0; j < 2; j++)
		// System.out.println(minimum_Distance_List.get(i)[j]);
		return minimum_Distance_List;
	}

	public static String getClassification(ArrayList<String[]> minimum_Distance_List) {
		HashMap<String, Integer> Label_Set = new HashMap<String, Integer>();
		for (int i = 0; i < minimum_Distance_List.size(); i++) {
			if (!Label_Set.containsKey(minimum_Distance_List.get(i)[1])) {
				Label_Set.put(minimum_Distance_List.get(i)[1], 1);
			} else {
				int value = Label_Set.get(minimum_Distance_List.get(i)[1]);
				value++;
				Label_Set.replace(minimum_Distance_List.get(i)[1], value);
			}
		}
		Collection<Integer> values = new ArrayList<Integer>();
		values = Label_Set.values();
		Collection<String> keys = new ArrayList<String>();
		keys = Label_Set.keySet();
		ArrayList<String> final_result = new ArrayList<String>();
		for (String key : keys) {
			if (Label_Set.get(key) == Collections.max(values)) {
				final_result.add(key);
			}
		}
		return final_result.get(new Random().nextInt(final_result.size()));
	}

	public static void main(String args[]) throws IOException {
		String training_data = "votes-train.csv";
		String test_data = "votes-test.csv";
		ArrayList<String[]> data = readData(training_data);
		ArrayList<String[]> input = readData(test_data);

		for (int i = 0; i < input.size(); i++) {
			ArrayList<String[]> distance = getDistanceFromPoints(input.get(i), data);
			ArrayList<String[]> minimum_Distance_List = getMinimumDistancePoints(distance);
			if (getClassification(minimum_Distance_List).equals("0"))
				System.out.println("The county voted Republican");
			else if (getClassification(minimum_Distance_List).equals("1"))
				System.out.println("The county voted Democrat");
			// System.out.println(getClassification(minimum_Distance_List));
		}
	}
}
