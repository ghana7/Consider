
public class Idea {
	private String name; //the name of the idea
	private Aspect[] recipe; //the aspects needed to discover this idea
	private Item[] materials; //the materials necessary to actualize this idea
	private Item item; //the item gained
	
	public Idea(Aspect[] _recipe, Item[] _materials, Item _item) {
		name = _item.getName();
		recipe = _recipe;
		materials = _materials;
		item = _item;
	}
	
	public String getName() {return name;}
	public Aspect[] getRecipe() {return recipe;}
	public Item[] getMaterials() {return materials;}
	public Item getItem() {return item;}
}