package gamecharacter;

import items.Item;
import items.Weapon;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import main.Strings;

/**
 * This class contains information such as speed, height and width, about a character.
 * @file GameCharacter.java
 * @version 0.3
 * @author Teddie
 *
 */
public abstract class GameCharacter implements Serializable {
	static final long serialVersionUID = 9123;
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
	private LinkedList<String> actions;
	private int moveRemaining;
	private String skin;
	private int animationIndex;
	private HashMap<Item, Long > cooldowns;
    public boolean paused;
    
	public GameCharacter(Weapon weapon, double speed, int width, int height, 
			int health, String name, Point point, String skin, boolean isPlayer){
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
        moveRemaining = 0;
        animationIndex=0;
        this.skin = skin;
	}
	
	/**
	 * Finds next move pattern.
	 * @param index
	 * @return Next move pattern.
	 */
	public void followPattern(){
		if( moveRemaining-- > 0 )
		{
			this.setDirection(direction);
			this.addAction(Strings.Move);
			return;
		}
		else{
			MovePattern tmp = patterns.pop();
			direction = tmp.direction;
			moveRemaining = tmp.length;
			patterns.addLast(tmp);
			followPattern();
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
	/**
	 * Get's the gamecharacters primary weapon
	 * @return weapon
	 */
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
	 * Checks if the possible action is an valid action
	 * @param action
	 * @return true if action
	 */
	public boolean isAction(String action){
		GameAnimationData gad = GameAnimationData.getInstance();
		return (gad.listActions(skin).contains(action));
	}
	
    /**
     * Set's an new type of animation
     * Unvalid types will throw an IllegalArgumentException
     * if type is null an NullPointerException will be thrown
     * @param String of type
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void setAnimationType(String type)  {
    	if( type == null)
    		throw new NullPointerException("NullPointer in setAnimationType for Client " + name);
    	if( isAction(type) )
    	{
	    	if( !type.equals(animation))
	    		animationIndex = 0;
	    	animation = type;
    	}
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
	    		actions.addLast(action);
	    	}	
    	}else {
    		setAnimationType(Strings.Die);
    	}
    }
    
    /**
     * Get's the next action from the actions list 
     * This removes the action from the list
     * @return String representing the action
     */
    public String getAction()
    {
    	if (!actions.isEmpty())
    		return actions.pop();
    	else return null;
    }
    /**
     * Adds a stun to the player with speciefied time
     * @param stun
     * @param time
     */
    public void addStun( String stun, int time) {
    	stun(time);
    	setAnimationType(Strings.Hit); 
    }
    

    
    /**
     * Returns a list with actions
     * @return ArrayList<String>
     */
    public ArrayList<String> runActions() {
    	String runAction;
    	ArrayList<String> runList = new ArrayList<String>();
    	for(int i=0; i < actions.size() ; i++) {
    		runAction = actions.remove();
    		if ( !isStunned() ){ 
    			runList.add(runAction);
    			// better to do check if animationType and then set it...
    			setAnimationType(runAction);
    		}
    	}
    	return runList;
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
    /**
     * Checks if the Gamecharacter is a player
     * @return true if player
     */
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
	/**
	 * gets the next image in the animation
	 * @return BufferedImage
	 */
    public BufferedImage getImage() {
    	GameAnimationData gad = GameAnimationData.getInstance();
    	if( animationIndex >= gad.size(skin, animation, direction) ) {
    		animationIndex = 0;
    		if( !isAlive() && animation.equals(Strings.Die)){
    			animationIndex = gad.size(skin, animation, direction)-1;
    		}
    		if( !isAlive())
    			animation = Strings.Die;
    		if( isAlive())
    			animation = Strings.Still;
      	}
    	return gad.getImage(skin, animation, direction, animationIndex++); 
    }
}
