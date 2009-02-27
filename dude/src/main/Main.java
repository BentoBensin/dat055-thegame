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
    public static void main(String [] args )
    {
    	new Gui();
    }
    
    public void init() {
    	engine = new Engine();
        engine.createEnemies(2);
        
    }
 
}