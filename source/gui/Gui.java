//gui.java

package gui;

import game.Client;
import game.IDGen;
import game.Player;
import gamecharacter.Warrior;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import main.strings;

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
	private JLayeredPane myLayeredPane;
	private JLabel WelcomeImage;
	private Player player;
	private HashMap<Client, TranspContainer> currentList;
	private ArrayList<Client> removeList;
	
	private Runner r;

	/**
	 * Konstruktorn g�r framen och alla grafiska objekt, vill att dom skall
	 * finnas n�r vi skapar GUIt
	 */
	// Object nedan skall vara player/client
	public Gui() {
		r = new Runner();
		// Current list of clients visible to player
		currentList = new HashMap<Client, TranspContainer>();
		removeList = new ArrayList<Client>();
		
		//thisDirection = new DirectionParser();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createGUI();
		
	}
	
	
	private void setPlayer(Player player){
		if(player != null){
			player.addObserver(this);
			this.player = player;
			currentList.put(player, null);
		}
	}
	/**
	 * s�tter att framen skall ha borderlayout l�gger till canvasen i mitten p�
	 * layouten Vi har en lagerpanel d�r vi kan l�gga till grafiska objekt i
	 * olika lager. Det finns 5 f�rdefinierade lager, men man kan nog anv�nda
	 * fler om man vill.
	 */
	private void createGUI() {
		frame.setTitle("Spel");
		frame.setLayout(new BorderLayout());
		myLayeredPane = new JLayeredPane();
		JPanel tmp = new JPanel();
		tmp.setBackground( new Color(1,107,6));
		myLayeredPane.add(tmp, JLayeredPane.DEFAULT_LAYER);
		
		tmp.setSize(800,600);
		menu = createIngameMenu();
		myLayeredPane.add( menu, JLayeredPane.POPUP_LAYER );
		
		// V�ran Lagerpanel kan inte vara genomskinlig
		myLayeredPane.setOpaque(true);

		frame.getContentPane().add(myLayeredPane, BorderLayout.CENTER);

		frame.addKeyListener(kl);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setResizable(false);
		// initContainer() m�ste alltid g�ras EFTER frame.pack()

		frame.setVisible(true);

	}

	public JPanel createIngameMenu(){
		BufferedReader reader = null;
		JPanel menu = new JPanel();
		menu.setLayout( new FlowLayout());
		menu.setBackground( new Color(1,107,6));
		try{
			reader =  new BufferedReader( new FileReader("languages/English/inGameMenu.txt"));
		}catch( IOException e) {
			e.printStackTrace();
			System.exit(0);
		}catch( Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		String[] functions = {"startgame","settings","loadgame","savegame","exitgame"};
		String line = "";
		for( String f : functions) {
			try{
				line = reader.readLine();
			}catch( IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
			JButton button = new JButton(line);
			button.setActionCommand(f);
			button.addActionListener(this);
			menu.add(button);
		}
		menu.setSize(120,160);
		menu.setLocation( ((800-menu.getWidth())/2) ,((600-menu.getHeight())/2));
		return menu;
	}
	
	public void actionPerformed( ActionEvent e) {
			try {
				this.getClass().getMethod(e.getActionCommand()).invoke(this);
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
	}
	
	
	public void startgame() {
		menu.setVisible(false);
		String tmp = (String) JOptionPane.showInputDialog(frame,"Ange Spelarnamn","Spelarnamn",JOptionPane.ERROR_MESSAGE);
		if( tmp != null){
			setPlayer(new Player( IDGen.generateID(), tmp, new Warrior() ));
		}else{
			menu.setVisible(true);
		}
	}
	public void settings() {
		System.out.println("Settings");
	}
	public void loadgame() {
		System.out.println("Load knapp");
	}
	public void savegame() {
		System.out.println("Save knapp");
	}
	public void exitgame() {
		System.exit(0);
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
		if( arg == null ) return;
		if (arg instanceof ArrayList) {
			ArrayList<Client> temp = (ArrayList<Client>) arg;
			for (Client client : temp) {
				if (!currentList.keySet().contains(client)) {
					currentList.put(client, null);
				}
			}
			for( Client client : currentList.keySet() ){
				if( !temp.contains(client) )
					removeList.add(client);
			}
		}
		SwingUtilities.invokeLater(r);
	}

	/**
	 * KeyListner �r en lyssnare som vi skapar med hj�lp av en hj�lpklass med
	 * anonym inre klass den utf�r saker beroende p� vilken h�ndelse (tangent)
	 * den "h�r"
	 */
	KeyListener kl = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {

			if( !Client.paused) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					player.setDirection(strings.North);
					player.addAction(strings.Move);
				}
	
				if (e.getKeyCode() == KeyEvent.VK_S) {
					player.setDirection(strings.South);
					player.addAction(strings.Move);
				}
	
				if (e.getKeyCode() == KeyEvent.VK_A) {
					player.setDirection(strings.West);
					player.addAction(strings.Move);
				}
	
				if (e.getKeyCode() == KeyEvent.VK_D) {
					player.setDirection(strings.East);
					player.addAction(strings.Move);
				}
				if (e.getKeyCode() == KeyEvent.VK_L) {
					player.addAction(strings.Attack);
					return;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_P) {
				player.addAction("pause");
				if( menu.isVisible() ) {
					menu.setVisible(false);
				}else
					menu.setVisible(true);
				return;
			}		
		}

		public void keyReleased(KeyEvent e) {
			player.addAction(strings.Still);
		}

	};
	
	private class Runner implements Runnable {
		public void run() {
				if( !removeList.isEmpty()) {
					int i = 0;
					while( !removeList.isEmpty()){
						removeGraphic(currentList.remove(removeList.remove(i++)));
					}
				}
				for (Client client : currentList.keySet()) {
					if (currentList.get(client) == null) {
						currentList.put(client, addGraphic());
						if( client instanceof Player )
							currentList.get(player).setLocation( new Point(350,250));
					}
					if( !Client.paused )
						updateGraphic(client);
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
			myLayeredPane.remove(myLayeredPane.getIndexOf(rtc));
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
			assert (myLayeredPane != null && tnc != null) : "myLayeredPane eller tnc �r Null i Gui.update()";
			try {
				myLayeredPane.add(tnc, JLayeredPane.PALETTE_LAYER);
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
		private void updateGraphic(Client client) {
			if( client != null && currentList.containsKey(client)){
				Point tmp = (Point)client.getPoint().clone();
					if( !(client instanceof Player) ){
						tmp.translate( (350 - player.getPoint().x), (250 - player.getPoint().y));
					}else{
						tmp = new Point(350,250);
					}
				BufferedImage bimg = client.getAnimation();
				if( bimg.getHeight() > 100 ) {
					tmp.translate( ((100-bimg.getHeight())/2) , ((100-bimg.getHeight())/2) );
				}
				currentList.get(client).setImage(client.getAnimation());
				currentList.get(client).setLocation(tmp);
			}
		}
		
	}

}
