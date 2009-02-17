package game;

/*Monster:
Ska kolla om nya positionen �r uppn�d, om n�gon spelare �r i n�rheten
Meddela observers om �ndring ska g�ras */
 
 
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
		super(id, point, health, name, gc, engine);
		moveRemaining=0;
		currentPattern=null;
		patternIndex=0;
	}

	public Monster(Monster monster) { //TODO Animation not coppied...... BAD
		super(IDGen.generateID(), (Point) monster.getPoint().clone(),
				monster.getHealth(), monster.getName(), monster.getGameCharacter(), monster.engine);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.print("InterruptedException in Monster.run().");
			}
			temp = engine.nearbyClients(this, 100); // antar att
			System.out.println("Monster letar efter n�rliggande");												// detta funkar
			//if (temp != null && !temp.isEmpty()) {
				setChanged();
				notifyObservers(temp);
			//}
			
		}
	}

	public Object clone() {
		return new Monster(this);
	}
	
	/**
	 * Gets a new move pattern for the current monster
	 */
	public void setPattern()
	{
		if (patternIndex>=getGameCharacter().numberOfPatterns())
			patternIndex=0;
		currentPattern= getGameCharacter().getPattern(patternIndex++);	// TODO H�MTA fr�n l�nkad lista
		moveRemaining=currentPattern.length; 		 // antal steg att dra h�mtas fr�n den nya MovePattern
		setDirection(currentPattern.direction); // V�derstrecket h�mtas fr�n den nya MovePattern
	}
	
	/**
	 * Checks whether there are more steps in the current move pattern
	 * @return true if yes
	 * @return false if no
	 */
	public boolean hasPattern()
	{
		if (moveRemaining==0)
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
