//gui.java

package gui;

import game.Client;
import game.Player;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

import command.Commands;

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
	 * Konstruktorn gör framen och alla grafiska objekt, vill att dom skall
	 * finnas när vi skapar GUIt
	 */
	// Object nedan skall vara player/client
	public Gui(Player player) {
		player.addObserver(this);
		this.player = player;
		started = false;
		r = new Runner();
		// Current list of clients visible to player
		currentList = new HashMap<Client, TranspContainer>();
		currentList.put(player,null);
		//thisDirection = new DirectionParser();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGUI();
	}

	/**
	 * sätter att framen skall ha borderlayout lägger till canvasen i mitten på
	 * layouten Vi har en lagerpanel där vi kan lägga till grafiska objekt i
	 * olika lager. Det finns 5 fördefinierade lager, men man kan nog använda
	 * fler om man vill.
	 */
	private void createGUI() {
		frame.setTitle("Spel");
		frame.setLayout(new BorderLayout());
		myLayeredPane = new JLayeredPane();
		
		//assert(WelcomeImage != null);
		//myLayeredPane.add(WelcomeImage, JLayeredPane.DEFAULT_LAYER);
		// Våran Lagerpanel kan inte vara genomskinlig
		myLayeredPane.setOpaque(true);

		frame.getContentPane().add(myLayeredPane, BorderLayout.CENTER);

		frame.addKeyListener(kl);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		createMenu();
		frame.setResizable(false);
		// initContainer() måste alltid göras EFTER frame.pack()

		frame.setVisible(true);

	}

	/**
	 * Skapar Menubar och lägger till en meny Arkiv, i arkiv lägger vi till två
	 * menu items start och quit (som är lyssnare) Obs!! Buggar ur om man
	 * trycker start mer än en gång.
	 */

	private void createMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu Arkiv = new JMenu("Arkiv");
		

		// JMenuitem start med anonym inre klass.
		JMenuItem start = new JMenuItem("New Game");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// När spelet startar skall vi skapa alla grafiska
				// komponenter
				startGame();
				// Tar bort välkomstbilden, antar att repaint anropas när
				// jag anropar setSize, kom inte på ett snyggare sätt
				// att få bort bilden.
				if (!started) {
					WelcomeImage.setSize(0, 0);
					myLayeredPane.remove(myLayeredPane.getIndexOf(WelcomeImage));
					WelcomeImage = null;
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
		startGame();
	}

	private void startGame() {
		started = true;
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
		if (arg instanceof ArrayList) {
			ArrayList<Client> temp = (ArrayList<Client>) arg;
			for (Client client : temp) {
				if (!currentList.keySet().contains(client)) {
					currentList.put(client, null);
				}

			}
		}
		SwingUtilities.invokeLater(r);
	}

	/**
	 * KeyListner är en lyssnare som vi skapar med hjälp av en hjälpklass med
	 * anonym inre klass den utför saker beroende på vilken händelse (tangent)
	 * den "hör"
	 */
	KeyListener kl = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_W) {
				player.setDirection(Commands.NORTH);
			}

			if (e.getKeyCode() == KeyEvent.VK_S) {
				player.setDirection(Commands.SOUTH);
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				player.setDirection(Commands.WEST);
			}

			if (e.getKeyCode() == KeyEvent.VK_D) {
				player.setDirection(Commands.EAST);
			}
			if (e.getKeyCode() == KeyEvent.VK_L) {
				player.addAction("attack");
				return;
			}
			player.addAction("walk");

		}

		public void keyReleased(KeyEvent e) {

			// Empty

		}

	};
	
	private class Runner implements Runnable {
		public void run() {
			for (Client client : currentList.keySet()) {
				if(currentList.get(client) == null) {
					TranspContainer tnc = new TranspContainer(100, 100);
					assert(myLayeredPane != null && tnc != null): "myLayeredPane eller tnc är Null i Gui.update()";
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
