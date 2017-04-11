
public class Item {
	private String name;
	private Aspect[] aspects; //the aspects inherent in this object
	
	public Item(String _name, Aspect[] _aspects) {
		name = _name;
		aspects = _aspects;
	}
	
	public String getName() {return name;}
	public Aspect[] getAspects() {return aspects;}
}
