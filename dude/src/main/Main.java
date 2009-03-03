package main;

import game.Engine;
import gui.Gui;

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
	 /*
	 *the main method of the game creates a Gui.
	 *@param String [] args - arguments are not used
	 */
    public static void main(String [] args )
    {
    	new Gui();
    }
    /*
	 *init initializes the game
	 *by creating a new Engine and enemies.
	 */
    public void init() {
    	engine = new Engine();
        engine.createEnemies(2);
        
    }
 
}