package game;

import gamecharacter.GameCharacter;

import java.awt.Point;
import java.util.ArrayList;

import main.strings;

/**
 * @file Player.java
 * @version 0.2
 * @author Mattias L�gdberg
 */
//TODO KOMMENTERA!!!!!!!
public class Player extends Client{

	private int intervall;
	private int ressurectionIntervall;
	private long ressurectionTime;
	private boolean ress;
	
	public Player(int id, String name, GameCharacter gc, Engine engine){
		super(id, new Point(500,300), name, gc, engine);		
		init();
	}
	public Player( int id, String name, GameCharacter gc){
		super(id,new Point(500,300),name,gc, new Engine());
		init();
	}
	
	private void init(){
		intervall = 50;
		ressurectionIntervall = intervall*10;
		ressurectionTime = 0;
		ress = false;
		startThread();
		engine.addClient(this);
	}
	
	public Player(Player p){	//TODO Animation not coppied...... BAD
		super(IDGen.generateID(), (Point)p.getPoint().clone(), p.getName(),p.getGameCharacter(), p.engine);
	}
	
	public void run() {
		ArrayList<Client> nc = null;
		while (true) {
			System.out.println("k�r");
			try {
					Thread.sleep(intervall); //variabel som vi skapar en funktion som kan �ndra
					if( !getGameCharacter().isAlive() && !ress){
						ress = true;
						ressurectionTime = System.currentTimeMillis()+ressurectionIntervall;
					}
			} catch (InterruptedException e) {
				System.out.println(e.getMessage() + " in Player.run()");
			}
			if( !this.getGameCharacter().isAlive() && ress && (System.currentTimeMillis() >ressurectionTime)) {
				this.getGameCharacter().changeHealth(100);
				setSuperAnimationType(strings.Still);
				ress = false;
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
			setChanged();
			notifyObservers(nc);
		}
	}
	
	public Object clone() {
		return new Player(this);
	}
}
