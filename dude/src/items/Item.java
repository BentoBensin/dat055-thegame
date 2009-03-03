package items;

import java.io.Serializable;

/**
 * 
 * @author bache
 *
 */
public abstract class Item implements Serializable {

	private int cooldown;
	
	public Item(int cd)
	{
		cooldown=cd;
	}
	
	public int getCoolDown() {
		return cooldown;
	}
	
}
