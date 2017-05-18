import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		/*
		for(Aspect a : GameData.ASPECTDICT.values()) {
			System.out.println(a);
		}*/
	
		Player ricc = new Player();
		
		Chunk home = new Chunk();
		ricc.setLocation(home);
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		while(running) {
			String[] arguments = sc.nextLine().split(" ");
			switch(arguments[0]) {
			case "quit":
			case "stop":
				running = false;
				break;
			case "move":
			case "go":
			case "perambulate":
			case "nyoom":
				String direction = arguments[1];
				ricc.move(direction);
				break;
			case "consider":
				Aspect a1 = ricc.getAspect(arguments[1]);
				Aspect a2 = ricc.getAspect(arguments[2]);
				if(a1 == null || a2 == null) {
					System.out.println("Not enough arguments");
				} else {
					ricc.consider(new Aspect[] {a1, a2});
				}
				break;
			case "inspect":
				if(arguments.length < 2) {
					System.out.println("Inspect what?");
				} else {
					Item i = ricc.getLocation().getItem(arguments[1]);
					if(i == null) {
						System.out.println("Inspect what?");
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
			case "look":
				for(Item i : ricc.getLocation().getItems()) {
					System.out.println(i.getDisplayName());
				}
				break;
			case "take":
				if(arguments.length < 2) {
					System.out.println("Take what?");
				} else {
					Item i = ricc.getLocation().getItem(arguments[1]);
					if(i == null) {
						System.out.println("There is no " + arguments[1] + " here.");
					} else {
						if(ricc.addToInventory(i)) {
							ricc.getLocation().getItems().remove(i);
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
	
	
}
