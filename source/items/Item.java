package items;

/**
 * 
 * @author bache
 *
 */
public abstract class Item {

	private int cooldown;
	
	public Item(int cd)
	{
		cooldown=cd;
	}
	
	public int getCoolDown() {
		return cooldown;
	}
	
}
