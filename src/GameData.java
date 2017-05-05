import java.util.HashMap;
import java.util.Map;

public class GameData {

	
	public final static Map<String, Aspect> ASPECTDICT = new HashMap<String,Aspect>();
	public final static Map<String, Item> ITEMDICT = new HashMap<String,Item>();
	static {
		makeAspect("hardness","Hardness",null);
		makeAspect("heaviness","Heaviness",null);
		makeAspect("smashing","Smashing",new String[] {"hardness","heaviness"});
		makeAspect("crushing","Crushing",new String[] {"smashing","heaviness"});
		
		makeItem("rock","Rock",new String[] {"heaviness","hardness"});
		
	}
	
	private static void makeAspect(String name, String display, String[] ingredients) { //shortcut for making a new aspect
		ASPECTDICT.put(name, new Aspect(name,display));
		if(ingredients != null) {
			Aspect[] aspectRecipe = new Aspect[ingredients.length];
			for(int i = 0; i < ingredients.length; i++) {
				aspectRecipe[i] = a(ingredients[i]);
			}
			ASPECTDICT.get(name).setRecipe(aspectRecipe);
		}
	}
	
	private static void makeItem(String name, String display, String[] aspects) { //shortcut for making a new item
		Aspect[] aspectArray = new Aspect[aspects.length];
		for(int i = 0; i < aspects.length; i++) {
			aspectArray[i] = a(aspects[i]);
		}
		ITEMDICT.put(name, new Item(name,display,aspectArray));
	}
	private static Aspect a(String s) { //just a shortcut for getting aspects from the dictionary
		return ASPECTDICT.get(s);
	}
}
