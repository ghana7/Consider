import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Aspect> spirit; //all the aspects currently being considered by the player
	private ArrayList<Idea> brain; //all the ideas currently being thought of by the player
	private ArrayList<Item> inventory; //all the items currently in the player's inventory
	
	private int wisdom; //capacity of spirit
	private int intelligence; //capacity of brain
	private int constitution; //capacity of inventory
	private Chunk location;
	
	public Player() {
		wisdom = 5;
		intelligence = 5;
		constitution = 5;
		spirit = new ArrayList<Aspect>();
		brain = new ArrayList<Idea>();
		inventory = new ArrayList<Item>();
	}
	
	public String getName() {return name;}
	public ArrayList<Aspect> getSpirit() {return spirit;}
	public ArrayList<Idea> getBrain() {return brain;}
	public ArrayList<Item> getInventory() {return inventory;}
	
	public int getWisdom() {return wisdom;}
	public int getIntelligence() {return intelligence;}
	public int getConstitution() {return constitution;}
	
	public void setLocation(Chunk l) {location = l;}
	public Chunk getLocation() {return location;}
	
	/* Adds an aspect to spirit if there is space and it does not exist in spirit yet
	 * Returns true if the aspect was added
	 */
	public boolean addToSpirit(Aspect a) {
		if(spirit.size() < wisdom && !spirit.contains(a)) {
			spirit.add(a);
			return true;
		} else {
			return false;
		}
	}
	
	/* Adds an idea to brain if there is space and it does not exist in brain yet
	 * Returns true if the idea was added
	 */
	public boolean addToBrain(Idea i) {
		if(brain.size() < intelligence && !brain.contains(i)) {
			brain.add(i);
			return true;
		} else {
			return false;
		}
	}
	
	/* Adds an item to inventory if there is space
	 * Returns true if the item was added
	 */
	public boolean addToInventory(Item i) {
		if(inventory.size() < constitution) {
			inventory.add(i);
			return true;
		} else {
			return false;
		}
	}
	
	/* Considers an item, adding one of its aspects to the spirit
	 * Returns true if the spirit was actually added (see addToSpirit for details)
	 */
	public boolean consider(Item item) {
		int randIndex = (int)(Math.random() * item.getAspects().length);
		return addToSpirit(item.getAspects()[randIndex]);
	}
	
	/* Considers a series of aspects, adding either a resultant aspect or idea to the spirit or brain, respectively
	 * Returns true if something is successfully added
	 */
	public boolean consider(Aspect[] aspects) {
		for(String key : GameData.ASPECTDICT.keySet()) {
			Aspect value = GameData.ASPECTDICT.get(key);
			if(value.getRecipe() != null && Aspect.getCraftingCode(value.getRecipe()).equals(Aspect.getCraftingCode(aspects))) { //if that aspect in the dict has the same recipe as the array given
				return addToSpirit(value);
			}
		}
		for(String key : GameData.IDEADICT.keySet()) {
			Idea value = GameData.IDEADICT.get(key);
			if(value.getRecipe() != null && Aspect.getCraftingCode(value.getRecipe()).equals(Aspect.getCraftingCode(aspects))) { //if that aspect in the dict has the same recipe as the array given
				return addToBrain(value);
			}
		}
		return false;
	}
	
	public boolean makeItem(Idea idea, Item[] materials) {
		if(idea.getMaterials().length == materials.length) { //make sure you have right amount of materials
			for(int i = 0; i < materials.length; i++) {
				Property p = idea.getMaterials()[i];
				if(!p.isValid(materials[i].getPropertyValue(p.getName()))) { //if the property from the i'th item has a valid value for property i
					return false;
				}
			}
			//THIS NEEDS TO BE CHANGED TO TAKE INTO CONSIDERATION ITEM STATS
			return addToInventory(new Item(idea.getItem().getName())); //adds the item corresponding to that idea
		}
		return false;
	}
	
	
	public void move(String direction) {
		if(location.getPointer(direction) == null) {
			Chunk c = new Chunk();
			location.addPointer(direction,c);
			c.addPointer(GameData.OPPOSITES.get(direction), location);
		}
		location = location.getPointer(direction);
	}
}
