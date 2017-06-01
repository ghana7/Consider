import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameData {

	public final static Map<String, Biome> BIOMEDICT = new HashMap<String,Biome>();
	public final static Map<String, Aspect> ASPECTDICT = new HashMap<String,Aspect>();
	public final static Map<String, Item> ITEMDICT = new HashMap<String,Item>();
	public final static Map<String, Idea> IDEADICT = new HashMap<String,Idea>();
	public static HashMap<String,String> OPPOSITES = new HashMap<String,String>();
	static {
		OPPOSITES.put("north", "south");
		OPPOSITES.put("south", "north");
		OPPOSITES.put("west", "east");
		OPPOSITES.put("east", "west");
		
		makeAspect("hard");
		makeAspect("heavy");
		makeAspect("smashing",		new String[] {"hard","heavy"});
		makeAspect("crushing",		new String[] {"smashing","heavy"});
		makeAspect("long");
		makeAspect("brittle");
		makeAspect("rigid");
		makeAspect("wet");
		makeAspect("thick",			new String[] {"wet","heavy"});
		makeAspect("crystalline",	new String[] {"hard","brittle"});
		makeAspect("bright");
		makeAspect("shiny",			new String[] {"crystalline","bright"});
		makeAspect("metallic",		new String[] {"shiny","hard"});
		makeAspect("alive");
		makeAspect("sharp");
		makeAspect("hot");
		makeAspect("stabbing",		new String[] {"sharp","long"});
		makeAspect("slashing",		new String[] {"sharp","hard"});
		makeAspect("light");
		makeAspect("hollow",		new String[] {"light","heavy"});
		makeAspect("piercing",		new String[] {"stabbing","sharp"});
		makeAspect("cutting",		new String[] {"slashing","piercing"});
		
		
		makeItem("rock","Rock","Rocks",new String[] {"heavy","hard"}, new String[] {"weight:7","hardness:15"}, true);
		makeItem("flint","Shard of flint","Shards of flint", new String[] {"hard","sharp"}, new String[] {"weight:1","sharpness:10"}, true);
		makeItem("club","Club","Clubs",new String[] {"smashing"}, new String[] {"weight:23"}, true);
		makeItem("tree","Tree","Trees",new String[] {"long","alive"}, new String[] {"height:30","flammable:1"}, false);
		makeItem("stick","Stick","Sticks",new String[] {"long","brittle"}, new String[] {"length:15","flammable:1"}, true);
		makeItem("hammer","Hammer","Hammers",new String[] {"long","heavy","smashing","crushing"}, new String[] {"hardness:50"}, true);
		makeItem("campfire","Campfire","Campfires",new String[] {"hot"}, new String[] {"temperature:600"}, false);
		makeItem("lake","Lake","Lakes",new String[] {"wet"}, new String[] {"moisture:1000"}, false);
		makeItem("saw","Saw","Saws",new String[] {"sharp","slashing"}, new String[] {"sharpness:10"}, true);
		makeItem("cactus","Cactus","Cacti",new String[] {"sharp","alive","wet"}, new String[] {"sharpness:3","moisture:50"}, false);
		makeItem("sandstone","Sandstone chunk","Sandstone chunks",new String[] {"hard","brittle","light"}, new String[] {"hardness:5"}, true);
		makeItem("bone","Small bone","Small bones", new String[] {"hard","long","hollow"}, new String[] {"length:20","life:5"}, true);
		makeItem("grass","Wild grass","Wild grasses", new String[] {"long","alive","light"}, new String[] {"moisture:20","flammable:1","life:5"},true);
		makeItem("pickaxe", "Pickaxe","Pickaxes", new String[] {"hard","long","sharp","piercing"}, new String[] {"hardness:50"}, true);
		makeItem("vein","Ore vein","Ore veins", new String[] {"hard","metallic","shiny"}, new String[] {"hardness:35"},false);
		makeItem("pebble","Pebble","Pebbles", new String[] {"hard","light"}, new String[] {"weight:4"},true);
		makeItem("powder","Crushed grass powder","Crushed grass powder", new String[] {"alive","light"}, new String[] {"flammable:1"}, true);
		makeItem("log","Log","Logs",new String[] {"long","alive","heavy"}, new String[] {"flammable:2"}, false);
		makeItem("axe","Axe","Axes",new String[] {"long","sharp","heavy"}, new String[] {"sharpness:20"}, true);
		makeItem("bucket","Bucket","Buckets",new String[] {"hollow"}, new String[] {"capacity:100"}, true);
		makeItem("water","Bucket of Water","Buckets of Water",new String[] {"hollow","wet"}, new String[] {"moisture:100"}, true);
		makeItem("ore","Iron ore","Iron ores",new String[] {"hard","metallic","shiny","heavy"}, new String[] {}, true);
		makeItem("forge","Forge","Forges",new String[] {"hot","hollow"}, new String[] {"temperature:2000"}, false);
		makeItem("plank","Wooden plank","Wooden planks",new String[] {"long","rigid"}, new String[] {"rigidity:50","flammable:2"}, true);
		makeItem("iron","Iron ingot","Iron ingots",new String[] {"hard","metallic","shiny","heavy"}, new String[] {"weight:15","hardness:20","malleability:25"}, true);
		makeItem("treasure","Treasure","Treasures",new String[] {"hollow","heavy","rigid"}, new String[] {}, false);
		makeItem("key","Key","Keys",new String[] {"light","shiny","metallic"}, new String[] {}, true);
		makeItem("diamond","Diamond","Diamonds",new String[] {"shiny","crystalline"}, new String[] {"hardness:100"}, true);
		makeItem("ruby","Ruby","Rubies",new String[] {"shiny","crystalline"}, new String[] {"hardness:50"}, true);
		makeItem("offering","Offering","Offerings",new String[] {"alive","shiny"}, new String[] {"magic:5"}, true);
		makeItem("rune","Rune","Runes",new String[] {"hard","heavy","shiny"}, new String[] {"hardness:50","magic:50"}, true);
		makeItem("scroll","Scroll","Scrolls",new String[] {"light"},new String[] {}, false);
		makeItem("certificate","Certificate of Glorious Participation","Certificates of Glorious Participation",new String[] {},new String[] {}, true);
		
		setInteractions("rock",new String[] {"smash:hammer:pebble:null:3:true:false"});
		setInteractions("tree",new String[] {"cut:axe:log::2:true:false"});
		setInteractions("bucket",new String[] {"fill::water:lake:1:true:false"});
		setInteractions("vein",new String[] {"mine:pickaxe:ore::3:true:false"});
		setInteractions("ore",new String[] {"smelt::iron:forge:1:true:false"});
		setInteractions("log",new String[] {"cut:saw:plank::2:true:false"});
		setInteractions("treasure",new String[] {"open:key:certificate::1:true:true"});
		setInteractions("offering",new String[] {"offer::rune:campfire:1:true:false"});
		
		makeIdea("pickaxe", new String[] {"piercing","long"}, new String[] {"hardness:10:100","length:10:30"},null);
		makeIdea("club", new String[] {"crushing","heavy"}, new String[] {"weight:5:10"},null);
		makeIdea("hammer", new String[] {"smashing","long"}, new String[] {"weight:5:15","length:10:30"},null);
		makeIdea("campfire", new String[] {"hard","hot"}, new String[] {"sharpness:5:15","hardness:10:20","flammable:1:2"},null);
		makeIdea("saw", new String[] {"slashing","long"}, new String[] {"sharpness:10:20","length:5:20"},null);
		makeIdea("axe", new String[] {"cutting","long"}, new String[] {"hardness:10:50","length:5:20"},null);
		makeIdea("forge", new String[] {"hot","hollow"}, new String[] {"moisture:100:500","weight:5:15","weight:5:15","flammable:2:2"}, "campfire");
		makeIdea("bucket", new String[] {"hollow","rigid"}, new String[] {"rigidity:50:100"},null);
		makeIdea("key", new String[] {"metallic","light"}, new String[] {"hardness:100:100","malleability:15:50","magic:10:50"},null);
		
		makeBiome("forest", 7, new String[] {"tree:5","lake:2","rock:10","flint:2","stick:10","scroll:1"});
		makeBiome("desert", 4, new String[] {"cactus:20","lake:2","sandstone:10","bone:4","treasure:1"});
		makeBiome("plains", 11, new String[] {"tree:1","lake:3","grass:10","scroll:1"});
		makeBiome("mountain", 5, new String[] {"rock:45","bone:6","flint:15","grass:3","vein:12","diamond:1"});
		
	}
	public static Biome getRandomBiome() {
		int length = BIOMEDICT.keySet().size();
		String randomKey = (new ArrayList<String>(BIOMEDICT.keySet())).get((int)(Math.random() * length));
		return BIOMEDICT.get(randomKey);
	}
	private static void makeAspect(String name) { //for base aspects only
		makeAspect(name,null);
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
	
	private static void makeItem(String name, String display, String plural, String[] aspects, String[] properties, boolean moveable) { //shortcut for making a new item
		Aspect[] aspectArray = new Aspect[aspects.length];
		for(int i = 0; i < aspects.length; i++) {
			aspectArray[i] = a(aspects[i]);
		}
		HashMap<String, Integer> propertyMap = new HashMap<String,Integer>();
		for(String s : properties) {
			String[] split = s.split(":");
			propertyMap.put(split[0], Integer.parseInt(split[1]));
		}
		
		
		ITEMDICT.put(name, new Item(name,display,plural,aspectArray,propertyMap, moveable,new HashMap<String,Interaction>()));
	}
	
	private static void setInteractions(String itemName, String[] interactions) {
		HashMap<String, Interaction> interactionMap = new HashMap<String, Interaction>();
		for(String s : interactions) {
			String[] split = s.split(":");
			interactionMap.put(split[0], new Interaction(i(split[1]),i(split[2]),i(split[3]),Integer.parseInt(split[4]),Boolean.parseBoolean(split[5]),Boolean.parseBoolean(split[6])));
		}
		i(itemName).setInteractions(interactionMap);
	}
	private static void makeIdea(String itemName, String[] aspectRecipe, String[] propertyRecipe, String auraName) {
		Aspect[] aspectArray = new Aspect[aspectRecipe.length];
		for(int i = 0; i < aspectRecipe.length; i++) {
			aspectArray[i] = a(aspectRecipe[i]);
		}
		
		Property[] propertyArray = new Property[propertyRecipe.length]; //items are formatted "property:min:max"
		for(int i = 0; i < propertyRecipe.length; i++) {
			String[] splitProperty = propertyRecipe[i].split(":");
			propertyArray[i] = new Property(splitProperty[0], Integer.parseInt(splitProperty[1]), Integer.parseInt(splitProperty[2]));
		}
		
		IDEADICT.put(itemName, new Idea(aspectArray, propertyArray, i(itemName), i(auraName)));
	}
	
	private static void makeBiome(String name, int itemDensity, String[] itemWeights) {
		HashMap<Item,Integer> inputMap = new HashMap<Item,Integer>();
		for(String s : itemWeights) {
			String[] split = s.split(":");
			inputMap.put(i(split[0]), Integer.parseInt(split[1]));
		}
		BIOMEDICT.put(name, new Biome(name, itemDensity, inputMap));
	}
	private static Aspect a(String s) { //shortcut for getting aspects from the dictionary
		return ASPECTDICT.get(s);
	}
	
	private static Item i(String s) { //^ but for items
		return ITEMDICT.get(s);
	}
}
