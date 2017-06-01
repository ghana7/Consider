import java.util.ArrayList;
import java.util.HashMap;

public class Chunk {
	private ArrayList<Item> items; //the items in the chunk
	private Biome biome; //the biome this chunk is
	private HashMap<String, Chunk> pointers; //chunks you can get to from this chunk
	private int x;
	private int y;
	
	
	public Chunk(int _x, int _y, Biome _biome) {
		items = new ArrayList<Item>();
		pointers = new HashMap<String, Chunk>();
		x = _x;
		y = _y;
		biome = _biome;
		for(int i = 0; i < biome.getItemDensity(); i++) {
			items.add(biome.getRandomItem());
		}
	}
	
	public ArrayList<Item> getItems() {return items;}
	public void addItem(Item item) {items.add(item);}
	public Biome getBiome() {return biome;}
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
	
	public boolean hasItem(String name) {
		if(name.equals("")) {
			return true;
		}
		return getItem(name) != null;
	}
}
