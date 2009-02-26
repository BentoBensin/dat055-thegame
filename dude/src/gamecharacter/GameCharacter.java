package gamecharacter;

import items.Item;
import items.Weapon;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import main.strings;

/**
 * This class contains information such as speed, height and width, about a character.
 * @file GameCharacter.java
 * @version 0.3
 * @author Teddie
 *
 */
public abstract class GameCharacter {
	
	private LinkedList<MovePattern> patterns;
	private double speed;
	private int width;
	private int height;
	private int attackRange;   
	private boolean isStunned;
	private Weapon weapon;
	private int health;
	
	private Point point;
	private String name;
	private String direction;
	private String animation;
	private String superAnimation;
	private LinkedList<String> actions;
	private int position;
	private HashMap<Item, Long > cooldowns;
	
	public GameCharacter(Weapon weapon, double speed, int width, int height, 
			int health, String name, Point point){
		
		patterns = new LinkedList<MovePattern>();
		this.weapon = weapon;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.attackRange = 20;
		isStunned = false;
		this.health = health;
		
		this.name = name;
        this.point = point;
        direction = strings.South;
        animation = strings.Still;
        position = 0;
        cooldowns = new HashMap<Item, Long >();
        actions = new LinkedList<String>();
        superAnimation = "";
    
	}

	// h�lla koll p� animeringsstyp
	// positionen i animeringen
	// direction p� snubben
	
	/**
	 * Finds next move pattern.
	 * @param index
	 * @return Next move pattern.
	 */
	public MovePattern getPattern(int index){
		
		if((index < 0) || !(index < patterns.size())){
			throw new IllegalArgumentException();
		}
		
		return patterns.get(index);
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
	 * Returns number of patterns
	 * @return Number of patterns.
	 */
	public int numberOfPatterns(){
		return patterns.size();
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
		System.out.println("-------------------------Players h�lsa: " + health);
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
    		position = 0;
    	animation = type;
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
    		position = 0;
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
	    		if( action.equals(strings.Attack))
	    			setSuperAnimationType(strings.Attack);
	    		actions.addFirst(action);
	    	}	
    	}else {
    		setSuperAnimationType("die");
    	}
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
    public ArrayList runActions() {
    	String tmp;
    	ArrayList<String> tmpList = new ArrayList<String>();
    	if( actions.isEmpty() && superAnimation.equals(""))
    		setAnimationType(strings.Still);
    	
    	for(int i=0; i < actions.size() ; i++) {
    		tmp = actions.remove();
    		if ( !isStunned() ){ 
    			
    			tmpList.add(tmp);
    			if( !tmp.equals("pause"))
    				setAnimationType(tmp);
    		}else {
    			animation = strings.Still;
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

    

	
	
	
}
