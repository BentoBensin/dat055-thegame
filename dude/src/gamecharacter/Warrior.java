package gamecharacter;

import items.TreeSword;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import main.strings;

/**
 * @file Warrior.java
 * @version 0.1
 * @author Teddie
 */
public class Warrior extends GameCharacter{
	
	
	public Warrior(String name, Point point, boolean isPlayer){
		super(new TreeSword(),1.0, 100, 100, 100,name, point, isPlayer); //speed, width and height.
		newMovePattern();
		
	}
	
	public void newMovePattern(){
		addPattern(new MovePattern(130, strings.South));
		addPattern(new MovePattern(100, strings.East));
		addPattern(new MovePattern(130, strings.North));
		addPattern(new MovePattern(100, strings.West));
	}
}
