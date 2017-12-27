package decisiontree;
public class DiscreteAttribute extends Attribute {
	public static final int zero_to_three = 0;
	public static final int three_to_seven = 1;
	public static final int seven_to_ten = 2;
	public static final String YES = "YES";
	public static final String NO = "NO";

	enum Democrat {
		No,
		Yes
	}
	
	enum population {
		High,
		Medium,
		Low
	}
	
	enum population_change {
		High,
		Medium,
		Low
	}
	
	enum age65plus {
		High,
		Medium,
		Low
	}
	
	enum Black {
		High,
		Medium,
		Low
	}

	enum Hispanic {
		High,
		Medium,
		Low
	}

	enum Edu_bachelors {
		High,
		Medium,
		Low
	}

	enum income {
		High,
		Medium,
		Low
	}

	enum Poverty {
		High,
		Medium,
		Low
	}

	enum Density {
		High,
		Medium,
		Low
	}

	public DiscreteAttribute(String name, double value) {
		super(name, value);
	}

	public DiscreteAttribute(String name, String value) {
		super(name, value);
	}
}
