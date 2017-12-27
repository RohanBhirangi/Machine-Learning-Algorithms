package kmeans;

import static java.lang.Math.abs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class KMean {

	private static final String SEPARATOR = ",";
	int k;
	ArrayList<Double[]> data;
	ArrayList<Double[]> dataItems;
	ArrayList<Double[]> cz;
	ArrayList<Double[]> oldCz;
	ArrayList<Double> row;
	ArrayList<ArrayList<Double[]>> groups;

	public KMean(int k, ArrayList<Double[]> data_copy) {
		this.k = k;
		this.data = data_copy;
		// for (int i = 0; i < data.size(); i++)
		// for (int j = 0; j < data.get(i).length; j++)
		// System.out.println(data.get(i)[j]);
		dataItems = new ArrayList<>();
		cz = new ArrayList<>();
		oldCz = new ArrayList<>();
		row = new ArrayList<>();
		groups = new ArrayList<>();
		int noOfItems = data.size();

		for (int i = 0; i < k; i++) {
			groups.add(new ArrayList<>());
		}

		for (int i = 0; i < noOfItems; i++) {
			dataItems.add(data.get(i));
			if (i < k) {
				cz.add(dataItems.get(i));
				// System.out.println("C" + (i + 1) + " is " + cz.get(i));
			}
		}
		int iter = 1;
		do {
			for (int i = 0; i < dataItems.size(); i++) {
				for (int j = 0; j < cz.size(); j++) {
				Double sum=0.0;
					ArrayList<Double> temp_array = new ArrayList<>();
					for (int p = 0; p < cz.get(j).length; p++) {
						temp_array.add(abs(cz.get(j)[p] - dataItems.get(i)[p]));
						sum=sum+temp_array.get(p);
					}
					// test.printArrayList(temp_array,0,0,0);
					row.add(sum);
//					index = row.indexOf(Collections.min(row));
					//groups.get(j).add(dataItems.get(i));
					}
				// test.printArrayList(row, 0, 0);
				int index = row.indexOf(Collections.min(row));

				//	int index = row.get(i).indexOf(Collections.min(row.get(i)));
				// System.out.println(index);
			groups.get(index).add(dataItems.get(i));
				
				
				
				row.removeAll(row);
			}

			for (int i = 0; i < k; i++) {
				if (iter == 1) {
					oldCz.add(cz.get(i));
				} else {
					oldCz.set(i, cz.get(i));
				}
				if (!groups.get(i).isEmpty()) {
					for (int j = 0; j < cz.size(); j++)
						cz.get(i)[j] = average(groups.get(i));
				}
			}

			if (!cz.equals(oldCz)) {
				for (int i = 0; i < groups.size(); i++) {
					groups.get(i).removeAll(groups.get(i));
				}
			}
			iter++;
		} while (!cz.equals(oldCz));
		for (int i = 0; i < cz.size(); i++) {
			System.out.println("New C" + (i + 1) + " " + cz.get(i));
		}
		for (int i = 0; i < groups.size(); i++) {
			System.out.println("Group " + (i + 1));
			System.out.println(groups.get(i).toString());
		}
		System.out.println("Number of Itrations: " + iter);
	}

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

	public static ArrayList<Double[]> convertData(ArrayList<String[]> data) {
		ArrayList<Double[]> converted_data = new ArrayList<Double[]>();
		for (int i = 0; i < data.size(); i++) {
			Double[] temp_array = new Double[data.get(i).length - 1];
			for (int j = 0; j < data.get(i).length - 1; j++) {
				temp_array[j] = Double.parseDouble(data.get(i)[j + 1]);
			}
			converted_data.add(temp_array);
		}
		// for (int i = 0; i < converted_data.size(); i++)
		// for (int j = 0; j < converted_data.get(i).length; j++)
		// System.out.println(converted_data.get(i)[j]);
		return converted_data;
	}

	public static void main(String[] args) throws IOException {
		int k = 10;
		String training_data = "votes-test.csv";
		ArrayList<String[]> data = readData(training_data);
		ArrayList<Double[]> converted_data = convertData(data);
		new KMean(k, converted_data);
	}

	public static Double average(ArrayList<Double[]> list) {
		Double sum = 0.0;
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).length; j++) {
				sum = sum + list.get(i)[j];
			}
		}
		return sum / list.size();
	}
}