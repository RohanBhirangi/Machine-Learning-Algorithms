package perceptron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Perceptron {
	private static final String SEPARATOR = ",";
	private static Double LEARNING_RATE = 0.005;
	private static final int THRESHOLD = 0;
	private static final int MAX_ITERATIONS = 777;
	private static ArrayList<Integer> outputs = new ArrayList<Integer>();
	private static ArrayList<Double> f1 = new ArrayList<Double>();
	private static ArrayList<Double> f2 = new ArrayList<Double>();
	private static ArrayList<Double> f3 = new ArrayList<Double>();
	private static ArrayList<Double> f4 = new ArrayList<Double>();
	private static ArrayList<Double> f5 = new ArrayList<Double>();
	private static ArrayList<Double> f6 = new ArrayList<Double>();
	private static ArrayList<Double> f7 = new ArrayList<Double>();
	private static ArrayList<Double> f8 = new ArrayList<Double>();
	private static ArrayList<Double> f9 = new ArrayList<Double>();
	private static Double[] weights = new Double[10];

	public static void readTrainingData(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		br.readLine();
		while ((line = br.readLine()) != null) {
			outputs.add(Integer.parseInt(line.split(SEPARATOR)[0]));
			f1.add(Double.parseDouble(line.split(SEPARATOR)[1]));
			f2.add(Double.parseDouble(line.split(SEPARATOR)[2]));
			f3.add(Double.parseDouble(line.split(SEPARATOR)[3]));
			f4.add(Double.parseDouble(line.split(SEPARATOR)[4]));
			f5.add(Double.parseDouble(line.split(SEPARATOR)[5]));
			f6.add(Double.parseDouble(line.split(SEPARATOR)[6]));
			f7.add(Double.parseDouble(line.split(SEPARATOR)[7]));
			f8.add(Double.parseDouble(line.split(SEPARATOR)[8]));
			f9.add(Double.parseDouble(line.split(SEPARATOR)[9]));
		}
		br.close();
		// for (int i = 0; i < outputs.size(); i++)
		// System.out.println(outputs.get(i));
	}

	public static ArrayList<String[]> readTestData(String filename) throws IOException {
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

	public static double randomNumber(int min, int max) {
		DecimalFormat df = new DecimalFormat("#.####");
		double d = min + Math.random() * (max - min);
		String s = df.format(d);
		double x = Double.parseDouble(s);
		return x;
	}

	public static int calculateOutput(int theta, Double[] weights, Double f1, Double f2, Double f3, Double f4,
			Double f5, Double f6, Double f7, Double f8, Double f9) {
		double sum = f1 * weights[0] + f2 * weights[1] + f3 * weights[2] + f4 * weights[3] + f5 * weights[4]
				+ f6 * weights[5] + f7 * weights[6] + f8 * weights[7] + f9 * weights[8] + weights[9];
		return (sum >= theta) ? 1 : 0;
	}

	public static void adjustWeights() {
		double local_error, global_error;
		weights[0] = randomNumber(0, 1);
		weights[1] = randomNumber(0, 1);
		weights[2] = randomNumber(0, 1);
		weights[3] = randomNumber(0, 1);
		weights[4] = randomNumber(0, 1);
		weights[5] = randomNumber(0, 1);
		weights[6] = randomNumber(0, 1);
		weights[7] = randomNumber(0, 1);
		weights[8] = randomNumber(0, 1);
		weights[9] = randomNumber(0, 1);

		int iteration = 0;
		do {
			iteration++;
			global_error = 0;
			int output;
			for (int i = 0; i < f1.size(); i++) {
				output = calculateOutput(THRESHOLD, weights, f1.get(i), f2.get(i), f3.get(i), f4.get(i), f5.get(i),
						f6.get(i), f7.get(i), f8.get(i), f9.get(i));
				local_error = outputs.get(i) - output;
				weights[0] += LEARNING_RATE * local_error * f1.get(i);
				weights[1] += LEARNING_RATE * local_error * f2.get(i);
				weights[2] += LEARNING_RATE * local_error * f3.get(i);
				weights[3] += LEARNING_RATE * local_error * f4.get(i);
				weights[4] += LEARNING_RATE * local_error * f5.get(i);
				weights[5] += LEARNING_RATE * local_error * f6.get(i);
				weights[6] += LEARNING_RATE * local_error * f7.get(i);
				weights[7] += LEARNING_RATE * local_error * f8.get(i);
				weights[8] += LEARNING_RATE * local_error * f9.get(i);
				weights[9] += LEARNING_RATE * local_error;
				global_error += (local_error * local_error);
			}
			LEARNING_RATE *= 0.05;
		} while (global_error != 0 && iteration <= MAX_ITERATIONS);
		// for (int i = 0; i < weights.length; i++)
		// System.out.println(weights[i]);
	}

	public static int getClassification(String[] input_array) {
		int output = calculateOutput(THRESHOLD, weights, Double.parseDouble(input_array[0]),
				Double.parseDouble(input_array[1]), Double.parseDouble(input_array[2]),
				Double.parseDouble(input_array[3]), Double.parseDouble(input_array[4]),
				Double.parseDouble(input_array[5]), Double.parseDouble(input_array[6]),
				Double.parseDouble(input_array[7]), Double.parseDouble(input_array[8]));
		return output;
	}

	public static void main(String args[]) throws IOException {
		String training_data = "votes-train.csv";
		String test_data = "votes-test.csv";
		readTrainingData(training_data);
		adjustWeights();
		ArrayList<String[]> input = readTestData(test_data);
		for (int i = 0; i < input.size(); i++) {
			if (getClassification(input.get(i)) == 0)
				System.out.println("The county voted Republican");
			else if (getClassification(input.get(i)) == 1)
				System.out.println("The county voted Democrat");
			// System.out.println(getClassification(input.get(i)));
		}
	}
}
