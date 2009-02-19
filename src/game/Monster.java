package game;

/*Monster:
Ska kolla om nya positionen är uppnåd, om någon spelare är i närheten
Meddela observers om ändring ska göras */
 
 
//TODO KOMMENTERA!!!!!!!
import gamecharacter.GameCharacter;
import gamecharacter.MovePattern;

import java.awt.Point;
import java.util.ArrayList;

public class Monster extends Client {
	private ArrayList<Client> temp;
	private int moveRemaining;
	private MovePattern currentPattern;
	private int patternIndex;
	
	public Monster(int id, Point point, int health, String name,GameCharacter gc, Engine engine) {
		super( id, point, name, gc, engine);
		moveRemaining=0;
		currentPattern=null;
		patternIndex=0;
	}

	public Monster(Monster monster) { //TODO Animation not coppied...... BAD
		super(IDGen.generateID(), (Point) monster.getPoint().clone(),
				monster.getName(), monster.getGameCharacter(), monster.engine);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(100); //variabel som vi skapar en funktion som kan ändra
			} catch (InterruptedException e) {
				System.out.print("InterruptedException in Monster.run().");
			}
			temp = engine.nearbyClients(this, 100); 
			System.out.println("Monster letar efter närliggande " + temp.size() );												// detta funkar
			setChanged();
			notifyObservers(temp);
		}
	}

	public Object clone() {
		return new Monster(this);
	}
	
	/**
	 * Sets a new moving pattern for the current monster.
	 */
	public void setPattern()
	{
		System.out.println("Monster.setPattern() is called");
		if (patternIndex>=getGameCharacter().numberOfPatterns())
			patternIndex=0;
		currentPattern= getGameCharacter().getPattern(patternIndex++);	
		moveRemaining=currentPattern.length; 		
		setDirection(currentPattern.direction);
	}
	
	/**
	 * Checks whether there are more steps in the current move pattern
	 * @return true if yes
	 * @return false if no
	 */
	public boolean hasPattern()
	{
		if (moveRemaining<=0)
			return false;
		return true; 
	}
	
	/**
	 * Decreases the remaining steps for the current move pattern
	 */
	public void decreaseRemaning()
	{
		moveRemaining--;
	}
	
}
