package game;

/*Monster:
Ska kolla om nya positionen �r uppn�d, om n�gon spelare �r i n�rheten
Meddela observers om �ndring ska g�ras */
 
 
//TODO KOMMENTERA!!!!!!!
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class Monster extends Client {
	private ArrayList<Client> temp;

	public Monster(int id, Point point, int health, String name,HashMap<String,ArrayList<String>>images, Engine engine) {
		super(id, point, health, name, images, engine);	
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
}
