package game;

import gamecharacter.GameCharacter;

import java.awt.Point;
import java.util.ArrayList;

import main.Strings;

/**
 * @file Player.java
 * @version 0.2
 * @author Mattias Lögdberg
 */
//TODO KOMMENTERA!!!!!!!
public class Player extends Client{
	
	static final long serialVersionUID = 00001234567; 
	private int intervall;
	private int ressurectionIntervall;
	private long ressurectionTime;
	private boolean ress;
	
	public Player(int id, GameCharacter gc, Engine engine){
		super(id, new Point(500,300), gc, engine);		
		init();
	}
	public Player( int id, GameCharacter gc){
		super(id,new Point(500,300),gc, new Engine());
		init();
	}
	/*public Player(int id, GameCharacter gc, String host, Integer port){
		super(id, new Point(500,300), gc);		
		init();
	}*/	
	
	private void init(){
		intervall = 50;
		ressurectionIntervall = intervall*10;
		ressurectionTime = 0;
		ress = false;
		startThread();
		engine.addClient(this);
	}
	
	public Player(Player p){
		super(IDGen.generateID(), (Point)p.getGameCharacter().getPoint().clone(),p.getGameCharacter(), p.engine);
	}
	
	public void run() {
		ArrayList<GameCharacter> gc = null;
		while (true) {
			System.out.println("kör");
			try {
					Thread.sleep(intervall); //variabel som vi skapar en funktion som kan ändra
					if( !getGameCharacter().isAlive() && !ress){
						ress = true;
						ressurectionTime = System.currentTimeMillis()+ressurectionIntervall;
					}
			} catch (InterruptedException e) {
				System.out.println(e.getMessage() + " in Player.run()");
			}
			if( !this.getGameCharacter().isAlive() && ress && (System.currentTimeMillis() >ressurectionTime)) {
				this.getGameCharacter().changeHealth(100);
				getGameCharacter().setSuperAnimationType(Strings.Still);
				ress = false;
			}
			//runs the actions that's in the running queue
			//changes the animation type that should be according to prio of the running queue
			runActions();
			try{
				gc = engine.nearbyClients(this.getGameCharacter().getPoint(), 500); // korrekt radius?
			}catch(NullPointerException e) {
				System.out.println(e.getMessage() + " in Player.run() trying engine.nearbyClients");
				e.printStackTrace();
			}
			setChanged();
			notifyObservers(gc);
		}
	}
	
	public Object clone() {
		return new Player(this);
	}
}
