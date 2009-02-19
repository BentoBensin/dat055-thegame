package items;

public class Weapon extends Item {

	private int strength, stunnTime, range;
	private String stunnType;
	
	public Weapon(int strength,int stunnTime,int range, String stunnType, int cd) 
	{
		super(cd);
		this.strength=strength;
		this.stunnTime=stunnTime;
		this.range=range;
		this.stunnType=stunnType;
	}
	
	/**
	 * Returns the strength of the current weapon
	 * @return strength
	 */
	public int getStrenght()
	{
		return strength;
	}
	
	/**
	 * Returns the stuntype of the current weapon
	 * @return stunn type
	 */
	public String getStuntype()
	{
		return stunnType;
	}
	
	/**
	 * Returns the stun time of the current weapon
	 * @return stunn time
	 */
	public int getStuntime()
	{
		return stunnTime;
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
