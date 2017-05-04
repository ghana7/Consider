import java.util.HashMap;
import java.util.Map;

public class GameData {

	
	public final static Map<String, Aspect> ASPECTDICT = new HashMap<String,Aspect>();
	public final static Map<String, Item> ITEMDICT = new HashMap<String,Item>();
	static {
		ASPECTDICT.put("hardness", new Aspect("hardness", "Hardness"));
		ASPECTDICT.put("heaviness", new Aspect("heaviness", "Heaviness"));
		ASPECTDICT.put("smashing", new Aspect("smashing", "Smashing")); //hard + heavy
		ASPECTDICT.put("crushing", new Aspect("crushing", "Crushing")); //smash + heavy
		ASPECTDICT.get("smashing").setRecipe(new Aspect[] {ASPECTDICT.get("hardness"),ASPECTDICT.get("heaviness")});
		ASPECTDICT.get("crushing").setRecipe(new Aspect[] {ASPECTDICT.get("smashing"),ASPECTDICT.get("heaviness")});
	
		ITEMDICT.put("rock", new Item("rock","Rock", new Aspect[] {a("heaviness"),a("crushing")}));
		
	}
	
	private static void makeAspect(String name, String display, String[] ingredients) {
		
	}
	private static Aspect a(String s) {
		return ASPECTDICT.get(s);
	}
}
