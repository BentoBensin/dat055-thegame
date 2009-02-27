package main;

//import java.lang.Enum;
import java.util.HashMap;
import java.awt.event.KeyEvent;
/**
 * Every string in the whole application
 * @file strings.java
 * @version 0.1
 * @author Josef Johansson
 *
 */
public final class Strings {
	public static final int KeyMoveNorth = KeyEvent.VK_W;
	public static final int KeyMoveSouth = KeyEvent.VK_S;
	public static final int KeyMoveWest = KeyEvent.VK_A;
	public static final int KeyMoveEast = KeyEvent.VK_D;
	public static final int KeyAttack 	= KeyEvent.VK_L;
	public static final int KeyPause 	= KeyEvent.VK_ESCAPE;
	public static final String North = "north";
	public static final String West = "west";
	public static final String South = "south";
	public static final String East = "east";
	public static final String Attack = "attack";
	public static final String Move = "walk";
	public static final String Still = "still";
	public static final String Pause = "pause";
	
	public static final boolean Debug = true;
	
	public static final String FullScreen = "Fullscreen";
	public static final String Window	= "Window";
	public static final String GraphicMode = "Graphicmode";
	public static final String ChooseAlternative = "Choose an alternative";
	
	public static final String Swedish = "Svenska";
	public static final String English = "English";
	public static final String Must = "Must";
	public static final String GameTitle = "Game";
	public static final String GivePlayerName = "GivePlayerName";
	public static final String PlayerName = "PlayerName";
	public static final String ChooseOne = "ChooseOne";
	public static final String Input = "Input";	
	public static final String exitTitle = "exitTitle";	
	public static final String exitQuestion = "exitQuestion";	
	
	public static final String ButtonLoad = "Load";
	public static final String ButtonSave = "Save";
	public static final int WindowSizeX = 800;
	public static final int WindowSizeY = 600;
	public static final int FullScreenSizeX = 800;
	public static final int FullScreenSizeY = 600;
	
	public static final int WindowBackgroundRed = 6;
	public static final int WindowBackgroundGreen = 107;
	public static final int WindowBackgroundBlue = 1;
	public static final int FullScreenBackgroundRed = 6;
	public static final int FullScreenBackgroundGreen = 107;
	public static final int FullScreenBackgroundBlue = 1;
	public static final String[] InGameMenuFunctions = {"startgame","settings","loadgame","savegame","exitgame"};
	public static final String LocalizationDefault = English;
	public static final HashMap<String,HashMap<String,String>> Localization = new HashMap<String,HashMap<String,String>>();
	
	public Strings(){
		final HashMap<String,String> LocalizationEnglish = new HashMap<String,String>();
		final HashMap<String,String> LocalizationSwedish = new HashMap<String,String>();
		final HashMap<String,String> LocalizationMust	= new HashMap<String,String>();
		LocalizationEnglish.put(InGameMenuFunctions[0],"Start Game");
		LocalizationEnglish.put(InGameMenuFunctions[1],"Settings");
		LocalizationEnglish.put(InGameMenuFunctions[2],"Load Game");
		LocalizationEnglish.put(InGameMenuFunctions[3],"Save Game");
		LocalizationEnglish.put(InGameMenuFunctions[4],"Exit");
		LocalizationEnglish.put(GivePlayerName,			"Enter a playername");
		LocalizationEnglish.put(PlayerName,				"Playername ");		
		LocalizationEnglish.put(ChooseOne,				"Choose one");
		LocalizationEnglish.put(Input,					"Input");		
		LocalizationEnglish.put(ButtonLoad,				"Load");		
		LocalizationEnglish.put(ButtonSave,				"Save");
		LocalizationEnglish.put(exitTitle,				"Wanna quit, sir?");		
		LocalizationEnglish.put(exitQuestion,			"Want to quit?");
				
		LocalizationSwedish.put(InGameMenuFunctions[0],"Stara spelet");
		LocalizationSwedish.put(InGameMenuFunctions[1],"Inställningar");
		LocalizationSwedish.put(InGameMenuFunctions[2],"Ladda");
		LocalizationSwedish.put(InGameMenuFunctions[3],"Spara");
		LocalizationSwedish.put(InGameMenuFunctions[4],"Avsluta");
		LocalizationSwedish.put(GivePlayerName,			"Ange ett spelarnamn");
		LocalizationSwedish.put(PlayerName,				"Spelarnamn ");		
		LocalizationSwedish.put(ChooseOne,				"Välj ett alternativ ");
		LocalizationSwedish.put(Input,					"Mata in ");		
		LocalizationSwedish.put(ButtonLoad,				"Ladda");		
		LocalizationSwedish.put(ButtonSave,				"Spara");
		LocalizationSwedish.put(exitTitle,				"Musta brustet?");		
		LocalizationSwedish.put(exitQuestion,			"Vill du avsluta?");

		LocalizationMust.put(InGameMenuFunctions[0],"Stara spelet");
		LocalizationMust.put(InGameMenuFunctions[1],"Inställningar");
		LocalizationMust.put(InGameMenuFunctions[2],"Ladda");
		LocalizationMust.put(InGameMenuFunctions[3],"Spara");
		LocalizationMust.put(InGameMenuFunctions[4],"Avsluta");
		LocalizationMust.put(GivePlayerName,			"Ange ett spelarnamn");
		LocalizationMust.put(PlayerName,				"Spelarnamn ");		
		LocalizationMust.put(ChooseOne,				"Välj ett alternativ ");
		LocalizationMust.put(Input,					"Mata in ");		
		LocalizationMust.put(ButtonLoad,				"Ladda");		
		LocalizationMust.put(ButtonSave,				"Spara");
		LocalizationMust.put(exitTitle,				"Musta brustet?");		
		LocalizationMust.put(exitQuestion,			"Vill du avsluta?");
		Localization.put(English, LocalizationEnglish);
		Localization.put(Swedish, LocalizationSwedish);
		Localization.put(Must, LocalizationMust);
	}
	 
}
