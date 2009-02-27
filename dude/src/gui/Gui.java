//gui.java

package gui;

import game.IDGen;
import game.Player;
import gamecharacter.GameAnimationEngine;
import gamecharacter.Warrior;
import gamecharacter.GameCharacter;
import main.Envelope;
import java.awt.BorderLayout;
import java.awt.Color;
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
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import main.Strings;

/**
 * Write a description of class Gui
 * 
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
	private HashMap<String,String> cL; //currentLanguage
	private Runner r;
	/**
	 * KeyListener is a listener that we create with the helpclass KeyAdapter 
	 * that contains an inner function that get keycodes and executes a command
	 * depending on what key the player pressed
	 */
	private KeyListener kl = new KeyAdapter() {
		GameCharacter gc = player.getGameCharacter();
		public void keyPressed(KeyEvent e) {
			if( !gc.paused){ 
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
			gc.addAction(Strings.Still);
		}
	};

	/**
	 * Konstruktorn gr framen och alla grafiska objekt, vill att dom skall
	 * finnas nr vi skapar GUIt
	 */
	// Object nedan skall vara player/client
	public Gui() {
		r = new Runner();
		// Current list of clients visible to player
		currentList = new HashMap<GameCharacter, TranspContainer>();
		removeList = new LinkedList<GameCharacter>();
		
		//thisDirection = new DirectionParser();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = ge.getDefaultScreenDevice();
		dmode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN); //sista arg DisplayMode.REFRESH_RATE_UNKNOWN
		    
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win = new JWindow(frame,gd.getDefaultConfiguration());

		//set default language
		lang=Strings.Swedish;
		if ((cL = Strings.Localization.get(lang))==null) //select the chosen lang
			cL = Strings.Localization.get(Strings.LocalizationDefault); //failsafe
		String[] graphicModes = {cL.get(Strings.FullScreen), cL.get(Strings.Window)};
        int x = JOptionPane.showOptionDialog(null, cL.get(Strings.ChooseAlternative), cL.get(Strings.GraphicMode), JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,graphicModes,graphicModes[1]);
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
		JPanel guiPanel = new JPanel();
		guiPanel.setBackground( new Color(Strings.WindowBackgroundRed,Strings.WindowBackgroundGreen,Strings.FullScreenBackgroundBlue));
		guiPane.add(guiPanel, JLayeredPane.DEFAULT_LAYER);
		guiPanel.setSize(Strings.WindowSizeX,Strings.WindowSizeY);
		menu = createIngameMenu();
		guiPane.add( menu, JLayeredPane.POPUP_LAYER );
		// Our pane can't be transparent
		guiPane.setOpaque(true);

		frame.getContentPane().add(guiPane, BorderLayout.CENTER);
		frame.addKeyListener(kl);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setResizable(false);
		// initContainer() mste alltid gras EFTER frame.pack()

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
		JPanel guiPanel = new JPanel();
		guiPanel.setBackground( new Color(Strings.FullScreenBackgroundRed,Strings.FullScreenBackgroundGreen,Strings.FullScreenBackgroundBlue));
		guiPane.add(guiPanel, JLayeredPane.DEFAULT_LAYER);
		guiPanel.setSize(Strings.FullScreenSizeX,Strings.FullScreenSizeY);
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
		menu.setBackground( new Color(Strings.WindowBackgroundRed,Strings.WindowBackgroundGreen,Strings.WindowBackgroundBlue));
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
	
	public void actionPerformed( ActionEvent e) {
			try {
				this.getClass().getMethod(e.getActionCommand()).invoke(this);
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
	}
	
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
	private void setPlayer(Player player){
		if(player != null){
			player.addObserver(this);
			this.player = player;
			currentList.put(player.getGameCharacter(), null);
		}
	}
	/**
	 * Allows the player to set the language
	 *
	 */
	public void settings() {
		menu.setVisible(false);
		String[] possibleValues = {};
		for(String langIterate : Strings.Localization.keySet())
			possibleValues[possibleValues.length+1]=langIterate;
		 lang = (String)JOptionPane.showInputDialog(frame,
		            cL.get(Strings.ChooseOne), cL.get(Strings.Input),
		            JOptionPane.INFORMATION_MESSAGE, null,
		            possibleValues, possibleValues[0]);
		if ((cL = Strings.Localization.get(lang))==null) //select the chosen lang
			cL = Strings.Localization.get(Strings.LocalizationDefault); //failsafe
		 guiPane.remove(guiPane.getIndexOf(menu));
		 menu=createIngameMenu();
		 guiPane.add( menu, JLayeredPane.POPUP_LAYER );
	}
	
	public void loadGame() {
		player.getGameCharacter().addAction(Strings.ButtonLoad);
		System.out.println(cL.get(Strings.ButtonLoad));
	}
	public void saveGame() {
		player.getGameCharacter().addAction(Strings.ButtonSave);
		System.out.println(cL.get(Strings.ButtonSave));
	}
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
		if( arg == null ) return;
		if (arg instanceof Envelope) {
			LinkedList<GameCharacter> remoteList = ((Envelope)arg).getGuiLinkedList();
			for (GameCharacter gameCharacter : remoteList)
				if (!currentList.keySet().contains(gameCharacter)) 
					currentList.put(gameCharacter, null);
			for( GameCharacter gameCharacter : currentList.keySet() )
				if( !remoteList.contains(gameCharacter) )
					removeList.add(gameCharacter);
		}
		SwingUtilities.invokeLater(r);
	}

		
	private class Runner implements Runnable {
		public void run() {
				if( !removeList.isEmpty()) 
					while(!removeList.isEmpty())
						removeGraphic(currentList.remove(removeList.removeLast()));
				for (GameCharacter gameCharacter : currentList.keySet()) {
					if (currentList.get(gameCharacter) == null) {
						currentList.put(gameCharacter, addGraphic());
						if( gameCharacter == player.getGameCharacter() )
							currentList.get(player).setLocation( new Point(350,250));
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
		 *            rtc - the container we want to remove @
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
		private void updateGraphic(GameCharacter gc) {
			GameAnimationEngine gae = GameAnimationEngine.getInstance();
			BufferedImage bimg = null;
			Point point = null;
			if ( gc != null ){
				point = (Point)gc.getPoint().clone();
				if (gc != player.getGameCharacter())
				// move everybody else 
					point.translate( (350 - gc.getPoint().x), (250 - gc.getPoint().y) );
				// The player want's to be centered in the window, atleast we think so ;)
				else point = new Point(350,250);
				if((bimg = gae.getImage(gc)) != null )
					if ( bimg.getHeight() > 100 )
						point.translate( ((100-bimg.getHeight())/2) , ((100-bimg.getHeight())/2) );
			}
			// no image, something went bananas, interrupt with a transparent picture please
			if ( bimg == null ) bimg = null;
			// if there is no GameCharacter we wont update
			if ( point != null ) {
				currentList.get(gc).setImage(bimg);
				currentList.get(gc).setLocation(point);
			}
		}
		
	}

}

