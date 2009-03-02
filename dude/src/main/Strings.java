package main;

//import java.lang.Enum;
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
	public static final int KeyPause 	= KeyEvent.VK_P;
	public static final String North = "north";
	public static final String West = "west";
	public static final String South = "south";
	public static final String East = "east";
	public static final String Attack = "attack";
	public static final String Move = "walk";
	public static final String Still = "still";
	public static final String Pause = "pause";
	public static final String Hit	=	"hit";
	public static final String Die	=	"die";
	
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
	public static final String[] InGameMenuFunctions = {"startGame","settings","loadGame","saveGame","exitGame"};

	public Strings(){
	}	 
}
