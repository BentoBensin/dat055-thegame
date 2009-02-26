package gamecharacter;
public class GameAnimationEngine()
{
	private GameAnimationEngine instance = null;
	private HashMap<String,GameAnimationData> data;
	private GameAnimationEngine()
	{
		data = new HashMap<String,GameAnimationData>();
		data.put("ShroomsMan",new GameAnimationData("ShroomsMan"));
		data.put("Warrior",new GameAnimationData("Warrior"));
	}
	public BufferedImage getImage(GameCharacter gc)
	{
		if (instance == null)
			instance = new GameAnimationEngine();
		String direction,action,skin;
		int index;
		if (gc != null
			&& (direction = gc.getDirection()) != null
			&& (action = gc.getAction()) != null
			&& (index = gc.getAnimationIndex()) != null
			&& (skin = gc.getSkin()) != null
		)
			return data.get(skin).getNextImage(action,direction,index);
		return null;
	}

}
