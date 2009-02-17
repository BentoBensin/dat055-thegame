package game;

/**
 * @file Client.java
 * @version 0.2
 * @author Mattias Lögdberg
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import command.Command;

public abstract class Client extends Observable implements Runnable {

	private Thread aktivitet;
    protected Engine engine;
    private Point point;
    private int health;
    private int id;
    private String name;
    private HashMap<String,ArrayList<String>> images;
    private String direction;
    private int position;
    
    /**
     * Constructor for Client object
     * @param id
     * @param cord
     * @param health
     * @param HashMap<String,ArrayList<String>>
     * @param name
     */
    public Client(int id, Point point, int health, String name, HashMap<String,ArrayList<String>> images, Engine engine){
    	aktivitet = new Thread(this);
    	this.id = id;
        this.health = health;
        this.name = name;
        //this.point = (Point) point.clone();
        this.point = point;
        this.engine = engine;
        this.images = images;
        direction = "south";
        position = 0;
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
    public void interpretCommand(String cmd) {
    	System.out.println("Interpret command körs för " + name);
        engine.interpretCommand(cmd, this.id);
    }
    
    /**
     * Get all the commands that can be executed
     * @return Hashmap with commands
     */
    public HashMap<String, Command> getCommands() {
        return engine.getCommands();
    }
    /**
     * 	Get's the next image in the animation
     * @return string to image
     */
    public String getAnimation() {
    	if(position == 8) //TODO not hårdkodad
    		position = 0;
    	return images.get(direction).get(position++);
    	//return null;
    }
    /**
     * Returns the 
     * @return
     */
    public String getAnimationType() {
    	return direction;
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
    	if(!images.containsKey(type))
    		throw new IllegalArgumentException("A non valid type was sent to setAnimationType for Client " + name);
    	direction = type;
    }
    
    /**
     * Update the objects Coordinates
     * @param cord
     */
    public void updateCoordinates(Point point){
        this.point = (Point)point.clone();
    	//setChanged();
    	//notifyObservers();
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
     * abstract methods
     */
    public abstract Object clone();
}

