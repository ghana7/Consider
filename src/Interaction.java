
public class Interaction
{
	private Item toolItem; //the item you need to do the action
	private Item resultItem; //the item you gain from doing it
	private Item aura; //an item that must be nearby to do it
	private boolean destroys; //is the item destroyed?
	private boolean destroysTool; //is the tool destroyed?
	private int count; //how many result items do you get?
	
	public Interaction(Item _toolItem, Item _resultItem, Item _aura, int _count, boolean _destroys, boolean _destroysTool) {
		toolItem = _toolItem;
		resultItem = _resultItem;
		aura = _aura;
		destroys = _destroys;
		destroysTool = _destroysTool;
		count = _count;
	}
	
	public Item getToolItem() {return toolItem;}
	public Item getResultItem() {return resultItem;}
	public Item getAura() {return aura;}
	public boolean getDestroys() {return destroys;}
	public boolean getDestroysTool() {return destroysTool;}
	public int getCount() {return count;}
}
