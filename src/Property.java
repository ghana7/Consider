
public class Property
{
	private String name;
	private int minimum;
	private int maximum;
	
	public Property(String _name, int _minimum, int _maximum) {
		name = _name;
		minimum = _minimum;
		maximum = _maximum;
	}
	public String getName() {
		return name;
	}
	
	public boolean isValid(Integer number) { //if number fits the property
		if(number != null && number >= minimum && number <= maximum) {
			return true;
		}
		return false;
	}
	public String toString() {
		String output = "";
		output += name.substring(0,1).toUpperCase();
		output += name.substring(1);
		output += " of minimum " + minimum + " and maximum " + maximum;
		return output;
	}
}
