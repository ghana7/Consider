
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
	
	public boolean isValid(int number) {
		if(number >= minimum && number <= maximum) {
			return true;
		}
		return false;
	}
}
