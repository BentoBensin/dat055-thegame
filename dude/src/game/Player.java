package game;

import gamecharacter.GameCharacter;

import java.awt.Point;
import java.util.ArrayList;

import main.Envelope;
import main.Strings;

/**
 * Player extends Client, which holds the Thread for updating the GameCharacter
 * This should take a networkmodule instead of engine someday.
 * @file Player.java
 * @version 0.2
 * @author Mattias Lögdberg
 */
public class Player extends Client{
	
	static final long serialVersionUID = 00001234567; 
	private int intervall;
	private int ressurectionIntervall;
	private long ressurectionTime;
	private boolean ress;
	
	/**
	*Constructor for class Player
	*@param int id - the Players id
	*@param GameCharacter gc - Character type
	*@param Engine engine - The game engine
	*/
	public Player(int id, GameCharacter gc, Engine engine){
		super(id, new Point(500,300), gc, engine);		
		init();
	}
	
	/**
	*Constructor for class Player
	*@param int id - the Players id
	*@param GameCharacter gc - Character type
	*/
	public Player( int id, GameCharacter gc){
		super(id,gc.getPoint(),gc, new Engine());
		init();
	}
	
	/**
	*Initiates the player varibles,
	*starts the players thread and
	*adds player to the list of all clients
	*/
	private void init(){
		intervall = 50;
		ressurectionIntervall = intervall*1000;
		ressurectionTime = 0;
		ress = false;
		startThread();
		engine.addClient(this);
	}
	/**
	*Clone Constructor for this class
	*@param Player p - the Player we want to clone
	*/
	public Player(Player p){
		super(IDGen.generateID(), (Point)p.getGameCharacter().getPoint().clone(),p.getGameCharacter(), p.engine);
	}
	
 /**
	* The players thread. Checks if player is alive.
	* Let's the player sleep in intervalls (so it won't update itself too often).
	* Checks if the thread is paused, if so it waits.
	* if the player is dead and has slept long enough it is ressurected.
	* Runs all actions for this player.
	* Checks if the player is close.
	* player says it has changed and notifys observers.
	*/	
	public void run() {
		ArrayList<GameCharacter> gc = null;
		while (true) {
			try {
					Thread.sleep(intervall); //variable for halting the thread.
					if( !getGameCharacter().isAlive() && !ress){
						ress = true;
						ressurectionTime = System.currentTimeMillis()+ressurectionIntervall;
					}
			} catch (InterruptedException e) {
				System.out.println(e.getMessage() + " in Player.run()");
			}
			if( !this.getGameCharacter().isAlive() && ress && (System.currentTimeMillis() >ressurectionTime)) {
				this.getGameCharacter().changeHealth(100);
				getGameCharacter().setAnimationType(Strings.Still);
				ress = false;
			}
			//runs the actions that's in the running queue
			//changes the animation type that should be according to prio of the running queue
			runActions();
			try{
				gc = engine.nearbyClients(this.getGameCharacter().getPoint(), 500); // korrekt radius?
			}catch(NullPointerException e) {
				System.out.println(e.getMessage() + " in Player.run() trying engine.nearbyClients");
				e.printStackTrace();
			}
			setChanged();
			notifyObservers( new Envelope(gc));
		}
	}
	/**
	*Clones this Player
	*@return new Player object
	*/
	public Object clone() {
		return new Player(this);
	}
}
