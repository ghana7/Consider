import java.util.HashMap;
import java.util.Map;

public class Item {
	private String name; //all lower, for internal use
	private String displayName; //with formatting, for display
	private Aspect[] aspects; //the aspects inherent in this object
	public final static Map<String, Item> ITEMDICT = new HashMap<String,Item>();
	
	static {
	}
	
	public Item(String _name, String _displayName, Aspect[] _aspects) {
		name = _name;
		displayName = _displayName;
		aspects = _aspects;
	}
	public Item(String _name) {
		name = _name;
		Item base = ITEMDICT.get(name);
		displayName = base.getDisplayName();
		aspects = base.getAspects();
	}
	
	public String getName() {return name;}
	public String getDisplayName() {return displayName;}
	public Aspect[] getAspects() {return aspects;}
}
