package game;

import gamecharacter.GameCharacter;

import java.awt.Point;
import java.util.ArrayList;

/**
 * @file Player.java
 * @version 0.2
 * @author Mattias Lögdberg
 */
//TODO KOMMENTERA!!!!!!!
public class Player extends Client{

	public Player(int id, Point point, int health, String name, GameCharacter gc, Engine engine){
		super(id, point, name, gc, engine);
	}
	
	public Player(Player p){	//TODO Animation not coppied...... BAD
		super(IDGen.generateID(), (Point)p.getPoint().clone(), p.getName(),p.getGameCharacter(), p.engine);
	}
	
	public void run() {
		ArrayList<Client> nc = null;
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage() + " in Player.run()");
			}
			//runs the actions that's in the running queue
			//changes the animation type that should be according to prio of the running queue
			runActions();
			try{
				nc = engine.nearbyClients(this.getPoint(), 500); // korrekt radius?
			}catch(NullPointerException e) {
				System.out.println(e.getMessage() + " in Player.run() trying engine.nearbyClients");
				e.printStackTrace();
			}
			if (nc != null && !nc.isEmpty()) {
				System.out.println("ändrat");
				setChanged();
			}
			notifyObservers(nc);
		}
	}
	
	public Object clone() {
		return new Player(this);
	}
}
