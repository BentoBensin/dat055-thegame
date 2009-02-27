package game;

/*Monster:
Ska kolla om nya positionen r uppnd, om ngon spelare r i nrheten
Meddela observers om ndring ska gras */
 
 
//TODO KOMMENTERA!!!!!!!
import gamecharacter.GameCharacter;
import main.Strings;
import java.awt.Point;
import java.util.ArrayList;

public class Monster extends Client {
	private ArrayList<GameCharacter> temp;
	private int moveRemaining;
	private int intervall;
	private int ressurectionTime;
	private boolean ress;
	
	public Monster(int id, Point point,GameCharacter gc, Engine engine) {
		super( id, point, gc, engine);
		moveRemaining=0;
		intervall = 100;
		ressurectionTime = 1000*20;
		ress = false;
	}

	public Monster(Monster monster) { //TODO Animation not coppied...... BAD
		super(IDGen.generateID(), (Point) monster.getGameCharacter().getPoint().clone(),
				 monster.getGameCharacter(), monster.engine);
	}

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
				getGameCharacter().setSuperAnimationType(Strings.Still);
				ress = false;
			}
			runActions();
			temp = engine.nearbyClients(this, 100);
			if( temp != null)
				System.out.println("Monster letar efter nrliggande " + temp.size() );												// detta funkar
			setChanged();
			notifyObservers(temp);
		}
	}

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
