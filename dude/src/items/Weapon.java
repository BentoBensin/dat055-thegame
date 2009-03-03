package items;

import java.io.Serializable;
/**
 * The class Weapon extends item,
 * instances of this class represents a weapon
 * that can be used by the player
 * @file Weapon.java
 * @version 1.0
 * @author bache
 */
public class Weapon extends Item implements Serializable{

	final static long serialVersionUID = 124998;
	private int strength, stunTime, range;
	private String stunType;
 
 /**
	*Constructor for Class Weapon
	*@param int strength - The weapons strenght
	*@param int stunTime - The weapons stunTime
	*@param int range - The weapons range
	*@param String stunType - The name of the stunType.
	*@param int cd - The cooldown time
	*/
	public Weapon(int strength,int stunTime,int range, String stunType, int cd) 
	{
		super(cd);
		this.strength=strength;
		this.stunTime=stunTime;
		this.range=range;
		this.stunType=stunType;
	}
	
	/**
	 * Returns the strength of the current weapon
	 * @return strength
	 */
	public int getStrength()
	{
		return strength;
	}
	
	/**
	 * Returns the stuntype of the current weapon
	 * @return stun type
	 */
	public String getStunType()
	{
		return stunType;
	}
	
	/**
	 * Returns the stun time of the current weapon
	 * @return stun time
	 */
	public int getStunTime()
	{
		return stunTime;
	}
	
	/**
	 * Returns the range of the current weapon
	 * @return
	 */
	public int getRange()
	{
		return range;
	}
	
	
}
