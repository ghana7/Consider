import java.util.HashMap;
import java.util.Map;

public class GameData {

	
	public final static Map<String, Aspect> ASPECTDICT = new HashMap<String,Aspect>();
	public final static Map<String, Item> ITEMDICT = new HashMap<String,Item>();
	public final static Map<String, Idea> IDEADICT = new HashMap<String, Idea>();
	public static HashMap<String,String> OPPOSITES = new HashMap<String,String>();
	static {
		OPPOSITES.put("north", "south");
		OPPOSITES.put("south", "north");
		OPPOSITES.put("west", "east");
		OPPOSITES.put("east", "west");
		OPPOSITES.put("up", "down");
		OPPOSITES.put("down", "up");
		OPPOSITES.put("in", "out");
		OPPOSITES.put("out", "in");
		
		makeAspect("hard",null);
		makeAspect("heavy",null);
		makeAspect("smashing",new String[] {"hard","heavy"});
		makeAspect("crushing",new String[] {"smashing","heavy"});
		makeAspect("long",null);
		makeAspect("brittle",null);
		makeAspect("wet",null);
		makeAspect("thick",new String[] {"wet","heavy"});
		makeAspect("crystalline",new String[] {"hard","brittle"});
		makeAspect("bright",null);
		makeAspect("shiny",new String[] {"crystalline","bright"});
		makeAspect("metallic",new String[] {"shiny","hard"});
		makeAspect("alive",null);
		makeAspect("sharp",null);
		makeAspect("hot",null);
		
		
		makeItem("rock","Rock",new String[] {"heavy","hard"}, new String[] {"weight:7"});
		makeItem("flint","Shard of flint", new String[] {"hard","sharp"}, new String[] {"weight:1","sharpness:5"});
		makeItem("club","Club",new String[] {"smashing"}, new String[] {"weight:23"});
		makeItem("tree","Tree",new String[] {"long","alive"}, new String[] {"height:30","flammable:1"});
		makeItem("stick","Stick",new String[] {"long","brittle"}, new String[] {"length:15"});
		makeItem("hammer","Hammer",new String[] {"long","heavy","smashing","crushing"}, new String[] {"hardness:50"});
		makeItem("campfire","Campfire",new String[] {"hot"}, new String[] {});
		
		makeIdea("club", new String[] {"crushing","heavy"}, new String[] {"weight:5:10"});
		makeIdea("hammer", new String[] {"smashing","long"}, new String[] {"weight:5:15","length:10:20"});
		makeIdea("campfire", new String[] {"hard","hot"}, new String[] {"sharpness:5:15","hardness:10:20","flammable:1:1"});
	}
	
	private static void makeAspect(String name, String[] ingredients) { //shortcut for making a new aspect
		ASPECTDICT.put(name, new Aspect(name));
		if(ingredients != null) {
			Aspect[] aspectRecipe = new Aspect[ingredients.length];
			for(int i = 0; i < ingredients.length; i++) {
				aspectRecipe[i] = a(ingredients[i]);
			}
			ASPECTDICT.get(name).setRecipe(aspectRecipe);
		}
	}
	
	private static void makeItem(String name, String display, String[] aspects, String[] properties) { //shortcut for making a new item
		Aspect[] aspectArray = new Aspect[aspects.length];
		for(int i = 0; i < aspects.length; i++) {
			aspectArray[i] = a(aspects[i]);
		}
		HashMap<String, Integer> propertyMap = new HashMap<String,Integer>();
		for(String s : properties) {
			String[] split = s.split(":");
			propertyMap.put(split[0], Integer.parseInt(split[1]));
		}
		ITEMDICT.put(name, new Item(name,display,aspectArray,propertyMap));
	}
	
	private static void makeIdea(String itemName, String[] aspectRecipe, String[] propertyRecipe) {
		Aspect[] aspectArray = new Aspect[aspectRecipe.length];
		for(int i = 0; i < aspectRecipe.length; i++) {
			aspectArray[i] = a(aspectRecipe[i]);
		}
		
		Property[] propertyArray = new Property[propertyRecipe.length]; //items are formatted "property:min:max"
		for(int i = 0; i < propertyRecipe.length; i++) {
			String[] splitProperty = propertyRecipe[i].split(":");
			propertyArray[i] = new Property(splitProperty[0], Integer.parseInt(splitProperty[1]), Integer.parseInt(splitProperty[2]));
		}
		
		IDEADICT.put(itemName, new Idea(aspectArray, propertyArray, i(itemName)));
	}
	
	private static Aspect a(String s) { //just a shortcut for getting aspects from the dictionary
		return ASPECTDICT.get(s);
	}
	
	private static Item i(String s) { //^ but for items
		return ITEMDICT.get(s);
	}
}
