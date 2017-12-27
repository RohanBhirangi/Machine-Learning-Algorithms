package decisiontree;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileReader {
	public static final String PATH_TO_DATA_FILE = "votes-training.data";

	public static ArrayList<Record> buildRecords() {
		BufferedReader reader = null;
		DataInputStream dis = null;
		ArrayList<Record> records = new ArrayList<Record>();

		try {
			File f = new File(PATH_TO_DATA_FILE);
			FileInputStream fis = new FileInputStream(f);
			reader = new BufferedReader(new InputStreamReader(fis));
			;

			// read the first record of the file
			String line;
			Record r = null;
			ArrayList<DiscreteAttribute> attributes;
			while ((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				attributes = new ArrayList<DiscreteAttribute>();
				r = new Record();

				if (ID3.NUM_ATTRS != st.countTokens()) {
					throw new Exception("Unknown number of attributes!");
				}

				@SuppressWarnings("unused")
				// String day = st.nextToken();
				String population = st.nextToken();
				String population_change = st.nextToken();
				String age65plus = st.nextToken();
				String Black = st.nextToken();
				String Hispanic = st.nextToken();
				String Edu_bachelors = st.nextToken();
				String income = st.nextToken();
				String Poverty = st.nextToken();
				String Density = st.nextToken();
				String Democrat = st.nextToken();

				if (Integer.parseInt(population)>=0&&Integer.parseInt(population)<=3) {
					attributes.add(new DiscreteAttribute("population", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(population)>3&&Integer.parseInt(population)<=7) {
					attributes.add(new DiscreteAttribute("population", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(population)>7&&Integer.parseInt(population)<=10) {
					attributes.add(new DiscreteAttribute("population", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(population_change)>=0&&Integer.parseInt(population_change)<=3) {
					attributes.add(new DiscreteAttribute("population_change", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(population_change)>3&&Integer.parseInt(population_change)<=7) {
					attributes.add(new DiscreteAttribute("population_change", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(population_change)>7&&Integer.parseInt(population_change)<=10) {
					attributes.add(new DiscreteAttribute("population_change", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(age65plus)>=0&&Integer.parseInt(age65plus)<=3) {
					attributes.add(new DiscreteAttribute("age65plus", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(age65plus)>3&&Integer.parseInt(age65plus)<=7) {
					attributes.add(new DiscreteAttribute("age65plus", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(age65plus)>7&&Integer.parseInt(age65plus)<=10) {
					attributes.add(new DiscreteAttribute("age65plus", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(Black)>=0&&Integer.parseInt(Black)<=3) {
					attributes.add(new DiscreteAttribute("Black", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(Black)>3&&Integer.parseInt(Black)<=7) {
					attributes.add(new DiscreteAttribute("Black", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(Black)>7&&Integer.parseInt(Black)<=10) {
					attributes.add(new DiscreteAttribute("Black", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(Hispanic)>=0&&Integer.parseInt(Hispanic)<=3) {
					attributes.add(new DiscreteAttribute("Hispanic", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(Hispanic)>3&&Integer.parseInt(Hispanic)<=7) {
					attributes.add(new DiscreteAttribute("Hispanic", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(Hispanic)>7&&Integer.parseInt(Hispanic)<=10) {
					attributes.add(new DiscreteAttribute("Hispanic", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(Edu_bachelors)>=0&&Integer.parseInt(Edu_bachelors)<=3) {
					attributes.add(new DiscreteAttribute("Edu_bachelors", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(Edu_bachelors)>3&&Integer.parseInt(Edu_bachelors)<=7) {
					attributes.add(new DiscreteAttribute("Edu_bachelors", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(Edu_bachelors)>7&&Integer.parseInt(Edu_bachelors)<=10) {
					attributes.add(new DiscreteAttribute("Edu_bachelors", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(income)>=0&&Integer.parseInt(income)<=3) {
					attributes.add(new DiscreteAttribute("income", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(income)>3&&Integer.parseInt(income)<=7) {
					attributes.add(new DiscreteAttribute("income", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(income)>7&&Integer.parseInt(income)<=10) {
					attributes.add(new DiscreteAttribute("income", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(Poverty)>=0&&Integer.parseInt(Poverty)<=3) {
					attributes.add(new DiscreteAttribute("Poverty", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(Poverty)>3&&Integer.parseInt(Poverty)<=7) {
					attributes.add(new DiscreteAttribute("Poverty", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(Poverty)>7&&Integer.parseInt(Poverty)<=10) {
					attributes.add(new DiscreteAttribute("Poverty", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(Density)>=0&&Integer.parseInt(Density)<=3) {
					attributes.add(new DiscreteAttribute("Density", DiscreteAttribute.zero_to_three));
				} else if (Integer.parseInt(Density)>3&&Integer.parseInt(Density)<=7) {
					attributes.add(new DiscreteAttribute("Density", DiscreteAttribute.three_to_seven));
				} else if (Integer.parseInt(Density)>7&&Integer.parseInt(Density)<=10) {
					attributes.add(new DiscreteAttribute("Density", DiscreteAttribute.seven_to_ten));
				}

				if (Integer.parseInt(Democrat)==0) {
					attributes.add(new DiscreteAttribute("Votes", DiscreteAttribute.NO));
				} else if (Integer.parseInt(Democrat)==1) {
					attributes.add(new DiscreteAttribute("PlayTennis", DiscreteAttribute.YES));
				}

				r.setAttributes(attributes);
				records.add(r);
			}

		} catch (IOException e) {
			System.out.println("Uh oh, got an IOException error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Uh oh, got an Exception error: " + e.getMessage());
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException ioe) {
					System.out.println("IOException error trying to close the file: " + ioe.getMessage());
				}
			}
		}
		return records;
	}
}
