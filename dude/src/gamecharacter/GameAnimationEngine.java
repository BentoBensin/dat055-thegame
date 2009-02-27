package gamecharacter;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class GameAnimationEngine
{
	private static GameAnimationEngine instance = null;
	private HashMap<String,GameAnimationData> data;
	private GameAnimationEngine()
	{
		data = new HashMap<String,GameAnimationData>();
		data.put("ShroomsMan",new GameAnimationDataShroomsMan());
		data.put("Warrior",new GameAnimationDataWarrior());
	}
	public static GameAnimationEngine getInstance(){
		if (instance == null)
			instance = new GameAnimationEngine();
		return instance;
	}
	public BufferedImage getImage(GameCharacter gc)
	{
		String direction=new String();
		String action=new String();
		String skin=new String();
		
		BufferedImage retImg = null;
		int index = 0;
		if (gc != null
			&& (direction = gc.getDirection()) != null
			&& (action = gc.getAction()) != null
			&& (index = gc.getAnimationIndex()) > -1
			&& (skin = gc.getSkin()) != null
		)
			retImg = data.get(skin).getNextImage(action,direction,index);
		if (retImg != null) index = data.get(skin).getNextIndex(action,direction,index);
		else index = 0;
		gc.setAnimationIndex(index);
		return retImg;
	}

}
