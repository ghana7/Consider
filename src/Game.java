
public class Game {

	public static void main(String[] args) {
		for(Aspect a : GameData.ASPECTDICT.values()) {
			System.out.println(a);
		}
	
		Player rik = new Player();
		Item rock = new Item("rock");
		Item club = new Item("club");
		
		System.out.println(rock);
		System.out.println(club);
		
		rik.consider(rock);
		rik.consider(rock);
		rik.consider(rock);
		rik.consider(rock);
		rik.consider(new Aspect[] {rik.getSpirit().get(0), rik.getSpirit().get(1)});
		for(Aspect a : rik.getSpirit()) {
			System.out.println(a);
		}
	}
	
	
}
