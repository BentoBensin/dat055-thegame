package items;

import java.io.Serializable;

/**
 * This abstract class represents a gameitem
 * that can be used by the player
 * cooldown will be used with a timer.
 * When cooldown > 0 you cant use the item.
 * @file Item.java
 * @version 1.0
 * @author bache
 *
 */
public abstract class Item implements Serializable {

	private int cooldown;
	/**
	*Constructor for Class Item
	*@param cd - the cooldown time
	*/
	public Item(int cd)
	{
		cooldown=cd;
	}
	
	/**
	*returns the cooldown
	*@return int cooldown - the cooldown time
	*/
	public int getCoolDown() {
		return cooldown;
	}
	
}
