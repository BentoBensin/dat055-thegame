package gamecharacter;

import java.util.ArrayList;
import java.util.HashMap;

import command.Commands;

/**
 * @file Warrior.java
 * @version 0.1
 * @author Teddie
 */
public class Warrior extends GameCharacter{
	
	
	public Warrior(){
		super(new Weapon(),1, 100, 100); //speed, width and height.
		initImages();
		newMovePattern();
		
	}
	
	private void initImages(){
		HashMap<String,HashMap<String,ArrayList<String>>> anim = new HashMap<String,HashMap<String,ArrayList<String>>>(); 
		anim.put("walk", new HashMap<String,ArrayList<String>>());
    	anim.get("walk").put("north", new ArrayList<String>());
    	anim.get("walk").put("south", new ArrayList<String>());
    	anim.get("walk").put("east", new ArrayList<String>());
    	anim.get("walk").put("west", new ArrayList<String>());
    	
    	anim.get("walk").get("north").add("images/warrior/walk/north/nn00.gif");
    	anim.get("walk").get("north").add("images/warrior/walk/north/nn01.gif");
    	anim.get("walk").get("north").add("images/warrior/walk/north/nn02.gif");
    	anim.get("walk").get("north").add("images/warrior/walk/north/nn03.gif");
    	anim.get("walk").get("north").add("images/warrior/walk/north/nn04.gif");
    	anim.get("walk").get("north").add("images/warrior/walk/north/nn05.gif");
    	anim.get("walk").get("north").add("images/warrior/walk/north/nn06.gif");
    	anim.get("walk").get("north").add("images/warrior/walk/north/nn07.gif");
    	
    	anim.get("walk").get("south").add("images/warrior/walk/south/ss00.gif");
    	anim.get("walk").get("south").add("images/warrior/walk/south/ss01.gif");
    	anim.get("walk").get("south").add("images/warrior/walk/south/ss02.gif");
    	anim.get("walk").get("south").add("images/warrior/walk/south/ss03.gif");
    	anim.get("walk").get("south").add("images/warrior/walk/south/ss04.gif");
    	anim.get("walk").get("south").add("images/warrior/walk/south/ss05.gif");
    	anim.get("walk").get("south").add("images/warrior/walk/south/ss06.gif");
    	anim.get("walk").get("south").add("images/warrior/walk/south/ss07.gif");
    	
    	anim.get("walk").get("east").add("images/warrior/walk/east/ee00.gif");
    	anim.get("walk").get("east").add("images/warrior/walk/east/ee01.gif");
    	anim.get("walk").get("east").add("images/warrior/walk/east/ee02.gif");
    	anim.get("walk").get("east").add("images/warrior/walk/east/ee03.gif");
    	anim.get("walk").get("east").add("images/warrior/walk/east/ee04.gif");
    	anim.get("walk").get("east").add("images/warrior/walk/east/ee05.gif");
    	anim.get("walk").get("east").add("images/warrior/walk/east/ee06.gif");
    	anim.get("walk").get("east").add("images/warrior/walk/east/ee07.gif");
    	
    	anim.get("walk").get("west").add("images/warrior/walk/west/ww00.gif");
    	anim.get("walk").get("west").add("images/warrior/walk/west/ww01.gif");
    	anim.get("walk").get("west").add("images/warrior/walk/west/ww02.gif");
    	anim.get("walk").get("west").add("images/warrior/walk/west/ww03.gif");
    	anim.get("walk").get("west").add("images/warrior/walk/west/ww04.gif");
    	anim.get("walk").get("west").add("images/warrior/walk/west/ww05.gif");
    	anim.get("walk").get("west").add("images/warrior/walk/west/ww06.gif");
    	anim.get("walk").get("west").add("images/warrior/walk/west/ww07.gif");
    	
    	anim.put("hit", new HashMap<String,ArrayList<String>>());
    	anim.get("hit").put("north", new ArrayList<String>());
    	anim.get("hit").put("south", new ArrayList<String>());
    	anim.get("hit").put("east", new ArrayList<String>());
    	anim.get("hit").put("west", new ArrayList<String>());
    	
    	anim.get("hit").get("north").add("images/warrior/hit/north/nn00.bmp");
    	anim.get("hit").get("north").add("images/warrior/hit/north/nn01.bmp");
    	anim.get("hit").get("north").add("images/warrior/hit/north/nn02.bmp");
    	anim.get("hit").get("north").add("images/warrior/hit/north/nn03.bmp");
    	anim.get("hit").get("north").add("images/warrior/hit/north/nn04.bmp");
    	anim.get("hit").get("north").add("images/warrior/hit/north/nn05.bmp");
    	anim.get("hit").get("north").add("images/warrior/hit/north/nn06.bmp");
    	anim.get("hit").get("north").add("images/warrior/hit/north/nn07.bmp");
    	
    	anim.get("hit").get("south").add("images/warrior/hit/south/ss00.bmp");
    	anim.get("hit").get("south").add("images/warrior/hit/south/ss01.bmp");
    	anim.get("hit").get("south").add("images/warrior/hit/south/ss02.bmp");
    	anim.get("hit").get("south").add("images/warrior/hit/south/ss03.bmp");
    	anim.get("hit").get("south").add("images/warrior/hit/south/ss04.bmp");
    	anim.get("hit").get("south").add("images/warrior/hit/south/ss05.bmp");
    	anim.get("hit").get("south").add("images/warrior/hit/south/ss06.bmp");
    	anim.get("hit").get("south").add("images/warrior/hit/south/ss07.bmp");
    	
    	anim.get("hit").get("east").add("images/warrior/hit/east/ee00.bmp");
    	anim.get("hit").get("east").add("images/warrior/hit/east/ee01.bmp");
    	anim.get("hit").get("east").add("images/warrior/hit/east/ee02.bmp");
    	anim.get("hit").get("east").add("images/warrior/hit/east/ee03.bmp");
    	anim.get("hit").get("east").add("images/warrior/hit/east/ee04.bmp");
    	anim.get("hit").get("east").add("images/warrior/hit/east/ee05.bmp");
    	anim.get("hit").get("east").add("images/warrior/hit/east/ee06.bmp");
    	anim.get("hit").get("east").add("images/warrior/hit/east/ee07.bmp");
    	
    	anim.get("hit").get("west").add("images/warrior/hit/west/ww00.bmp");
    	anim.get("hit").get("west").add("images/warrior/hit/west/ww01.bmp");
    	anim.get("hit").get("west").add("images/warrior/hit/west/ww02.bmp");
    	anim.get("hit").get("west").add("images/warrior/hit/west/ww03.bmp");
    	anim.get("hit").get("west").add("images/warrior/hit/west/ww04.bmp");
    	anim.get("hit").get("west").add("images/warrior/hit/west/ww05.bmp");
    	anim.get("hit").get("west").add("images/warrior/hit/west/ww06.bmp");
    	anim.get("hit").get("west").add("images/warrior/hit/west/ww07.bmp");
    	
    	anim.put("attack", new HashMap<String,ArrayList<String>>());
    	anim.get("attack").put("north", new ArrayList<String>());
    	anim.get("attack").put("south", new ArrayList<String>());
    	anim.get("attack").put("east", new ArrayList<String>());
    	anim.get("attack").put("west", new ArrayList<String>());
    	
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn00.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn01.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn02.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn03.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn04.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn05.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn06.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn07.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn08.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn09.gif");
    	anim.get("attack").get("north").add("images/warrior/attack/north/nn10.gif");
    	
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss00.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss01.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss02.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss03.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss04.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss05.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss06.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss07.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss08.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss09.gif");
    	anim.get("attack").get("south").add("images/warrior/attack/south/ss10.gif");
    	
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee00.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee01.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee02.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee03.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee04.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee05.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee06.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee07.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee08.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee09.gif");
    	anim.get("attack").get("east").add("images/warrior/attack/east/ee10.gif");
    	
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww00.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww01.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww02.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww03.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww04.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww05.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww06.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww07.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww08.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww09.gif");
    	anim.get("attack").get("west").add("images/warrior/attack/west/ww10.gif");
    	
    	loadImages(anim);
	}
	
	public void newMovePattern(){
		addPattern(new MovePattern(130, Commands.SOUTH));
		addPattern(new MovePattern(100, Commands.EAST));
		addPattern(new MovePattern(130, Commands.NORTH));
		addPattern(new MovePattern(100, Commands.WEST));
		
	}
}
