import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		/*
		for(Aspect a : GameData.ASPECTDICT.values()) {
			System.out.println(a);
		}
		*/
	
		Player ricc = new Player();
		Chunk home = new Chunk(0,0, GameData.getRandomBiome());
		ArrayList<Chunk> map = new ArrayList<Chunk>();
		map.add(home);
		ricc.setLocation(home);
		look(ricc);
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		while(running) {
			String[] arguments = sc.nextLine().split(" ");
			switch(arguments[0]) {
			case "quit":
			case "stop":
				running = false;
				break;
			case "consider":
				if(arguments.length < 3) {
					System.out.println("Not enough arguments");
				} else {
					Aspect a1 = ricc.getAspect(arguments[1]);
					Aspect a2 = ricc.getAspect(arguments[2]);
					if(a1 == null || a2 == null) {
						System.out.println("Not enough arguments");
					} else {
						ricc.consider(new Aspect[] {a1, a2});
					}
				}
				break;
			case "inspect":
				if(arguments.length < 2) {
					System.out.println("Inspect what?");
				} else {
					Item i = ricc.getLocation().getItem(arguments[1]);
					if(i == null) {
						i = ricc.getItem(arguments[1]);
						if(i == null) {
							System.out.println("Inspect what?");
						} else {
							ricc.consider(i);
						}
					} else {
						ricc.consider(i);
					}
				}
				break;
			case "aspectualize":
				for(Aspect a : ricc.getSpirit()) {
					System.out.println(a);
				}
				break;
			case "move":
			case "go":
			case "perambulate":
			case "nyoom":
				String direction = arguments[1];
				if(GameData.OPPOSITES.keySet().contains(direction)) {
					ricc.move(direction, map);
				} else {
					System.out.println("You can't move that way.");
					break;
				}
			case "look":
				look(ricc);
				break;
			case "take":
				if(arguments.length < 2) {
					System.out.println("Take what?");
				} else {
					Item i = ricc.getLocation().getItem(arguments[1]);
					if(i == null) {
						System.out.println("There is no " + arguments[1] + " here.");
					} else {
						if(i.isMoveable()) {
							if(ricc.addToInventory(i)) {
								ricc.getLocation().getItems().remove(i);
							}
						} else {
							System.out.println("You can't move that.");
						}
					}
				}
				break;
			case "drop":
				if(arguments.length < 2) {
					System.out.println("Drop what?");
				} else {
					Item i = ricc.getItem(arguments[1]);
					if(i == null) {
						System.out.println("You don't have a " + arguments[1] + ".");
					} else {
						ricc.getLocation().addItem(i);
						ricc.getInventory().remove(i);
						System.out.println("You dropped the " + i.getDisplayName() + ".");
					}
				}
				break;
			case "inventory":
				for(Item i : ricc.getInventory()) {
					System.out.println(i.getDisplayName());
				}
				break;
			case "craft":
				if(arguments.length < 2) {
					System.out.println("Craft what?");
				} else {
					Idea i = GameData.IDEADICT.get(arguments[1]);
					if(i == null) {
						System.out.println("You don't have that recipe.");
					} else {
						System.out.println("This recipe requires items with:");
						for(Property p : i.getMaterials()) {
							System.out.println("\t" + p);
						}
						System.out.println("Enter your items:");
						Item[] recipe = new Item[i.getMaterials().length];
						for(int j = 0; j < i.getMaterials().length; j++) {
							System.out.print("Item " + (j + 1) + ": ");
							String input = sc.nextLine();
							recipe[j] = ricc.getItem(input);
						}
						if(ricc.makeItem(i, recipe)) {
							for(Item item : recipe) {
								ricc.getInventory().remove(item);
							}
							System.out.println("You crafted " + i.getName() + "!");
						}
					}
				}
				break;
			case "help":
			case "?":
				System.out.println("Help:");
				System.out.println("\tquit:");
				System.out.println("\t\tquits the game");
				System.out.println("\tconsider <aspect1> <aspect2>");
				System.out.println("\t\tthinks about aspect1 and aspect2 and creates the result aspect or idea.");
				System.out.println("\tinspect:");
				System.out.println("\t\tgathers a random aspect from an item in the world or your inventory.");
				System.out.println("\taspectualize:");
				System.out.println("\t\tdisplays all known aspects.");
				System.out.println("\tmove|go <direction>:");
				System.out.println("\t\tmoves in direction north, south, east, or west.");
				System.out.println("\tlook:");
				System.out.println("\t\tlooks around the player's current location.");
				System.out.println("\ttake <item>:");
				System.out.println("\t\ttakes item from the location.");
				System.out.println("\tdrop <item>:");
				System.out.println("\t\tdrops item from the player's inventory.");
				System.out.println("\tinventory:");
				System.out.println("\t\tshows all items in the player's inventory.");
				System.out.println("\tcraft <idea>:");
				System.out.println("\t\tbegins the crafting of the item corresponding to idea.");
				break;
			case "cheat":
				ricc.addItem(arguments[1]);
				break;
			default:
				if(arguments.length > 1) {
					if(ricc.hasItem(arguments[1])) {
						for(String action : ricc.getItem(arguments[1]).getInteractions().keySet()) {
							if(action.equals(arguments[0])) {
								Interaction interaction = ricc.getItem(arguments[1]).getInteraction(action);
								if(ricc.hasItem(interaction.getToolItem().getName())) {
									for(int i = 0; i < interaction.getCount(); i++) {
										ricc.addItem(interaction.getResultItem().getName());
									}
									if(interaction.getDestroys()) {
										ricc.dropItem(ricc.getItem(arguments[1]).getName());
									}
									break;
								}
							}
						}
					} else {
						System.out.println("You can't " + arguments[0] + " that.");
					}
				} else {
					System.out.println("You can't do that.");
				}
				
				break;
			}
		}
	}
	public static void look(Player p) {
		String output = "You find yourself in a " + p.getLocation().getBiome().getName() + ".\nAround you you see ";
		HashMap<String, Integer> itemCount = new HashMap<String,Integer>();
		for(Item i : p.getLocation().getItems()) {
			if(itemCount.get(i.getDisplayName()) != null) {
				if(itemCount.get(i.getPluralName()) != null) {
					itemCount.put(i.getPluralName(), itemCount.get(i.getPluralName()) + 1);
				} else {
					itemCount.put(i.getPluralName(), 2);
					itemCount.put(i.getDisplayName(), 0);
				}
				
			} else {
				itemCount.put(i.getDisplayName(), 1);
			}
		}
		for(String s : itemCount.keySet()) {
			
			if(itemCount.get(s) > 0) {
				output += "" + itemCount.get(s) + " " + s;
				output += ", ";
			}
			
		}
		output = output.substring(0, output.length() - 2);
		System.out.println(output);
	}
	
}
