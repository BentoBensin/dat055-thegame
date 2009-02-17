package game;

/*Monster:
Ska kolla om nya positionen är uppnåd, om någon spelare är i närheten
Meddela observers om ändring ska göras */
 
 
//TODO KOMMENTERA!!!!!!!
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Monster extends Client {
	private ArrayList<Client> temp;
	private int moveRemaining;
	private MovePattern currentPattern;
	private int patternIndex;
	public Monster(int id, Point point, int health, String name,HashMap<String,ArrayList<String>>images, Engine engine,GameCharacter gc) {
		super(id, point, health, name, images, engine,gc);
		moveRemaining=0;
		currentPattern=null;
		patternIndex=0;
		
	}

	public Monster(Monster monster) { //TODO Animation not coppied...... BAD
		super(IDGen.generateID(), (Point) monster.getPoint().clone(),
				monster.getHealth(), monster.getName(), new HashMap<String,ArrayList<String>>(), monster.engine);
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				System.out.print("InterruptedException in Monster.run().");
			}
			temp = engine.nearbyClients(this.getPoint(), 100); // antar att
			System.out.println("Monster letar efter närliggande");												// detta funkar
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
		if (patternIndex>=GameCharacter.numberOfPatterns())
			patternIndex=0;
		currentPattern= GameCharachter.getPattern(patternIndex++);	// TODO HÄMTA från länkad lista
		moveRemaining=currentPattern.length; 		 // antal steg att dra hämtas från den nya MovePattern
		setDirection(currentPattern.direction); // Väderstrecket hämtas från den nya MovePattern
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
