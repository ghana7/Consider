
public class Interaction
{
	private Item toolItem;
	private Item resultItem;
	private Item aura;
	private boolean destroys;
	private boolean destroysTool;
	private int count;
	
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
