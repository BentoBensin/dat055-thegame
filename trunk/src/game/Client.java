package game;


import gamecharacter.GameCharacter;

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
    private int health;
    private String name;
    private String direction;
    private String animation;
    private LinkedList<String> actions;
    private int position;
    private GameCharacter gc;
    
    /**
     * Constructor for Client object
     * @param id
     * @param cord
     * @param health
     * @param HashMap<String,ArrayList<String>>
     * @param name
     */
    public Client(int id, Point point, String name, GameCharacter gc, Engine engine){
    	aktivitet = new Thread(this);
    	this.id = id;
        this.name = name;
        this.point = point;
        this.engine = engine;
        direction = strings.West;
        animation = strings.Still;
        position = 0;
        this.gc=gc;
        health = 100; // make it come from gc
        actions = new LinkedList<String>();
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
    	if( !gc.isStunned() ) 
    		engine.interpretCommand(cmd, this.id);
    }
    /**
     * Adds an action to the action list
     * This list is used to send actions
     * @param action
     */
    public void addAction(String action) {  	
    	if( !actions.contains(action) ) {
    		//if( action.equals(strings.Attack))
    			actions.addLast(action);
    	//	if( actions.equals(strings.Move))
    		//	actions.addFirst(action);
    	}		
    }
    
    public void addStun( String stun, int time) {
    	gc.stun(time);
    	animation = "hit";
    }
    
    /**
     * Runns all actions and uses interpretCommand
     * to send actions
     */
    public void runActions() {
    	String tmp;
    	/*if( actions.isEmpty() )
    		setAnimationType(strings.Still);*/
    	
    	for(int i=0; i < actions.size() ; i++) {
    		tmp = actions.remove();
    		if ( !gc.isStunned() ){ 
    			interpretCommand(tmp);
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
    	if(position >= gc.getAnimationLength(animation, direction)) {
    		position = 0;
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
     * Change the Clients health
     * @param change of health in int
     */
    public void changeHealth( int change) {
        health = change;
    }
    /**
     * Get's the clients health
     * @return int representing the health
     */
    public int getHealth() {
        return health;
    }
    /**
     * Is the Client Alive
     * @return true if client alive
     */
    public boolean isAlive(){
        return ( health > 0);
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
    	if( c instanceof Player)
    		return true;
    	return false;
    }
    
    /**
     * abstract methods
     */
    public abstract Object clone();
    
}

