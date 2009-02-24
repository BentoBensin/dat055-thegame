package game;


import gamecharacter.GameCharacter;
import items.Item;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;

import main.strings;

import command.Command;

/**
 * Client is responsible for having all the connection
 * with engine and other.
 * 
 * @file Client.java
 * @version 0.3
 * @author mathal
 */
public abstract class Client extends Observable implements Runnable {

	private Thread aktivitet;
    protected Engine engine;
    private Point point;
    private int id;
    private String name;
    private String direction;
    private String animation;
    private String superAnimation;
    private LinkedList<String> actions;
    private int position;
    private GameCharacter gc;
    private HashMap<Item, Long > cooldowns;
    
    public static boolean paused = false; 
    
    /**
     * Constructor for Client object
     * @param id
     * @param cord
     * @param health
     * @param HashMap<String,ArrayList<String>>
     * @param name
     */
    public Client(int id, Point point, String name, GameCharacter gc, Engine engine){
    	this.id = id;
        this.name = name;
        this.point = point;
        this.engine = engine;
        this.gc=gc;
        aktivitet = new Thread(this);
        direction = strings.South;
        animation = strings.Still;
        position = 0;
        cooldowns = new HashMap<Item, Long >();
        actions = new LinkedList<String>();
        superAnimation = "";
    }
    /**
     * Run method that makes things happen
     */
    public abstract void run();
    
    /**
     * Start's the Clients thread
     */
    public void startThread() {
    	aktivitet.start();
    }
    
    /**
     * Takes an command and forward's it to the engine
     * adding this as target for the command
     * @param cmd
     */
    private void interpretCommand(String cmd) {
    	if( !gc.isStunned() && gc.isAlive() ) 
    		engine.interpretCommand(cmd, this.id);
    }
    /**
     * Adds an action to the action list
     * This list is used to send actions
     * @param action
     */
    public void addAction(String action) { 
    	if( gc.isAlive()) {
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
    	gc.stun(time);
    	setSuperAnimationType("hit"); 
    }
    
    /**
     * Runns all actions and uses interpretCommand
     * to send actions
     */
    public void runActions() {
    	String tmp;
    	if( actions.isEmpty() && superAnimation.equals(""))
    		setAnimationType(strings.Still);
    	
    	for(int i=0; i < actions.size() ; i++) {
    		tmp = actions.remove();
    		if ( !gc.isStunned() ){ 
    			interpretCommand(tmp);
    			if( !tmp.equals("pause"))
    				setAnimationType(tmp);
    		}else {
    			animation = strings.Still;
    		}
    	}
    }
    
    /**
     * Get all the commands that can be executed
     * @return HashMap with commands
     */
    public HashMap<String, Command> getCommands() {
        return engine.getCommands();
    }
    /**
     * 	Get's the next image in the animation
     * @return string to image
     */
    public BufferedImage getAnimation() {
    	if( gc.isAlive()) {
	    	if(position >= gc.getAnimationLength(animation, direction) ) {
	    		position = 0;
	    		superAnimation = "";
	    	}
	    	if( !superAnimation.equals(""))
	    		animation = superAnimation;
    	}else{
    		animation = "die";
    		if( position >= gc.getAnimationLength(animation, direction) ) {
    			position = gc.getAnimationLength(animation, direction)-1;
    		}
    	}
    	return gc.getNextImage(animation, direction, position++);
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
    protected void setSuperAnimationType(String type)  {
    	if( type == null)
    		throw new NullPointerException("NullPointer in setAnimationType for Client " + name);
    	if( !type.equals(superAnimation)){
    		position = 0;
    		}
    	superAnimation = type;
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
     * Check's if an object is equal to this
     * @return true if equal
     */
    public boolean equals(Object obj){
        if( this == obj)
            return true;
        if(!(obj instanceof Client))
            return false;
        Client tmp = (Client) obj;
        if( tmp.getID() == id ) {
            return true;
        }
        return false;
    }
    /**
     * Return the Clients ID
     * @return
     */
    public int getID() {
        return id;
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
    
    public GameCharacter getGameCharacter()
    {
    	return gc;
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
     * Can this client attack an specified client?
     * Checks if this client can attack the incoming client
     * @param c target
     * @return	true if attable else false
     */
    public boolean isAttackAble(Client c){
    	if( this instanceof Monster && c instanceof Player)
    		return true;
    	if( this instanceof Player)
    		return true;
    	return false;
    }
    
    
    public synchronized void resumeThread() {
    	if( !Client.paused )
    		notify();
    }
    /**
     * abstract methods
     */
    public abstract Object clone();
    
}

