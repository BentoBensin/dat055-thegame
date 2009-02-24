package main;

import game.Engine;
import game.IDGen;
import game.Player;
import gamecharacter.Warrior;
import gui.Gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @file Main.java
 * @version 0.2
 * @author Raul Bache
 */
public class Main
{
    /**
     * Constructor for objects of class Main
     */
	private Engine engine;
	
    public Main()
    {
    	init();
    }
    public static void main(String [] args )
    {
    	/*Engine e;
    	Gui gui;
    	e = new Engine();*/
    	new Gui();
    	/*Player p = new Player( IDGen.generateID() , new Point( 500,250 ), 100, "player",new Warrior(), e);
    	gui.setPlayer(p);
    	e.addClient( p);
    	p.startThread();
    	
    	e.createEnemies(3);*/
    	
    }
    
    public void init() {
    	engine = new Engine();
        engine.createEnemies(2);
        
    }
 
}