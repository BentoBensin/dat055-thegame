//gui.java

package gui;

import game.IDGen;
import game.Player;
import gamecharacter.GameCharacter;
import gamecharacter.Warrior;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import main.Envelope;
import main.Strings;
import map.Maps;

/**
 * The GUI class controls all the interaction between the user and the program.
 * It displays the visual representation of our game that is:
 * The player itself, monsters and the environment.
 * It also lets the player control a gamecharacter via the keyboard
 * @file Gui.java
 * @version 0.6
 * @author Robban
 */
public class Gui implements Observer, ActionListener {
	private JFrame frame;
	private JPanel menu;
	private JLayeredPane guiPane;
	private Player player;
	private HashMap<GameCharacter, TranspContainer> currentList;
	private LinkedList<GameCharacter> removeList;
	private GraphicsEnvironment ge;
	private GraphicsDevice gd;
	private DisplayMode dmode;
	private JWindow win;
	private String lang;
	private Localization cL; //currentLanguage
	private Runner r;
	private GameCharacter gc;
	//Till kartor
	private Point oldspot;
	private Maps kartor;
	private TranspContainer backgroundPanel;

	/**
	 * Constructor for class Gui creates the frame and all the
	 * graphical components
	 */
	public Gui() {
		r = new Runner();
		// init maps
		kartor = Maps.getInstance();
		kartor.loadMap("map.txt");
		oldspot = new Point(0,0);
		// Current list of clients visible to player
		currentList = new HashMap<GameCharacter, TranspContainer>();
		removeList = new LinkedList<GameCharacter>();
		
		//thisDirection = new DirectionParser();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = ge.getDefaultScreenDevice();
		dmode = new DisplayMode(Strings.WindowSizeX,Strings.WindowSizeY, 16, DisplayMode.REFRESH_RATE_UNKNOWN); //sista arg DisplayMode.REFRESH_RATE_UNKNOWN
		    
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win = new JWindow(frame,gd.getDefaultConfiguration());
		cL = new Localization();
		//set default language
		lang=Strings.Swedish;
		lang = cL.get(Strings.FullScreen);
		String[] graphicModes = {cL.get(Strings.FullScreen), cL.get(Strings.Window)};
        int x = JOptionPane.showOptionDialog(null,cL.get(Strings.ChooseAlternative), cL.get(Strings.GraphicMode), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,graphicModes,graphicModes[1]);
        if(x==0) createFullGUI();
        else createGUI();
		
	}

