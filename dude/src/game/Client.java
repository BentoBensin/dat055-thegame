package game;
import java.io.Serializable;

import gamecharacter.GameCharacter;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import command.Command;

/**
 * Client is responsible for having all the connection
 * with engine and containing an GameCharacter.
 * 
 * @file Client.java
 * @version 0.3
 * @author mathal
 */
public abstract class Client extends Observable implements Runnable, Serializable {

	private Thread aktivitet;
    protected Engine engine;
    private GameCharacter gc; 
    private int id;

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
    	this.engine = engine;
        this.gc=gc;
        gc.setPoint(point);
        aktivitet = new Thread(this);
    }
    public Client(int id, Point point, String name, GameCharacter gc){
    	this.id = id;
    	//TODO if this scenario make an connection to server and have an
    	// communication engine instead!
    	this.engine = null;
        this.gc=gc;
        gc.setPoint(point);
        aktivitet = new Thread(this);
    }
    /*****************************************************/
  
    /**
     * Runs all actions and uses interpretCommand
     * to send actions
     */
    @SuppressWarnings("unchecked")
    public void runActions(){
    	ArrayList<String> tmp = gc.runActions();
    	
    	for(int i=0; i<tmp.size();i++){
    		interpretCommand(tmp.get(i));
    	}
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
    

    
    public GameCharacter getGameCharacter()
    {
    	return gc;
    }
    
    /**
     * Can this client attack an specified client?
     * Checks if this client can attack the incoming client
     * @param c target
     * @return	true if attackable else false
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

