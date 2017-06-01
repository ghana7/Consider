import java.util.HashMap;
import java.util.Map;

public class Item {
	private String name; //all lower, for internal use
	private String displayName; //with formatting, for display
	private String pluralName;
	private Aspect[] aspects; //the aspects inherent in this object
	private HashMap<String, Integer> propertyValues; //the values for the properties of the object
	private boolean moveable; //whether the object can be picked up or not
	private HashMap<String, Interaction> interactions; //different interactions that can be done to the item
	
	public Item(String _name, String _displayName, String _pluralName, Aspect[] _aspects, HashMap<String, Integer> _propertyValues, boolean _moveable, HashMap<String, Interaction> _interactions) {
		name = _name;
		displayName = _displayName;
		pluralName = _pluralName;
		aspects = _aspects;
		propertyValues = _propertyValues;
		moveable = _moveable;
		interactions = _interactions;
	}
	public Item(String _name) { //only use if already exists in dict
		name = _name;
		Item base = GameData.ITEMDICT.get(name);
		displayName = base.getDisplayName();
		pluralName = base.getPluralName();
		aspects = base.getAspects();
		propertyValues = base.getPropertyValues();
		moveable = base.isMoveable();
		interactions = base.interactions;
	}
	
	public void addProperty(String name, int value) {
		propertyValues.put(name, value);
	}
	public String getName() {return name;}
	public String getDisplayName() {return displayName;}
	public String getPluralName() {return pluralName;}
	public Aspect[] getAspects() {return aspects;}
	public Integer getPropertyValue(String key) {return propertyValues.get(key);}
	public HashMap<String, Integer> getPropertyValues() {return propertyValues;}
	public boolean isMoveable() {return moveable;}
	public Interaction getInteraction(String command) {return interactions.get(command);}
	public HashMap<String, Interaction> getInteractions() {return interactions;}
	
	public void setInteractions(HashMap<String,Interaction> map) {
		interactions = map;
	}
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
