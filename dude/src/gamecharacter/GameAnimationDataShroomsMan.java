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
	
	public GameAnimationDataShroomsMan() {
		super();
		initImages();
	}
	
	public void initImages(){
		HashMap<String,HashMap<String,ArrayList<String>>> anim = new HashMap<String,HashMap<String,ArrayList<String>>>(); 
		anim.put(Strings.Move, new HashMap<String,ArrayList<String>>());
    	anim.get(Strings.Move).put(Strings.North, new ArrayList<String>());
    	anim.get(Strings.Move).put(Strings.South, new ArrayList<String>());
    	anim.get(Strings.Move).put(Strings.East, new ArrayList<String>());
    	anim.get(Strings.Move).put(Strings.West, new ArrayList<String>());
    	
    	anim.get(Strings.Move).get(Strings.North).add("images/shroomsman/walk/north/nn00.gif");
    	anim.get(Strings.Move).get(Strings.North).add("images/shroomsman/walk/north/nn01.gif");
    	anim.get(Strings.Move).get(Strings.North).add("images/shroomsman/walk/north/nn02.gif");
    	anim.get(Strings.Move).get(Strings.North).add("images/shroomsman/walk/north/nn03.gif");
    	anim.get(Strings.Move).get(Strings.North).add("images/shroomsman/walk/north/nn04.gif");
    	anim.get(Strings.Move).get(Strings.North).add("images/shroomsman/walk/north/nn05.gif");
    	anim.get(Strings.Move).get(Strings.North).add("images/shroomsman/walk/north/nn06.gif");
    	anim.get(Strings.Move).get(Strings.North).add("images/shroomsman/walk/north/nn07.gif");
    	
    	anim.get(Strings.Move).get(Strings.South).add("images/shroomsman/walk/south/ss00.gif");
    	anim.get(Strings.Move).get(Strings.South).add("images/shroomsman/walk/south/ss01.gif");
    	anim.get(Strings.Move).get(Strings.South).add("images/shroomsman/walk/south/ss02.gif");
    	anim.get(Strings.Move).get(Strings.South).add("images/shroomsman/walk/south/ss03.gif");
    	anim.get(Strings.Move).get(Strings.South).add("images/shroomsman/walk/south/ss04.gif");
    	anim.get(Strings.Move).get(Strings.South).add("images/shroomsman/walk/south/ss05.gif");
    	anim.get(Strings.Move).get(Strings.South).add("images/shroomsman/walk/south/ss06.gif");
    	anim.get(Strings.Move).get(Strings.South).add("images/shroomsman/walk/south/ss07.gif");
    	
    	anim.get(Strings.Move).get(Strings.East).add("images/shroomsman/walk/east/ee00.gif");
    	anim.get(Strings.Move).get(Strings.East).add("images/shroomsman/walk/east/ee01.gif");
    	anim.get(Strings.Move).get(Strings.East).add("images/shroomsman/walk/east/ee02.gif");
    	anim.get(Strings.Move).get(Strings.East).add("images/shroomsman/walk/east/ee03.gif");
    	anim.get(Strings.Move).get(Strings.East).add("images/shroomsman/walk/east/ee04.gif");
    	anim.get(Strings.Move).get(Strings.East).add("images/shroomsman/walk/east/ee05.gif");
    	anim.get(Strings.Move).get(Strings.East).add("images/shroomsman/walk/east/ee06.gif");
    	anim.get(Strings.Move).get(Strings.East).add("images/shroomsman/walk/east/ee07.gif");
    	
    	anim.get(Strings.Move).get(Strings.West).add("images/shroomsman/walk/west/ww00.gif");
    	anim.get(Strings.Move).get(Strings.West).add("images/shroomsman/walk/west/ww01.gif");
    	anim.get(Strings.Move).get(Strings.West).add("images/shroomsman/walk/west/ww02.gif");
    	anim.get(Strings.Move).get(Strings.West).add("images/shroomsman/walk/west/ww03.gif");
    	anim.get(Strings.Move).get(Strings.West).add("images/shroomsman/walk/west/ww04.gif");
    	anim.get(Strings.Move).get(Strings.West).add("images/shroomsman/walk/west/ww05.gif");
    	anim.get(Strings.Move).get(Strings.West).add("images/shroomsman/walk/west/ww06.gif");
    	anim.get(Strings.Move).get(Strings.West).add("images/shroomsman/walk/west/ww07.gif");
    	
    	anim.put(Strings.Attack, new HashMap<String,ArrayList<String>>());
    	anim.get(Strings.Attack).put(Strings.North, new ArrayList<String>());
    	anim.get(Strings.Attack).put(Strings.South, new ArrayList<String>());
    	anim.get(Strings.Attack).put(Strings.East, new ArrayList<String>());
    	anim.get(Strings.Attack).put(Strings.West, new ArrayList<String>());
    	
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn00.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn01.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn02.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn03.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn04.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn05.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn06.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn07.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn08.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn09.gif");
    	anim.get(Strings.Attack).get(Strings.North).add("images/shroomsman/attack/north/nn10.gif");
    	
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss00.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss01.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss02.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss03.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss04.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss05.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss06.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss07.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss08.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss09.gif");
    	anim.get(Strings.Attack).get(Strings.South).add("images/shroomsman/attack/south/ss10.gif");
    	
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee00.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee01.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee02.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee03.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee04.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee05.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee06.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee07.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee08.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee09.gif");
    	anim.get(Strings.Attack).get(Strings.East).add("images/shroomsman/attack/east/ee10.gif");
    	
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww00.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww01.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww02.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww03.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww04.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww05.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww06.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww07.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww08.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww09.gif");
    	anim.get(Strings.Attack).get(Strings.West).add("images/shroomsman/attack/west/ww10.gif");
    	
    	anim.put(Strings.Die, new HashMap<String,ArrayList<String>>());
    	anim.get(Strings.Die).put(Strings.North, new ArrayList<String>());
    	anim.get(Strings.Die).put(Strings.South, new ArrayList<String>());
    	anim.get(Strings.Die).put(Strings.East, new ArrayList<String>());
    	anim.get(Strings.Die).put(Strings.West, new ArrayList<String>());
    	
    	anim.get(Strings.Die).get(Strings.North).add("images/shroomsman/die/north/nn00.gif");
    	anim.get(Strings.Die).get(Strings.North).add("images/shroomsman/die/north/nn01.gif");
    	anim.get(Strings.Die).get(Strings.North).add("images/shroomsman/die/north/nn02.gif");
    	anim.get(Strings.Die).get(Strings.North).add("images/shroomsman/die/north/nn03.gif");
    	anim.get(Strings.Die).get(Strings.North).add("images/shroomsman/die/north/nn04.gif");
    	anim.get(Strings.Die).get(Strings.North).add("images/shroomsman/die/north/nn05.gif");
    	anim.get(Strings.Die).get(Strings.North).add("images/shroomsman/die/north/nn06.gif");
    	
    	anim.get(Strings.Die).get(Strings.South).add("images/shroomsman/die/south/ss00.gif");
    	anim.get(Strings.Die).get(Strings.South).add("images/shroomsman/die/south/ss01.gif");
    	anim.get(Strings.Die).get(Strings.South).add("images/shroomsman/die/south/ss02.gif");
    	anim.get(Strings.Die).get(Strings.South).add("images/shroomsman/die/south/ss03.gif");
    	anim.get(Strings.Die).get(Strings.South).add("images/shroomsman/die/south/ss04.gif");
    	anim.get(Strings.Die).get(Strings.South).add("images/shroomsman/die/south/ss05.gif");
    	anim.get(Strings.Die).get(Strings.South).add("images/shroomsman/die/south/ss06.gif");
    	
    	anim.get(Strings.Die).get(Strings.East).add("images/shroomsman/die/east/ee00.gif");
    	anim.get(Strings.Die).get(Strings.East).add("images/shroomsman/die/east/ee01.gif");
    	anim.get(Strings.Die).get(Strings.East).add("images/shroomsman/die/east/ee02.gif");
    	anim.get(Strings.Die).get(Strings.East).add("images/shroomsman/die/east/ee03.gif");
    	anim.get(Strings.Die).get(Strings.East).add("images/shroomsman/die/east/ee04.gif");
    	anim.get(Strings.Die).get(Strings.East).add("images/shroomsman/die/east/ee05.gif");
    	anim.get(Strings.Die).get(Strings.East).add("images/shroomsman/die/east/ee06.gif");
    	
    	anim.get(Strings.Die).get(Strings.West).add("images/shroomsman/die/west/ww00.gif");
    	anim.get(Strings.Die).get(Strings.West).add("images/shroomsman/die/west/ww01.gif");
    	anim.get(Strings.Die).get(Strings.West).add("images/shroomsman/die/west/ww02.gif");
    	anim.get(Strings.Die).get(Strings.West).add("images/shroomsman/die/west/ww03.gif");
    	anim.get(Strings.Die).get(Strings.West).add("images/shroomsman/die/west/ww04.gif");
    	anim.get(Strings.Die).get(Strings.West).add("images/shroomsman/die/west/ww05.gif");
    	anim.get(Strings.Die).get(Strings.West).add("images/shroomsman/die/west/ww06.gif");
    	
    	anim.put(Strings.Hit, new HashMap<String,ArrayList<String>>());
    	anim.get(Strings.Hit).put(Strings.North, new ArrayList<String>());
    	anim.get(Strings.Hit).put(Strings.South, new ArrayList<String>());
    	anim.get(Strings.Hit).put(Strings.East, new ArrayList<String>());
    	anim.get(Strings.Hit).put(Strings.West, new ArrayList<String>());
    	
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn00.gif");
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn01.gif");
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn02.gif");
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn03.gif");
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn04.gif");
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn05.gif");
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn06.gif");
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn07.gif");
    	anim.get(Strings.Hit).get(Strings.North).add("images/shroomsman/hit/north/nn08.gif");
    	
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss00.gif");
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss01.gif");
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss02.gif");
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss03.gif");
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss04.gif");
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss05.gif");
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss06.gif");
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss07.gif");
    	anim.get(Strings.Hit).get(Strings.South).add("images/shroomsman/hit/south/ss08.gif");
    	
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee00.gif");
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee01.gif");
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee02.gif");
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee03.gif");
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee04.gif");
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee05.gif");
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee06.gif");
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee07.gif");
    	anim.get(Strings.Hit).get(Strings.East).add("images/shroomsman/hit/east/ee08.gif");
    	
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww00.gif");
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww01.gif");
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww02.gif");
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww03.gif");
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww04.gif");
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww05.gif");
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww06.gif");
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww07.gif");
    	anim.get(Strings.Hit).get(Strings.West).add("images/shroomsman/hit/west/ww08.gif");
    	
    	anim.put(Strings.Still, new HashMap<String,ArrayList<String>>());
    	anim.get(Strings.Still).put(Strings.North, new ArrayList<String>());
    	anim.get(Strings.Still).put(Strings.South, new ArrayList<String>());
    	anim.get(Strings.Still).put(Strings.East, new ArrayList<String>());
    	anim.get(Strings.Still).put(Strings.West, new ArrayList<String>());
    	
    	anim.get(Strings.Still).get(Strings.North).add("images/shroomsman/still/north/nn00.gif");
    	anim.get(Strings.Still).get(Strings.South).add("images/shroomsman/still/south/ss00.gif");
    	anim.get(Strings.Still).get(Strings.East).add("images/shroomsman/still/east/ee00.gif");
    	anim.get(Strings.Still).get(Strings.West).add("images/shroomsman/still/west/ww00.gif");
    	try {
    		loadImages(anim);
    	} catch (NullPointerException e){
    		if ( Strings.Debug ) System.out.println("Error: "+e);
    	}
	}
}