	/**
	 * createGUI sets the layout of the frame to BorderLayout 
	 * and adds a JLayeredPane to the frame
	 * adds a guiPanel that contains the ingame menu
	 * we set the guiPanel to a certian width, height and background.
	 * This is where we put our graphical objects
	 * guiPanel has 5 layers to start with.
	 */
	private void createGUI() {
		frame.setTitle(cL.get(Strings.GameTitle));
		frame.setLayout(new BorderLayout());
		guiPane = new JLayeredPane();

		backgroundPanel = new TranspContainer(800,600);
		backgroundPanel.setOpaque(true);
		guiPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
		
		backgroundPanel.setSize(Strings.WindowSizeX,Strings.WindowSizeY);
		backgroundPanel.initContainer();
		
		menu = createIngameMenu();
		guiPane.add( menu, JLayeredPane.POPUP_LAYER );
		// Our pane can't be transparent
		guiPane.setOpaque(true);

		frame.getContentPane().add(guiPane, BorderLayout.CENTER);
		frame.addKeyListener(kl);
		frame.setPreferredSize(new Dimension(Strings.WindowSizeX,Strings.WindowSizeY));
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

	}
	/**
	 * Creates a fullscreen gui instead of a window
	 * look at createGUI for comments.
	 */
	private void createFullGUI()
	{
		frame.setTitle(cL.get(Strings.GameTitle));
		frame.validate();
		frame.setVisible(true);
		guiPane = new JLayeredPane();
		guiPane.setOpaque(true);
		win.getContentPane().add(guiPane, BorderLayout.CENTER);
			
		backgroundPanel = new TranspContainer(Strings.FullScreenSizeX,Strings.FullScreenSizeY);
		backgroundPanel.setOpaque(true);
		guiPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
		
		backgroundPanel.setSize(Strings.WindowSizeX,Strings.WindowSizeY);
		backgroundPanel.initContainer();
			
		menu = createIngameMenu();
		guiPane.add( menu, JLayeredPane.POPUP_LAYER );
		win.addKeyListener(kl);
		win.validate();
		win.setFocusable(true);
		gd.setFullScreenWindow(win);
		gd.setDisplayMode(dmode);
		win.requestFocus();
	}
	/**
	 * Creates the startmenu
	 * @return a panel that contains our menu
	 */
	public JPanel createIngameMenu(){
		JPanel menu = new JPanel();
		menu.setLayout( new FlowLayout());
		//menu.setBackground( new Color(Strings.WindowBackgroundRed,Strings.WindowBackgroundGreen,Strings.WindowBackgroundBlue));
		for( String function : Strings.InGameMenuFunctions) {
			if (cL.get(function)!=null){
				JButton button = new JButton(cL.get(function));
				button.setActionCommand(function);
				button.addActionListener(this);
				menu.add(button);
			}
		}
		menu.setSize(120,160);
		menu.setLocation( ((Strings.WindowSizeX-menu.getWidth())/2) ,((Strings.WindowSizeY-menu.getHeight())/2));
		return menu;
	}
	/**
	 * KeyListener is a listener that we create with the helpclass KeyAdapter 
	 * that contains an inner function that get keycodes and executes a command
	 * depending on what key the player pressed
	 */
	private KeyListener kl = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if( gc != null && !gc.paused){ 
				switch (e.getKeyCode())
				{
				case Strings.KeyMoveNorth :
					gc.setDirection(Strings.North);
					gc.addAction(Strings.Move);
					break;
				case Strings.KeyMoveSouth :
					gc.setDirection(Strings.South);
					gc.addAction(Strings.Move);
					break;
				case Strings.KeyMoveWest :
					gc.setDirection(Strings.West);
					gc.addAction(Strings.Move);
					break;
				case Strings.KeyMoveEast :
					gc.setDirection(Strings.East);
					gc.addAction(Strings.Move);
					break;
				case Strings.KeyAttack :
					gc.addAction(Strings.Attack);
					break;
				}
				return;
			}
			if (e.getKeyCode() == Strings.KeyPause) {
				gc.addAction(Strings.Pause);
				if( menu.isVisible() )	menu.setVisible(false);
				else 					menu.setVisible(true);
			}		
		}

		public void keyReleased(KeyEvent e) {
			//gc.addAction(Strings.Still);
		}
	};
	/**
	 * This handels the actions done by keybord
	 */
	public void actionPerformed( ActionEvent e) {
			try {
				this.getClass().getMethod(e.getActionCommand()).invoke(this);
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
	}
	
	/**
	 * Setsup and starts the game
	 */
	public void startGame() {
		menu.setVisible(false);
		String jOptionPane = (String) JOptionPane.showInputDialog(frame,cL.get(Strings.GivePlayerName),cL.get(Strings.PlayerName),JOptionPane.ERROR_MESSAGE);
		if( jOptionPane != null) 
			setPlayer(
					new Player( 
							IDGen.generateID(), 
							new Warrior(jOptionPane, 
									new Point(100,100), 
									true
							) 
					) 
			);
		else menu.setVisible(true);
	}
	/**
	 * Sets an player connected to the GUI ( observer mode)
	 * @param player
	 */
	private void setPlayer(Player player){
		if(player != null){
			player.addObserver(this);
			this.player = player;
			this.gc = player.getGameCharacter();
			currentList.put(player.getGameCharacter(), null);
		}
	}
	/**
	 * Allows the player to set the language
	 *
	 */
	public void settings() {
		menu.setVisible(false);
		int i = 0;
		String[] possibleValues = new String[cL.availableLanguages().length];
		for(String langIterate : cL.availableLanguages())
			possibleValues[i++]=langIterate;
			lang = (String)JOptionPane.showInputDialog(frame,
		            cL.get(Strings.ChooseOne), cL.get(Strings.Input),
		            JOptionPane.INFORMATION_MESSAGE, null,
		            possibleValues, possibleValues[0]);
		 cL.setLanguage(lang);
		 guiPane.remove(guiPane.getIndexOf(menu));
		 menu=createIngameMenu();
		 guiPane.add( menu, JLayeredPane.POPUP_LAYER );
	}
	
	/**
	 * Loads an game.. Not working atm
	 */
	public void loadGame() {
		player.getGameCharacter().addAction(Strings.ButtonLoad);
		System.out.println(cL.get(Strings.ButtonLoad));
	}
	/**
	 * Saves an game... Not working atm
	 */
	public void saveGame() {
		player.getGameCharacter().addAction(Strings.ButtonSave);
		System.out.println(cL.get(Strings.ButtonSave));
	}
	/**
	 * Exits the game, this terminates the game
	 */
	public void exitGame() {
		int choice=(int)JOptionPane.showConfirmDialog(frame,
	            cL.get(Strings.exitTitle), cL.get(Strings.exitQuestion), JOptionPane.YES_NO_OPTION);
		if (choice==0)
			System.exit(0);
		else return;
	}


	/**
	 * update updates our currentList of GameCharacters it goes through each GameCharacter in
	 * our currentlist and compares it with arg list, it adds a new GameCharacter for
	 * each GameCharacter in arglist that does not exist in our currentlist and removes
	 * the GameCharacters in currentlist that does not exist in the new list(arg)
	 * 
	 * @param Observable
	 *            client
	 * @param Object
	 *            arg - the new list
	 * 
	 */

	public void update(Observable o, Object arg) {
		System.out.println("hej hopp nu kör vi update");
		if( arg == null ) return;
		System.out.println("steg2");
		if (arg instanceof Envelope) {
			System.out.println("steg3");
			ArrayList<GameCharacter> remoteList = ((Envelope)arg).getGuiLinkedList();
			System.out.println("Längden på listan = " + remoteList.size());
			for (GameCharacter gameCharacter : remoteList)
				if (!currentList.keySet().contains(gameCharacter)) 
					currentList.put(gameCharacter, null);
			for( GameCharacter gameCharacter : currentList.keySet() )
				if( !remoteList.contains(gameCharacter) )
					removeList.add(gameCharacter);
		}
		SwingUtilities.invokeLater(r);
	}

	/**
	 * Private helper class that handels all the updating and adding to the
	 * screen when the game is started	
	 * @author Mattias
	 *
	 */
	private class Runner implements Runnable {
		public void run() {
				if( !removeList.isEmpty()) 
					while(!removeList.isEmpty())
						removeGraphic(currentList.remove(removeList.removeLast()));
				for (GameCharacter gameCharacter : currentList.keySet()) {
					if (currentList.get(gameCharacter) == null) {
						currentList.put(gameCharacter, addGraphic());
					}
					if( !gameCharacter.paused )
						updateGraphic(gameCharacter);
				}
			}
		/**
		 * removeGraphic removes the container from our main window
		 * 
		 * @param TranspContainer
		 *            rtc - the container we want to remove @
		 */

		private void removeGraphic(TranspContainer rtc) {
			rtc.setSize(0, 0);
			guiPane.remove(guiPane.getIndexOf(rtc));
			rtc = null;
		}
		/**
		 * addGraphic adds an new container 
		 * 
		 * @param TranspContainer
		 * @return TranspContainer - the container we want to remove
		 */

		private TranspContainer addGraphic() {
			TranspContainer tnc = new TranspContainer(100, 100);
			assert (guiPane != null && tnc != null) : "guiPane eller tnc r Null i Gui.update()";
			try {
				guiPane.add(tnc, JLayeredPane.PALETTE_LAYER);
			} catch (NullPointerException e) {
				System.out.println("Nullpointer in Runner.run");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			tnc.setOpaque(false);
			return tnc;
		}
		/**
		 * update screen gets the keyset for the currentlist it uses the set to go
		 * through each client and update its corresponding value (TranspContainer)
		 * with the new animationimage and new (absolute) position
		 * 
		 */
		private void updateGraphic(GameCharacter gameCharacter) {
			if ( gameCharacter != null ){
				BufferedImage bimg = gameCharacter.getImage();
				Point point = (Point)gameCharacter.getPoint().clone();
			// move everybody else
			//TODO if network we need to solve this on another way
				if (!gameCharacter.isPlayer()) 
					point.translate( (350 - gc.getPoint().x), (250 - gc.getPoint().y) );
			// The player want's to be centered in the window, atleast we think so ;)
				else{
					if(!(oldspot.equals(point) )){
				    	BufferedImage bakgr = kartor.getCurrentBackground( point );
						backgroundPanel.setBackImage(bakgr );
						oldspot = (Point)point.clone();
				    	}
					point = new Point(350,250);
				}
				if ( bimg.getHeight() > 100 )
						point.translate( ((100-bimg.getHeight())/2) , ((100-bimg.getHeight())/2) );
				currentList.get(gameCharacter).setImage(bimg);
				currentList.get(gameCharacter).setLocation(point);
				System.out.println(gameCharacter.getName() + " har position " + point + " och i erkliga världen: " + gameCharacter.getPoint());
			}				
		}
		
	}

}

