package gamecharacter;

import java.util.ArrayList;
import java.util.HashMap;

import command.Commands;

/**
 * @file ShroomsMan.java
 * @version 0.1
 * @author Teddie
 *
 */
public class ShroomsMan extends GameCharacter {
	
	public ShroomsMan(){
		super(new Weapon(), 0.5, 100, 100,100); //speed, width and height.
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
    	
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn00.bmp");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn01.bmp");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn02.bmp");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn03.bmp");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn04.bmp");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn05.bmp");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn06.bmp");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn07.bmp");
    	
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss00.bmp");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss01.bmp");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss02.bmp");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss03.bmp");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss04.bmp");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss05.bmp");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss06.bmp");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss07.bmp");
    	
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee00.bmp");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee01.bmp");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee02.bmp");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee03.bmp");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee04.bmp");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee05.bmp");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee06.bmp");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee07.bmp");
    	
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww00.bmp");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww01.bmp");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww02.bmp");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww03.bmp");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww04.bmp");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww05.bmp");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww06.bmp");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww07.bmp");
    	
    	anim.put("attack", new HashMap<String,ArrayList<String>>());
    	anim.get("attack").put("north", new ArrayList<String>());
    	anim.get("attack").put("south", new ArrayList<String>());
    	anim.get("attack").put("east", new ArrayList<String>());
    	anim.get("attack").put("west", new ArrayList<String>());
    	
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn00.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn01.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn02.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn03.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn04.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn05.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn06.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn07.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn08.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn09.bmp");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn10.bmp");
    	
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss00.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss01.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss02.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss03.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss04.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss05.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss06.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss07.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss08.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss09.bmp");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss10.bmp");
    	
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee00.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee01.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee02.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee03.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee04.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee05.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee06.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee07.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee08.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee09.bmp");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee10.bmp");
    	
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww00.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww01.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww02.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww03.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww04.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww05.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww06.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww07.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww08.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww09.bmp");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww10.bmp");
    	
    	loadImages(anim);
	}
	
	public void newMovePattern(){
		addPattern(new MovePattern(130, Commands.SOUTH));
		addPattern(new MovePattern(100, Commands.EAST));
		addPattern(new MovePattern(130, Commands.NORTH));
		addPattern(new MovePattern(100, Commands.WEST));
		
	}
}
