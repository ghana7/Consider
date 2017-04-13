import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Aspect implements Comparable<Aspect>{
	private String name; //the name of the aspect
	private String displayName; //the name shown of the object
	private Aspect[] recipe; //the aspects needed to combine to discover this one
	
	public final static Map<String, Aspect> ASPECTDICT = new HashMap<String,Aspect>();
	static {
		ASPECTDICT.put("hardness", new Aspect("hardness", "Hardness"));
		ASPECTDICT.put("heaviness", new Aspect("heaviness", "Heaviness"));
		ASPECTDICT.put("smashing", new Aspect("smashing", "Smashing")); //hard + heavy
		ASPECTDICT.put("crushing", new Aspect("crushing", "Crushing")); //smash + heavy
		ASPECTDICT.get("smashing").setRecipe(new Aspect[] {ASPECTDICT.get("hardness"),ASPECTDICT.get("heaviness")});
		ASPECTDICT.get("crushing").setRecipe(new Aspect[] {ASPECTDICT.get("smashing"),ASPECTDICT.get("heaviness")});
	}
	
	public Aspect(String _name, String _displayName) {
		name = _name;
		displayName = _displayName;
	}
	
	public void setRecipe(Aspect[] _recipe) {recipe = _recipe;}
	public String getName() {return name;}
	public Aspect[] getRecipe() {return recipe;}
	
	/* returns an alphabetized, comma separated list of these aspects
	 * used in crafting
	 * Aspect array --> crafting code
	 * crafting code is checked against all aspect/ideas' crafting codes
	 * if there is a match, that thing is made
	 */
	public static String getCraftingCode(Aspect[] aspects) {
		Aspect[] sorted = aspects;
		Arrays.sort(sorted);
		String output = "";
		for(int i = 0; i < sorted.length - 1; i++) {
			output += sorted[i];
			output += ",";
		}
		output += sorted[sorted.length - 1];
		return output;
	}

	public int compareTo(Aspect a) {
		return name.compareTo(a.getName());
	}
}
