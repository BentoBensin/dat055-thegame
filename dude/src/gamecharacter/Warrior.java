package gamecharacter;

import items.TreeSword;
import java.awt.Point;
import main.Strings;

/**
 * @file Warrior.java
 * @version 0.1
 * @author Teddie
 */
public class Warrior extends GameCharacter{
	
	static final long serialVersionUID=12331;
	public Warrior(String name, Point point, boolean isPlayer){
		super(new TreeSword(),1.0, 100, 100, 100,name, point, "warrior",isPlayer); //speed, width and height.
		newMovePattern();
		
	}
	/**
	 * Creates new movepatterns
	 */
	public void newMovePattern(){
		addPattern(new MovePattern(130, Strings.South));
		addPattern(new MovePattern(100, Strings.East));
		addPattern(new MovePattern(130, Strings.North));
		addPattern(new MovePattern(100, Strings.West));
	}
}
