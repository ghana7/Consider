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
			default:
				System.out.println("What?");
				break;
			}
		}
	}
	
	
}
