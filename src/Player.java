import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Aspect> spirit; //all the aspects currently being considered by the player
	private ArrayList<Idea> brain; //all the ideas currently being thought of by the player
	private ArrayList<Item> inventory; //all the items currently in the player's inventory
	
	private int wisdom; //capacity of spirit
	private int intelligence; //capacity of brain
	private int constitution; //capacity of inventory
	
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
	
	/* Adds an aspect to spirit if there is space
	 * Returns true if the aspect was added
	 */
	public boolean addToSpirit(Aspect a) {
		if(spirit.size() < wisdom) {
			spirit.add(a);
			return true;
		} else {
			return false;
		}
	}
	
	/* Adds an idea to brain if there is space
	 * Returns true if the idea was added
	 */
	public boolean addToBrain(Idea i) {
		if(brain.size() < intelligence) {
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
	
	
}
