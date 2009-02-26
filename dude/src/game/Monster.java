package game;

/*Monster:
Ska kolla om nya positionen är uppnåd, om någon spelare är i närheten
Meddela observers om ändring ska göras */
 
 
//TODO KOMMENTERA!!!!!!!
import gamecharacter.GameCharacter;
import gamecharacter.MovePattern;
import main.Strings;
import java.awt.Point;
import java.util.ArrayList;

public class Monster extends Client {
	private ArrayList<GameCharacter> temp;
	private int moveRemaining;
	private MovePattern currentPattern;
	private int patternIndex;
	private int intervall;
	private int ressurectionTime;
	private boolean ress;
	
	public Monster(int id, Point point, int health, String name,GameCharacter gc, Engine engine) {
		super( id, point, name, gc, engine);
		moveRemaining=0;
		currentPattern=null;
		patternIndex=0;
		intervall = 100;
		ressurectionTime = 1000*20;
		ress = false;
	}

	public Monster(Monster monster) { //TODO Animation not coppied...... BAD
		super(IDGen.generateID(), (Point) monster.getGameCharacter().getPoint().clone(),
				monster.getGameCharacter().getName(), monster.getGameCharacter(), monster.engine);
	}

	public void run() {
		while (true) {
			try {
				if( this.getGameCharacter().isAlive() )
					Thread.sleep(intervall); //variabel som vi skapar en funktion som kan ändra
				else{
					Thread.sleep(ressurectionTime);
					ress = true;
				}
				synchronized(this) {
					while( Client.paused)
						wait();
				}
			} catch (InterruptedException e) {
				System.out.print("InterruptedException in Monster.run().");
			}
			if( !this.getGameCharacter().isAlive() && ress ) {
				this.getGameCharacter().changeHealth(100);
				getGameCharacter().setSuperAnimationType(Strings.Still);
				ress = false;
			}
			runActions();
			temp = engine.nearbyClients(this, 100);
			if( temp != null)
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
		getGameCharacter().setDirection(currentPattern.direction);
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
