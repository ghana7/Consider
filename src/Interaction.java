
public class Interaction
{
	private Item toolItem;
	private Item resultItem;
	private boolean destroys;
	private int count;
	
	public Interaction(Item _toolItem, Item _resultItem, int _count, boolean _destroys) {
		toolItem = _toolItem;
		resultItem = _resultItem;
		destroys = _destroys;
		count = _count;
	}
	
	public Item getToolItem() {return toolItem;}
	public Item getResultItem() {return resultItem;}
	public boolean getDestroys() {return destroys;}
	public int getCount() {return count;}
}
