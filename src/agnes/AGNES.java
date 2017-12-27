package agnes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AGNES {
	private static final String SEPARATOR = ",";
	private static ArrayList<ArrayList<Double[]>> clusters;
	private static ArrayList<String[]> string_data;
	private static ArrayList<Double[]> data;

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
		return data;
	}

	public static ArrayList<Double[]> convertData(ArrayList<String[]> data) {
		ArrayList<Double[]> converted_data = new ArrayList<Double[]>();
		for (int i = 0; i < data.size(); i++) {
			Double[] temp_array = new Double[data.get(i).length - 1];
			for (int j = 0; j < data.get(i).length - 1; j++) {
				temp_array[j] = Double.parseDouble(data.get(i)[j + 1]);
			}
			converted_data.add(temp_array);
		}
		return converted_data;
	}

	public static ArrayList<Double> getDistanceFromPoints(Double[] input_array, ArrayList<Double[]> data) {
		ArrayList<Double> distance = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			Double sum = 0.0;
			Double difference = 0.0;
			Double temp_distance = 0.0;
			for (int j = 1; j < input_array.length - 1; j++) {
				difference = data.get(i)[j] - input_array[j];
				sum = sum + Math.pow(difference, 2);
			}
			temp_distance = Math.sqrt(sum);
			distance.add(temp_distance);
		}
		return distance;
	}

	public static void main(String args[]) throws IOException {
		String test_data = "votes-test.csv";
		string_data = readData(test_data);
		data = convertData(string_data);
		for (int i = 0; i < data.size(); i++) {
			ArrayList<Double[]> temp = new ArrayList<>();
			temp.add(data.get(i));
			clusters.set(i, temp);
			data.remove(i);
		}
		while (clusters.size() != 1) {

			for (int i = 0; i < clusters.size(); i++)
				data.set(i, clusters.get(i).get(new Random().nextInt(clusters.get(i).size())));

			for (int i = 0; i < data.size(); i++) {
				Double[] temp = data.get(i);
				data.remove(i);
				ArrayList<Double> distance=getDistanceFromPoints(temp, data);
				Collections.sort(distance);
				
			}

		}
	}
}
