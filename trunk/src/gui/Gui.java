//gui.java

package gui;

import game.Client;
import game.Engine;
import game.IDGen;
import game.Player;
import gamecharacter.Warrior;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import main.Main;
import main.Strings;

/**
 * Write a description of class Gui
 * 
 * @file Gui.java
 * @version 0.6
 * @author Robban
 */
public class Gui implements Observer {
	private JFrame frame;
	private JMenuBar menu;
	private JLayeredPane myLayeredPane;
	private JLabel WelcomeImage;
	private boolean started;
	private Player player;
	private HashMap<Client, TranspContainer> currentList;
	
	private Runner r;

	/**
	 * We start with a empty shell, when you click new game, engine will create everything for us!
	 */
	public Gui() {
		
		started = false;
		//thisDirection = new DirectionParser();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGUI();
	}
	/**
	 * starts the gamegui
	 * @param player needs a player
	 */
	public void startGame() {
    	Engine engine = new Engine();
    	player = new Player( IDGen.generateID() , new Point( 500,250 ), 100, "player",new Warrior(), engine);
		player.addObserver(this);
		engine.addClient( player );
    	player.startThread();    	
    	engine.createEnemies(1);
		r = new Runner();
		// Current list of clients visible to player
		currentList = new HashMap<Client, TranspContainer>();
		currentList.put(player,null);
	}
	/**
	 * stter att framen skall ha borderlayout lgger till canvasen i mitten p
	 * layouten Vi har en lagerpanel dr vi kan lgga till grafiska objekt i
	 * olika lager. Det finns 5 frdefinierade lager, men man kan nog anvnda
	 * fler om man vill.
	 */
	private void createGUI() {
		frame.setTitle("Spel");
		frame.setLayout(new BorderLayout());
		myLayeredPane = new JLayeredPane();
		
		//assert(WelcomeImage != null);
		//myLayeredPane.add(WelcomeImage, JLayeredPane.DEFAULT_LAYER);
		// Vran Lagerpanel kan inte vara genomskinlig
		myLayeredPane.setOpaque(true);

		frame.getContentPane().add(myLayeredPane, BorderLayout.CENTER);

		frame.addKeyListener(kl);
		frame.setPreferredSize(new Dimension(800, 600));
		createMenu();
		frame.pack();
		frame.setResizable(false);
		//initContainer(); //must do this after frame.pack()

		frame.setVisible(true);

	}

	/**
	 * Creates a Menubar and adds a menu Archive and inside archive we add start and quit
	 * TODO Something wierd happens if you push start twice
	 */

	private void createMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu Arkiv = new JMenu("Arkiv");
		/**
		 * JMenuitem start with an anonymous inner class
		 */ 
		JMenuItem start = new JMenuItem("New Game");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When the game starts we are going to create the graphics
				// startGame();
				// TODO nicer way
				// Takes away the welcomescreen, guess that repaint is called when I call setSize
				if (!started) {
					WelcomeImage.setSize(0, 0);
					myLayeredPane.remove(myLayeredPane.getIndexOf(WelcomeImage));
					WelcomeImage = null;
					//startGame();
					started = true;
				}
			}
		});
		Arkiv.add(start);
		// JMenuitem quit med anonym inre klass.
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Arkiv.add(quit);
		menu.add(Arkiv);
		frame.setJMenuBar(menu);
	}

	/**
	 * removeGraphic removes the container from our main window
	 * 
	 * @param TranspContainer
	 *            rtc - the container we want to remove @
	 */

	private void removeGraphic(TranspContainer rtc) {
		rtc.setSize(0, 0);
		myLayeredPane.remove(myLayeredPane.getIndexOf(rtc));
	}

	/**
	 * update screen gets the keyset for the currentlist it uses the set to go
	 * through each client and update its corresponding value (TranspContainer)
	 * with the new animationimage and new (absolute) position
	 * 
	 */
	private void updateScreen() {
		if (started)
		for (Client client : currentList.keySet()) {
			currentList.get(client).setLocation(client.getPoint());
			currentList.get(client).setImage(client.getAnimation());
		}
	}

	/**
	 * update updates our currentList of clients it goes through each client in
	 * our currentlist and compares it with arg list, it adds a new client for
	 * each client in arglist that does not exist in our currentlist and removes
	 * the clients in currentlist that does not exist in the new list(arg)
	 * 
	 * @param Observable
	 *            client
	 * @param Object
	 *            arg - the new list
	 * 
	 */

	public void update(Observable o, Object arg) {
		if( arg == null || !started) return;
		if (arg instanceof ArrayList)
			for (Object object : (ArrayList)arg)
				if(object instanceof Client)
					if (!currentList.keySet().contains((Client)object))
						currentList.put((Client)object, null);
		/*
		 * Maybe a cleaner and safer way to accomplish the same thing? // Josef
		 */
		SwingUtilities.invokeLater(r);
	}

	/**
	 * KeyListner r en lyssnare som vi skapar med hjlp av en hjlpklass med
	 * anonym inre klass den utfr saker beroende p vilken hndelse (tangent)
	 * den "hr"
	 */
	KeyListener kl = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_W) {
				player.setDirection(Strings.North);
			}

			if (e.getKeyCode() == KeyEvent.VK_S) {
				player.setDirection(Strings.South);
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				player.setDirection(Strings.West);
			}

			if (e.getKeyCode() == KeyEvent.VK_D) {
				player.setDirection(Strings.East);
			}
			if (e.getKeyCode() == KeyEvent.VK_L) {
				player.addAction(Strings.Attack);
				return;
			}
			player.addAction(Strings.Move);

		}

		public void keyReleased(KeyEvent e) {
			player.addAction(Strings.Still);
			// Empty

		}

	};
	
	private class Runner implements Runnable {
		public void run() {
			for (Client client : currentList.keySet()) {
				if(currentList.get(client) == null) {
					TranspContainer tnc = new TranspContainer(100, 100);
					assert(myLayeredPane != null && tnc != null): "myLayeredPane eller tnc r Null i Gui.update()";
					try {
						myLayeredPane.add(tnc, JLayeredPane.PALETTE_LAYER);
					}catch(NullPointerException e){
						System.out.println("Nullpointer in Runner.run");
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
					
					tnc.setOpaque(false);
					currentList.put(client, tnc);
				}
				currentList.get(client).setLocation(client.getPoint());
				System.out.println("Denna Client har ett namn (null om inte) " + client.getName() + " x,y: " + currentList.get(client).getLocation());
				currentList.get(client).setImage(client.getAnimation());
			}
		}
	}

}
