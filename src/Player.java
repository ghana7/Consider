import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Aspect> spirit; //all the aspects currently being considered by the player
	private ArrayList<Idea> brain; //all the ideas currently being thought of by the player
	private ArrayList<Item> inventory; //all the items currently in the player's inventory
	private int intelligence; //capacity of brain
	private int constitution; //capacity of inventory
	private Chunk location;
	
	public Player() {
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
	
	public int getIntelligence() {return intelligence;}
	public int getConstitution() {return constitution;}
	
	public void setLocation(Chunk l) {location = l;}
	public Chunk getLocation() {return location;}
	
	/* Adds an aspect to spirit if there is space and it does not exist in spirit yet
	 * Returns true if the aspect was added
	 */
	public boolean addToSpirit(Aspect a) {
		if(!spirit.contains(a)) {
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
			System.out.println("You got a " + i.getDisplayName() + ".");
			return true;
		} else {
			System.out.println("You can't hold any more.");
			return false;
		}
	}
	
	/* Considers an item, adding one of its aspects to the spirit
	 * Returns true if the spirit was actually added (see addToSpirit for details)
	 */
	public boolean consider(Item item) {
		int randIndex = (int)(Math.random() * item.getAspects().length);
		Aspect a = item.getAspects()[randIndex];
		System.out.println("The aspect of the " + a.getName() + " has been discovered");
		return addToSpirit(a);
	}
	
	/* Considers a series of aspects, adding either a resultant aspect or idea to the spirit or brain, respectively
	 * Returns true if something is successfully added
	 */
	public boolean consider(Aspect[] aspects) {
		for(String key : GameData.ASPECTDICT.keySet()) {
			Aspect value = GameData.ASPECTDICT.get(key);
			if(value.getRecipe() != null && Aspect.getCraftingCode(value.getRecipe()).equals(Aspect.getCraftingCode(aspects))) { //if that aspect in the dict has the same recipe as the array given
				System.out.println("The aspect of the " + value.getName() + " has been discovered." );
				return addToSpirit(value);
			}
		}
		for(String key : GameData.IDEADICT.keySet()) {
			Idea value = GameData.IDEADICT.get(key);
			if(value.getRecipe() != null && Aspect.getCraftingCode(value.getRecipe()).equals(Aspect.getCraftingCode(aspects))) { //if that aspect in the dict has the same recipe as the array given
				System.out.println("An idea for " + value.getName() + " has been created." );
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
					System.out.println("Material " + (i + 1) + " failed to meet requirements.");
					return false;
				}
			}
			//THIS NEEDS TO BE CHANGED TO TAKE INTO CONSIDERATION ITEM STATS
			if(location.hasItem(idea.getAura().getName())) {
				return addToInventory(new Item(idea.getItem().getName())); //adds the item corresponding to that idea
			} else {
				System.out.println("The area is missing a " + idea.getAura().getDisplayName() + ".");
				return false;
			}
			
		}
		return false;
	}
	
	
	public void move(String direction, ArrayList<Chunk> map) {
		if(location.getPointer(direction) == null) {
			int oldX = location.getX();
			int oldY = location.getY();
			int newX = oldX;
			int newY = oldY;
			boolean correct = true;
			Chunk c;
			switch(direction) {
			case "west":
				newX--;
				break;
			case "east":
				newX++;
				break;
			case "north":
				newY++;
				break;
			case "south":
				newY--;
				break;
			default:
				correct = false;
				break;
			}
			if(correct) {
				c = new Chunk(newX,newY, GameData.getRandomBiome());
				boolean locationExists = false;
				for(Chunk ch : map) {
					if(ch.getX() == c.getX() && ch.getY() == c.getY()) {
						location.addPointer(direction,ch);
						ch.addPointer(GameData.OPPOSITES.get(direction), location);
						locationExists = true;
					}
				}
				if(!locationExists) {
					map.add(c);
					location.addPointer(direction,c);
					c.addPointer(GameData.OPPOSITES.get(direction), location);
				}
			}
		}
		location = location.getPointer(direction);
	}
	
	public Aspect getAspect(String name) {
		for(Aspect a : spirit) {
			if(a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
	
	public Item getItem(String name) {
		for(Item i : inventory) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	
	public void addItem(String name) {
		inventory.add(new Item(name));
	}
	public void dropItem(String name) {
		Item toRemove = null;
		for(Item i : inventory) {
			if(i.getName().equals(name)) {
				toRemove = i;
				break;
			}
		}
		inventory.remove(toRemove);
	}
	public boolean hasItem(String name) {
		return getItem(name) != null;
	}
	
	public Idea getIdea(String name) {
		for(Idea i : brain) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
	public boolean hasIdea(String name) {
		return getIdea(name) != null;
	}
}
