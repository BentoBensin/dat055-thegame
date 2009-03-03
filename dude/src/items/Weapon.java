package items;

import java.io.Serializable;

public class Weapon extends Item implements Serializable{

	final static long serialVersionUID = 124998;
	private int strength, stunTime, range;
	private String stunType;
	
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
