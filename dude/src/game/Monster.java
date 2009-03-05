package game;

import gamecharacter.GameCharacter;
import java.awt.Point;
import java.util.ArrayList;
import main.Envelope;
import main.Strings;
/**
 * Monster extends Client it is a logical representation
 * of an enemy in our game.
 * @file Monster.java
 * @version 0.3
 * @author Mattias, GammelTeddie, Raul
 */
public class Monster extends Client {
	private ArrayList<GameCharacter> temp;
	private int moveRemaining;
	private int intervall;
	private int ressurectionTime;
	private boolean ress;
	
	
/**
* Constructor for Monster
* @param int id - Monster ID
* @param Point point - Monster position
* @Param GameCharacter gc - Character type for this monster
* @param Engine engine - The game engine
*/
	
	public Monster(int id, Point point,GameCharacter gc, Engine engine) {
		super( id, point, gc, engine);
		moveRemaining=0;
		intervall = 100;
		ressurectionTime = 1000*20;
		ress = false;
	}

	public Monster(Monster monster) { 
		super(IDGen.generateID(), (Point) monster.getGameCharacter().getPoint().clone(),
				 monster.getGameCharacter(), monster.engine);
	}
	
	/**
	* The monsters thread. Checks if monster is alive.
	* Let's the monster sleep in intervalls (so it won't update itself too often).
	* Checks if the thread is paused, if so it waits.
	* if the monster is dead and has slept long enough it is ressurected.
	* Runs all actions for this monster.
	* Checks if the player is close.
	* Monster says it has changed and notifys observers.
	*/
	
	public void run() {
		while (true) {
			try {
				if( this.getGameCharacter().isAlive() )
					Thread.sleep(intervall); //variabel som vi skapar en funktion som kan ndra
				else{
					Thread.sleep(ressurectionTime);
					ress = true;
				}
				synchronized(this) {
					while( this.isPaused())
						wait();
				}
			} catch (InterruptedException e) {
				System.out.print("InterruptedException in Monster.run().");
			}
			if( !this.getGameCharacter().isAlive() && ress ) {
				this.getGameCharacter().changeHealth(100);
				getGameCharacter().setAnimationType(Strings.Still);
				ress = false;
			}
			runActions();
			temp = engine.nearbyClients(this, 100);
			if( temp != null)
				//System.out.println("Monster letar efter nrliggande " + temp.size() );												// detta funkar
			setChanged();
			notifyObservers( new Envelope(temp));
		}
	}
	/**
	* Clones this object.
	* @return Monster
	*/
	public Object clone() {
		return new Monster(this);
	}
	
	/**
	 * Decreases the remaining steps for the current move pattern
	 */
	public void decreaseRemaning()
	{
		moveRemaining--;
	}
	
}
