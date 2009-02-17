package gamecharacter;

import java.util.ArrayList;
import java.util.HashMap;

public class Warrior extends GameCharacter{
	
	
	public Warrior(){
		super(1, 100, 100); //speed, width and height.
		initImages();
		
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
    	loadImages(anim);
	}
}
