//gui.java

package gui;

import game.Client;
import game.IDGen;
import game.Player;
import gamecharacter.Warrior;

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
	private JLayeredPane myLayeredPane;
	private JLabel WelcomeImage;
	private Player player;
	private HashMap<Client, TranspContainer> currentList;
	private ArrayList<Client> removeList;
	
	private GraphicsEnvironment ge;
	private GraphicsDevice gd;
	private DisplayMode dmode;
	private JWindow win;
	private String lang;
	private Runner r;

	/**
	 * Konstruktorn gör framen och alla grafiska objekt, vill att dom skall
	 * finnas när vi skapar GUIt
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
		
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd = ge.getDefaultScreenDevice();
		dmode = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN); //sista arg DisplayMode.REFRESH_RATE_UNKNOWN
		    
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win = new JWindow(frame,gd.getDefaultConfiguration());

		//set default language
		lang="Svenska";
		
		String[] a = {"Fullskärmsläge", "Fönsterläge"};
        int x = JOptionPane.showOptionDialog(null, "Välj ett alternativ", "Grafikläge", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,a,a[1]);
        if(x==0) createFullGUI();
        else createGUI();
		
	}
	
	
	private void setPlayer(Player player){
		if(player != null){
			player.addObserver(this);
			this.player = player;
			currentList.put(player, null);
		}
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
		JPanel tmp = new JPanel();
		tmp.setBackground( new Color(1,107,6));
		myLayeredPane.add(tmp, JLayeredPane.DEFAULT_LAYER);
		
		tmp.setSize(800,600);
		menu = createIngameMenu();
		myLayeredPane.add( menu, JLayeredPane.POPUP_LAYER );
		
		// Våran Lagerpanel kan inte vara genomskinlig
		myLayeredPane.setOpaque(true);

		frame.getContentPane().add(myLayeredPane, BorderLayout.CENTER);

		frame.addKeyListener(kl);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.pack();
		frame.setResizable(false);
		// initContainer() måste alltid göras EFTER frame.pack()

		frame.setVisible(true);

	}
	
	private void createFullGUI()
	{
		frame.setTitle("Spel");
		frame.validate();
		frame.setVisible(true);
		
		myLayeredPane = new JLayeredPane();
		myLayeredPane.setOpaque(true);
			
		win.getContentPane().add(myLayeredPane, BorderLayout.CENTER);
		//////////////////
		JPanel tmp = new JPanel();
		tmp.setBackground( new Color(1,107,6));
		myLayeredPane.add(tmp, JLayeredPane.DEFAULT_LAYER);
		
		tmp.setSize(800,600);
		menu = createIngameMenu();
		myLayeredPane.add( menu, JLayeredPane.POPUP_LAYER );
		//////////////////
	      	win.addKeyListener(kl);
	      	win.validate();
	      	win.setFocusable(true);
	      	gd.setFullScreenWindow(win);
	      	gd.setDisplayMode(dmode);
	      	win.requestFocus();
		

	}
	


	public JPanel createIngameMenu(){
		BufferedReader reader = null;
		JPanel menu = new JPanel();
		menu.setLayout( new FlowLayout());
		menu.setBackground( new Color(1,107,6));
		try{
			reader =  new BufferedReader( new FileReader("languages/"+lang+"/inGameMenu.txt"));
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
		menu.setVisible(false);
		 Object[] possibleValues = { "English", "Svenska"};
		 lang = (String)JOptionPane.showInputDialog(frame,

		            "Choose one", "Input",

		            JOptionPane.INFORMATION_MESSAGE, null,

		            possibleValues, possibleValues[0]);

		 myLayeredPane.remove(myLayeredPane.getIndexOf(menu));
		 menu=createIngameMenu();
		 myLayeredPane.add( menu, JLayeredPane.POPUP_LAYER );
	}
	
	public void loadgame() {
		player.addAction("load");
		System.out.println("Loadknapp");
	}
	public void savegame() {
		player.addAction("save");
		System.out.println("Save knapp");
	}
	public void exitgame() {
		int choice=(int)JOptionPane.showConfirmDialog(frame,

	            "Wanna quit, sir?", "Vill du avsluta?", JOptionPane.YES_NO_OPTION);
		if (choice==0)
			System.exit(0);
		else return;
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
	 * KeyListner är en lyssnare som vi skapar med hjälp av en hjälpklass med
	 * anonym inre klass den utför saker beroende på vilken händelse (tangent)
	 * den "hör"
	 */
	KeyListener kl = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {

			if( !Client.paused) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
					player.setDirection(Strings.North);
					player.addAction(Strings.Move);
				}
	
				if (e.getKeyCode() == KeyEvent.VK_S) {
					player.setDirection(Strings.South);
					player.addAction(Strings.Move);
				}
	
				if (e.getKeyCode() == KeyEvent.VK_A) {
					player.setDirection(Strings.West);
					player.addAction(Strings.Move);
				}
	
				if (e.getKeyCode() == KeyEvent.VK_D) {
					player.setDirection(Strings.East);
					player.addAction(Strings.Move);
				}
				if (e.getKeyCode() == KeyEvent.VK_L) {
					player.addAction(Strings.Attack);
					return;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				player.addAction("pause");
				if( menu.isVisible() ) {
					menu.setVisible(false);
				}else
					menu.setVisible(true);
				return;
			}		
		}

		public void keyReleased(KeyEvent e) {
			player.addAction(Strings.Still);
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
			assert (myLayeredPane != null && tnc != null) : "myLayeredPane eller tnc är Null i Gui.update()";
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
				// animeringsmotor .getAnimation( gc );
				if( bimg.getHeight() > 100 ) {
					tmp.translate( ((100-bimg.getHeight())/2) , ((100-bimg.getHeight())/2) );
				}
				currentList.get(client).setImage(client.getAnimation());
				currentList.get(client).setLocation(tmp);
			}
		}
		
	}

}

