import java.util.ArrayList;
import java.util.HashMap;

public class Chunk {
	private ArrayList<Item> items; //the items in the chunk
	private String biome;
	private HashMap<String, Chunk> pointers;
	private int x;
	private int y;
	
	
	public Chunk(int _x, int _y) {
		items = new ArrayList<Item>();
		pointers = new HashMap<String, Chunk>();
		x = _x;
		y = _y;
		items.add(new Item("rock"));
		items.add(new Item("rock"));
		items.add(new Item("rock"));
		items.add(new Item("rock"));
		items.add(new Item("rock"));
		items.add(new Item("rock"));
		items.add(new Item("tree"));
		items.add(new Item("lake"));
		items.add(new Item("flint"));
		items.add(new Item("stick"));
	}
	
	public ArrayList<Item> getItems() {return items;}
	public void addItem(Item item) {items.add(item);}
	public String getBiome() {return biome;}
	public void addPointer(String direction, Chunk location) {pointers.put(direction, location);}
	public Chunk getPointer(String direction) {return pointers.get(direction);}
	public int getX() {return x;}
	public int getY() {return y;}
	
	public Item getItem(String name) {
		for(Item i : items) {
			if(i.getName().equals(name)) {
				return i;
			}
		}
		return null;
	}
}
