import java.util.HashMap;

public class Biome
{
	private String name;
	private HashMap<Item,Integer> items;
	private Item[] weightedItems;
	private int totalItemWeight;
	private int itemDensity;
	public Biome(String _name, int _itemDensity, HashMap<Item,Integer> _items) {
		name = _name;
		itemDensity = _itemDensity;
		items = _items;
		for(Item i : items.keySet()) {
			totalItemWeight += items.get(i);
		}
		weightedItems = new Item[totalItemWeight];
		int index = 0;
		for(Item i : items.keySet()) {
			for(int j = 0; j < items.get(i); j++) {
				weightedItems[index] = i;
				index++;
			}
		}
	}
	public String getName() {return name;}
	public int getItemDensity() {return itemDensity;}
	public Item getRandomItem() {
		int randIndex = (int)(Math.random() * totalItemWeight);
		return weightedItems[randIndex];
	}
}
