package gamecharacter;

import items.TreeSword;

import java.awt.Point;
import main.Strings;

/**
 * @file ShroomsMan.java
 * @version 0.1
 * @author Teddie
 *
 */
public class ShroomsMan extends GameCharacter {
	
	public ShroomsMan(String name, Point point, boolean isPlayer){
		super(new TreeSword(), 0.5, 100, 100,100,name, point, isPlayer); //speed, width and height.
		newMovePattern();
	}

	
	public void newMovePattern(){
		addPattern(new MovePattern(130, Strings.South));
		addPattern(new MovePattern(100, Strings.East));
		addPattern(new MovePattern(130, Strings.North));
		addPattern(new MovePattern(100, Strings.West));
		
	}
}
