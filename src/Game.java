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
				ricc.move(direction, map);
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
			default:
				System.out.println("What?");
				break;
			}
		}
	}
	public static void look(Player p) {
		String output = "You find yourself in a " + p.getLocation().getBiome().getName() + ".\nAround you you see ";
		HashMap<String, Integer> itemCount = new HashMap<String,Integer>();
		for(Item i : p.getLocation().getItems()) {
			if(itemCount.get(i.getDisplayName()) != null) {
				itemCount.put(i.getDisplayName(), itemCount.get(i.getDisplayName()) + 1);
			} else {
				itemCount.put(i.getDisplayName(), 1);
			}
		}
		for(String s : itemCount.keySet()) {
			output += "" + itemCount.get(s) + " " + s;
			if(itemCount.get(s) > 1) {
				output += "s";
			}
			output += ", ";
		}
		output = output.substring(0, output.length() - 2);
		System.out.println(output);
	}
	
}
