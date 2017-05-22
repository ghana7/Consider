import java.util.HashMap;
import java.util.Map;

public class Item {
	private String name; //all lower, for internal use
	private String displayName; //with formatting, for display
	private Aspect[] aspects; //the aspects inherent in this object
	private HashMap<String, Integer> propertyValues; //the values for the properties of the object
	private boolean moveable; //whether the object can be picked up or not
	
	public Item(String _name, String _displayName, Aspect[] _aspects, HashMap<String, Integer> _propertyValues, boolean _moveable) {
		name = _name;
		displayName = _displayName;
		aspects = _aspects;
		propertyValues = _propertyValues;
		moveable = _moveable;
	}
	public Item(String _name) { //only use if already exists in dict
		name = _name;
		Item base = GameData.ITEMDICT.get(name);
		displayName = base.getDisplayName();
		aspects = base.getAspects();
		propertyValues = base.getPropertyValues();
		moveable = base.isMoveable();
	}
	
	public void addProperty(String name, int value) {
		propertyValues.put(name, value);
	}
	public String getName() {return name;}
	public String getDisplayName() {return displayName;}
	public Aspect[] getAspects() {return aspects;}
	public Integer getPropertyValue(String key) {return propertyValues.get(key);}
	public HashMap<String, Integer> getPropertyValues() {return propertyValues;}
	public boolean isMoveable() {return moveable;}
	
	public String toString() {
		String output = getName() + ":\nAspects:\n";
		for(Aspect a : getAspects()) {
			output += "\t" + a.getName() + "\n";
		}
		output += "Properties:\n";
		for(String p : propertyValues.keySet()) {
			output += "\t" + p + ": " + propertyValues.get(p) + "\n";
		}
		return output;
	}
}
