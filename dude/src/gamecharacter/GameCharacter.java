package gamecharacter;

import items.Item;
import items.Weapon;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import main.Strings;
import java.io.Serializable;

/**
 * This class contains information such as speed, height and width, about a character.
 * @file GameCharacter.java
 * @version 0.3
 * @author Teddie
 *
 */
public abstract class GameCharacter implements Serializable {
	
	private LinkedList<MovePattern> patterns;
	private double speed;
	private int width;
	private int height;
	private int attackRange;   
	private boolean isStunned;
	private Weapon weapon;
	private int health;
	private boolean isPlayer;
	private Point point;
	private String name;
	private String direction;
	private String animation;
	private String superAnimation;
	private LinkedList<String> actions;
	private int moveRemaining;
	private String skin;
	private int animationIndex;
	private HashMap<Item, Long > cooldowns;
    public boolean paused;
    
	public GameCharacter(Weapon weapon, double speed, int width, int height, 
			int health, String name, Point point, boolean isPlayer){
		paused = false;
		patterns = new LinkedList<MovePattern>();
		this.weapon = weapon;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.attackRange = 20;
		isStunned = false;
		this.health = health;
		this.isPlayer = isPlayer;
		this.name = name;
        this.point = point;
        direction = Strings.South;
        animation = Strings.Still;
        cooldowns = new HashMap<Item, Long >();
        actions = new LinkedList<String>();
        superAnimation = "";
        moveRemaining = 0;
        animationIndex=0;
        skin="ShroomsMan";
	}

	// hlla koll p animeringsstyp
	// positionen i animeringen
	// direction p snubben
	
	/**
	 * Finds next move pattern.
	 * @param index
	 * @return Next move pattern.
	 */
	public void followPattern(){
		if( moveRemaining-- != 0 )
			return;
		else{
			MovePattern tmp = patterns.pop();
			direction = tmp.direction;
			moveRemaining = tmp.length;
			patterns.addLast(tmp);
		}
	}

	/**
	 * Adds a new pattern to pattern list.
	 * @param pattern
	 */
	public void addPattern(MovePattern pattern){
		if(pattern == null){
			throw new NullPointerException();
		}
		patterns.add(pattern);
	}
	
	/**
	 * Adds an stun to the GameCharacter
	 * param is the stun time in milliseconds
	 * @param time
	 */
	public void stun( int time) {
		if( time > 0){
			//isStunned = true;
		}
	}
	/**
	 * Checks if the GameCharacter is stunned
	 * @return true if stunned
	 */
	public boolean isStunned() {
		return isStunned;
	}
	
	/**
	 * Returns speed of character.
	 * @return Speed of character.
	 */
	public double getSpeed(){
		return speed;
	}
	
	/**
	 * Returns width of character.
	 * @return Width of character.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Returns height of character.
	 * @return Height of character.
	 */
	public int getHeight(){
		return height;
	}
	
	public Weapon getPrimary() {
		return weapon;
	}
	
	/**
	 * Returns the attackrange for the specifik character
	 * @return distance
	 */
	public int getAttackRange()
	{
		return attackRange;
	}
	/**
	 * Changes the chracters health
	 * @return new health
	 */
	public int changeHealth(int healthChange)
	{
		health += healthChange;
		System.out.println("-------------------------Players hlsa: " + health);
		return health;
	}
	/**
	 * Checks if the character is alive
	 */
	public boolean isAlive()
	{
		return (health>0);
	}
	

