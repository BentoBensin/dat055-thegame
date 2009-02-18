package game;

import gamecharacter.ShroomsMan;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import map.MapPiece;
import map.Maps;

import command.Command;

/**
 * @file Engine.java
 * @version 0.2
 * @author Josef Johansson and Mattias Lögdberg
 */
public class Engine {
    /**
     * This is the commands we have
     */
    private Parser parser;
    /**
     * Stores a view of the map, maps are loaded from GUI vid loadMap.
     */
    private Maps maps;
    /**
     * Out logicModel stores data as players and coordinates
     */
    private LogicModel logicModel;
    private AI ai;
    
    public Engine()
    {
    	parser = new Parser(this);
        maps = new Maps();
        logicModel = new LogicModel();
        ai = new AI();
    }
    /**
     * Adds an Client to the LogicModel
     * @param name
     * @param genericInterface
     */
    public Boolean addClient(Client client)
    {	
    	if( client == null)
    		throw new NullPointerException("Nullpointer in addClient, engine");
    	return logicModel.addClient(client);
    }
    
    public void createEnemies( int n ){
    	Client monster;
    
    	for( int i = 0 ; i < n ; i++ ) {
    		monster = new Monster( IDGen.generateID() , new Point( 200, 100), 100, "Monster", new ShroomsMan(), this);
    		//TODO Clone av animeringen!!!
    		monster.addObserver( ai );
    		addClient(monster );
    		monster.startThread();
    	}
    }
    /**
     * Get's all the nearby clients of an point
     * @param point
     * @param radius
     * @return ArrayList of nearby clients
     */
    public ArrayList<Client> nearbyClients( Client client, int radius){
    	if( client == null || radius < 0)
    		throw new IllegalArgumentException();
    	return logicModel.nearbyClients(client, radius);
    }
    public ArrayList<Client> nearbyClients( Point point, int radius){
    	if( point == null || radius < 0)
    		throw new IllegalArgumentException();
    	return logicModel.nearbyClients(point, radius);
    }
    /**
     * loads a map into the memory from file, this is just a tunnel from client to Maps.
     * @param mapPath
     */
    public void loadMap(String mapPath) throws IOException
    {
       	maps.loadMap(mapPath);
    }
    public boolean checkSpot(int [] v) {
    	return maps.checkSpot(v);
    }
    /**
     * his function returns a loaded map to the GUI
     * @param map
     * @return TreeMap<String,MapPiece>, returns empty TreeMap if no objects exist.
     */
    public TreeMap<String,MapPiece> getMap(String map)
    {
       /* if (maps.isMap(map))
        return maps.getMap(map);*/
        return new TreeMap<String,MapPiece>();
    }
    /**
     * Sends the genericInterface's command to the given command.
     * @param client
     * @param command
     */
    public void interpretCommand(String cmd, int id)
    {
    	Command command = parser.getCommand(cmd);
        try {
        	command.execute(logicModel.getClient(id));
        }catch( NullPointerException e) {
        	//hmm must check this out
        }
    }
    /**
     * Returns the commands that we have defined.
     * @return HashMap<String,Command>
     */
    public HashMap<String,Command> getCommands()
    {
        return parser.getAllCommands();
    }
}


