
public class Idea {
	private String name; //the name of the idea
	private Aspect[] recipe; //the aspects needed to discover this idea
	private Property[] materials; //the materials necessary to actualize this idea
	private Item item; //the item gained
	private Item aura; //an item that must be in the current location to craft the item
	
	public Idea(Aspect[] _recipe, Property[] _materials, Item _item, Item _aura) {
		name = _item.getName();
		recipe = _recipe;
		materials = _materials;
		item = _item;
		aura = _aura;
	}
	
	public String getName() {return name;}
	public Aspect[] getRecipe() {return recipe;}
	public Property[] getMaterials() {return materials;}
	public Item getItem() {return item;}
	public Item getAura() {return aura;}
}
