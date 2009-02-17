package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @file Player.java
 * @version 0.2
 * @author Mattias Lögdberg
 */
//TODO KOMMENTERA!!!!!!!
public class Player extends Client{

	public Player(int id, Point point, int health, String name, HashMap<String,ArrayList<String>>images, Engine engine){
		super(id, point, health, name,images, engine);
	}
	
	public Player(Player p){	//TODO Animation not coppied...... BAD
		super(IDGen.generateID(), (Point)p.getPoint().clone(), p.getHealth(), p.getName(),new HashMap<String,ArrayList<String>>(), p.engine);
	}
	
	public void run() {
		ArrayList<Client> nc = null;
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage() + " in Player.run()");
			}
			try{
				nc = engine.nearbyClients(this.getPoint(), 500); // korrekt radius?
			}catch(NullPointerException e) {
				System.out.println(e.getMessage() + " in Player.run() trying engine.nearbyClients");
				e.printStackTrace();
			}
			if (nc != null && !nc.isEmpty()) {
				setChanged();
			}
			notifyObservers(nc);
		}
	}
	
	public Object clone() {
		return new Player(this);
	}
}
