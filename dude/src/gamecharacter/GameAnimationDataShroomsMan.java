/**
 * 
 */
package gamecharacter;

import java.util.ArrayList;
import java.util.HashMap;

import main.Strings;

/**
 * @author josjoh
 *
 */
public class GameAnimationDataShroomsMan extends GameAnimationData {
	public void initImages(){
		HashMap<String,HashMap<String,ArrayList<String>>> anim = new HashMap<String,HashMap<String,ArrayList<String>>>(); 
		anim.put("walk", new HashMap<String,ArrayList<String>>());
    	anim.get("walk").put("north", new ArrayList<String>());
    	anim.get("walk").put("south", new ArrayList<String>());
    	anim.get("walk").put("east", new ArrayList<String>());
    	anim.get("walk").put("west", new ArrayList<String>());
    	
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn00.gif");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn01.gif");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn02.gif");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn03.gif");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn04.gif");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn05.gif");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn06.gif");
    	anim.get("walk").get("north").add("images/shroomsman/walk/north/nn07.gif");
    	
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss00.gif");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss01.gif");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss02.gif");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss03.gif");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss04.gif");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss05.gif");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss06.gif");
    	anim.get("walk").get("south").add("images/shroomsman/walk/south/ss07.gif");
    	
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee00.gif");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee01.gif");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee02.gif");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee03.gif");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee04.gif");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee05.gif");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee06.gif");
    	anim.get("walk").get("east").add("images/shroomsman/walk/east/ee07.gif");
    	
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww00.gif");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww01.gif");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww02.gif");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww03.gif");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww04.gif");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww05.gif");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww06.gif");
    	anim.get("walk").get("west").add("images/shroomsman/walk/west/ww07.gif");
    	
    	anim.put("attack", new HashMap<String,ArrayList<String>>());
    	anim.get("attack").put("north", new ArrayList<String>());
    	anim.get("attack").put("south", new ArrayList<String>());
    	anim.get("attack").put("east", new ArrayList<String>());
    	anim.get("attack").put("west", new ArrayList<String>());
    	
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn00.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn01.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn02.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn03.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn04.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn05.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn06.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn07.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn08.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn09.gif");
    	anim.get("attack").get("north").add("images/shroomsman/attack/north/nn10.gif");
    	
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss00.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss01.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss02.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss03.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss04.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss05.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss06.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss07.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss08.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss09.gif");
    	anim.get("attack").get("south").add("images/shroomsman/attack/south/ss10.gif");
    	
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee00.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee01.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee02.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee03.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee04.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee05.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee06.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee07.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee08.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee09.gif");
    	anim.get("attack").get("east").add("images/shroomsman/attack/east/ee10.gif");
    	
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww00.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww01.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww02.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww03.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww04.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww05.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww06.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww07.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww08.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww09.gif");
    	anim.get("attack").get("west").add("images/shroomsman/attack/west/ww10.gif");
    	
    	anim.put("die", new HashMap<String,ArrayList<String>>());
    	anim.get("die").put("north", new ArrayList<String>());
    	anim.get("die").put("south", new ArrayList<String>());
    	anim.get("die").put("east", new ArrayList<String>());
    	anim.get("die").put("west", new ArrayList<String>());
    	
    	anim.get("die").get("north").add("images/shroomsman/die/north/nn00.gif");
    	anim.get("die").get("north").add("images/shroomsman/die/north/nn01.gif");
    	anim.get("die").get("north").add("images/shroomsman/die/north/nn02.gif");
    	anim.get("die").get("north").add("images/shroomsman/die/north/nn03.gif");
    	anim.get("die").get("north").add("images/shroomsman/die/north/nn04.gif");
    	anim.get("die").get("north").add("images/shroomsman/die/north/nn05.gif");
    	anim.get("die").get("north").add("images/shroomsman/die/north/nn06.gif");
    	
    	anim.get("die").get("south").add("images/shroomsman/die/south/ss00.gif");
    	anim.get("die").get("south").add("images/shroomsman/die/south/ss01.gif");
    	anim.get("die").get("south").add("images/shroomsman/die/south/ss02.gif");
    	anim.get("die").get("south").add("images/shroomsman/die/south/ss03.gif");
    	anim.get("die").get("south").add("images/shroomsman/die/south/ss04.gif");
    	anim.get("die").get("south").add("images/shroomsman/die/south/ss05.gif");
    	anim.get("die").get("south").add("images/shroomsman/die/south/ss06.gif");
    	
    	anim.get("die").get("east").add("images/shroomsman/die/east/ee00.gif");
    	anim.get("die").get("east").add("images/shroomsman/die/east/ee01.gif");
    	anim.get("die").get("east").add("images/shroomsman/die/east/ee02.gif");
    	anim.get("die").get("east").add("images/shroomsman/die/east/ee03.gif");
    	anim.get("die").get("east").add("images/shroomsman/die/east/ee04.gif");
    	anim.get("die").get("east").add("images/shroomsman/die/east/ee05.gif");
    	anim.get("die").get("east").add("images/shroomsman/die/east/ee06.gif");
    	
    	anim.get("die").get("west").add("images/shroomsman/die/west/ww00.gif");
    	anim.get("die").get("west").add("images/shroomsman/die/west/ww01.gif");
    	anim.get("die").get("west").add("images/shroomsman/die/west/ww02.gif");
    	anim.get("die").get("west").add("images/shroomsman/die/west/ww03.gif");
    	anim.get("die").get("west").add("images/shroomsman/die/west/ww04.gif");
    	anim.get("die").get("west").add("images/shroomsman/die/west/ww05.gif");
    	anim.get("die").get("west").add("images/shroomsman/die/west/ww06.gif");
    	
    	anim.put("hit", new HashMap<String,ArrayList<String>>());
    	anim.get("hit").put("north", new ArrayList<String>());
    	anim.get("hit").put("south", new ArrayList<String>());
    	anim.get("hit").put("east", new ArrayList<String>());
    	anim.get("hit").put("west", new ArrayList<String>());
    	
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn00.gif");
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn01.gif");
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn02.gif");
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn03.gif");
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn04.gif");
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn05.gif");
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn06.gif");
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn07.gif");
    	anim.get("hit").get("north").add("images/shroomsman/hit/north/nn08.gif");
    	
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss00.gif");
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss01.gif");
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss02.gif");
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss03.gif");
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss04.gif");
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss05.gif");
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss06.gif");
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss07.gif");
    	anim.get("hit").get("south").add("images/shroomsman/hit/south/ss08.gif");
    	
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee00.gif");
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee01.gif");
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee02.gif");
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee03.gif");
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee04.gif");
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee05.gif");
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee06.gif");
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee07.gif");
    	anim.get("hit").get("east").add("images/shroomsman/hit/east/ee08.gif");
    	
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww00.gif");
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww01.gif");
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww02.gif");
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww03.gif");
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww04.gif");
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww05.gif");
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww06.gif");
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww07.gif");
    	anim.get("hit").get("west").add("images/shroomsman/hit/west/ww08.gif");
    	
    	anim.put("still", new HashMap<String,ArrayList<String>>());
    	anim.get("still").put("north", new ArrayList<String>());
    	anim.get("still").put("south", new ArrayList<String>());
    	anim.get("still").put("east", new ArrayList<String>());
    	anim.get("still").put("west", new ArrayList<String>());
    	
    	anim.get("still").get("north").add("images/shroomsman/still/north/nn00.gif");
    	anim.get("still").get("south").add("images/shroomsman/still/south/ss00.gif");
    	anim.get("still").get("east").add("images/shroomsman/still/east/ee00.gif");
    	anim.get("still").get("west").add("images/shroomsman/still/west/ww00.gif");
    	try {
    		loadImages(anim);
    	} catch (NullPointerException e){
    		if ( Strings.Debug ) System.out.println("Error: "+e);
    	}
}
