import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		for(Aspect a : GameData.ASPECTDICT.values()) {
			System.out.println(a);
		}
	
		Player ricc = new Player();
		
		Chunk home = new Chunk();
		ricc.setLocation(home);
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		while(running) {
			String[] arguments = sc.nextLine().split(" ");
			switch(arguments[0]) {
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
				
			}
		}
	}
	
	
}