    /**
     * Set's an new type of animation
     * Unvalid types will throw an IllegalArgumentException
     * if type is null an NullPointerException will be thrown
     * @param String of type
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    private void setAnimationType(String type)  {
    	if( type == null)
    		throw new NullPointerException("NullPointer in setAnimationType for Client " + name);
    	if( !type.equals(animation))
    		animationIndex = 0;
    	animation = type;
    }
    public String getAnimationType()
    {
    	return animation;
    }
    /**
     * Set's an new type of superAnimation this is an superior
     * animation type. Overrides normal animations.
     * Unvalid types will throw an IllegalArgumentException
     * if type is null an NullPointerException will be thrown
     * @param String of type
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void setSuperAnimationType(String type)  {
    	if( type == null)
    		throw new NullPointerException("NullPointer in setAnimationType for Client " + name);
    	if( !type.equals(superAnimation)){
    		animationIndex = 0;
    		}
    	superAnimation = type;
    }
    
    /**
     * Add cooldown to the player, makes the added items
     * unusable for the time in milliseconds
     * @param item to be linked to the cooldown
     * @param cooldown time in milliseconds
     * @return true if cooldown added else false 
     */
    public boolean addCoolDown(Item item, int time){
    	if( item != null && !cooldowns.containsKey(item) ){
    		cooldowns.put(item, new Long( System.currentTimeMillis()+time) );
    		return true;
    	}
    	return false;
    }
    /**
     * checks if the client has an cooldown on the specified
     * item
     * @param item to check 
     * @return true if has cooldown on that item 
     */
    public boolean hasCoolDown(Item item){
    	if( cooldowns.containsKey(item)) {
    		if( System.currentTimeMillis() > cooldowns.get(item) ){
    			cooldowns.remove(item);
    			return false;
    		}
    		return true;
    	}
    	return false;
    }
    
    
    
    /**
     * Sets the looking direction for the client
     * @param newDirection
     */
    public void setDirection(String newDirection)
    {
    	direction=newDirection;
    }
    
    /**
     * Gets the direction of the client
     * @return direction
     */
    public String getDirection()
    {
    	return direction;
    }    

    /**
     * Adds an action to the action list
     * This list is used to send actions
     * @param action
     */
    public void addAction(String action) { 
    	if(isAlive()) {
	    	if( !actions.contains(action) ) {
	    		if( action.equals(Strings.Attack))
	    			setSuperAnimationType(Strings.Attack);
	    		actions.addLast(action);
	    	}	
    	}else {
    		setSuperAnimationType("die");
    	}
    }
    
    public String getAction()
    {
    	if (!actions.isEmpty())
    		return actions.pop();
    	else return null;
    }
    public void addStun( String stun, int time) {
    	stun(time);
    	setSuperAnimationType("hit"); 
    }
    
    /**
     * Sets a new point.
     * @param newPoint
     */
    
    /**
     * Runs all actions and uses interpretCommand
     * to send actions
     */
    public ArrayList<String> runActions() {
    	String tmp;
    	ArrayList<String> tmpList = new ArrayList<String>();
    	if( actions.isEmpty() && superAnimation.equals(""))
    		setAnimationType(Strings.Still);
    	
    	for(int i=0; i < actions.size() ; i++) {
    		tmp = actions.remove();
    		if ( !isStunned() ){ 
    			
    			tmpList.add(tmp);
    			if( !tmp.equals("pause"))
    				setAnimationType(tmp);
    		}else {
    			animation = Strings.Still;
    		}
    	}
    	return tmpList;
    }
    
    public void setPoint(Point newPoint){
    	point = newPoint;
    }
    
    /**
     * Update the objects Coordinates
     * @param cord
     */
    public void updateCoordinates(Point point){
        this.point = (Point)point.clone();
    }
    /**
     * Return the objects Coordinates
     * @return the objects Coordinates
     */
    public Point getPoint(){
        return point;
    }
    /**
     * Get's the Clients name
     * @return String
     */
    public String getName(){
        return name;
    }
    /**
     * Set's the Clients name
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    public boolean isPlayer() {
    	return isPlayer;
    }
    /**
	 * Decreases the remaining steps for the current move pattern
	 */
	public void decreaseRemaning()
	{
		moveRemaining--;
	}
    public int getAnimationIndex()
    {
    	return animationIndex;
    }
    public void setAnimationIndex(int index)
    {
    	animationIndex = index;
    }
    public String getSkin()
    {
    	return skin;
    }

}
