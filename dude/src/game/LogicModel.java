package game;

/**
 * Logic Model contains all client's in the game,
 * Can search for Clients with ID, x,y cordinates
 * Clients can be added, returned and removed
 *
 * @file LogicModel.java
 * @version 0.4
 * @author Mattias Lögdberg
 */

import java.awt.Point;
import java.util.ArrayList;
import java.util.TreeMap;

public class LogicModel {

    private TreeMap<Integer,Client> tm;
   
    /**
     * Logic model keeps all the clients data
     *
     */
   
    public LogicModel() {
        tm = new TreeMap<Integer,Client>();   
    }
    /**
     * Wrapper so you can use a Client object with nearbyClients
     * @param Client the client that's requesting
     * @param int the radius of the search
     * @return ArrayList with nearby Clients, empty if no one is there
     */
    public ArrayList<Client> nearbyClients( Client c, int r) {
        Point p = c.getGameCharacter().getPoint();
        return nearbyClients( new Point(p.x + c.getGameCharacter().getWidth()/2,
                                        p.y + c.getGameCharacter().getHeight()/2),
                                        r);
    }
    /**
     * Checks a point and radius for Clients
     * @param p a Point to check ( center of box )
     * @param r radius
     * @return ArrayList<Client> the list of nearby clients
     */
    public ArrayList<Client> nearbyClients( Point p, int r) {
        ArrayList<Client> clientList = new ArrayList<Client>();
        for( Client t : tm.values() ) {
            Point tP = t.getGameCharacter().getPoint();
            /*
                *******************
                *  _|t |_____ | |
                * | |__|     |
                * |  p.x,p.y |
                  |c         |
                  |__________|
                       <----->
                         r
                  x<p.x+r
                  y<p.y+r
                  x+b>p.x-r
                  y+h>p.y-r
             */
            if(( tP.x < p.x+r &&
                    tP.y < p.y+r &&
                    ((tP.x+t.getGameCharacter().getWidth()) > (p.x-r) ) &&
                    ((tP.y+t.getGameCharacter().getHeight()) > (p.y-r)) ))
                        clientList.add(t);
            }   
        return clientList;
    }
    /**
     * Adds an Client
     * @param client
     * @return true if client was added
     */
    public boolean addClient(Client client) {
        if(tm.put(client.getID(), client) != null)
            return true;
        return false;
    }
   
    /**
     * Gets the client with the represented id
     * @param id
     * @return client
     */
    public Client getClient( int id ) {
        Client tmp = tm.get( id);
        assert( tmp != null);
        return tmp;
    }
    /**
     * Get's all the clients that are in the game
     * @return ArrayList with all clients
     */
    public ArrayList<Client> getAllClients() {
    	ArrayList<Client> all = new ArrayList<Client>();
    	for( Integer i : tm.keySet() ){
    		all.add( getClient(i.intValue()));
    	}
    	return all;
    }
    /**
     * Checks if there is a client at that spot
     * @param point
     * @return false if client found at the spot
     */
    public boolean checkSpot(Point point){
        for( Client client : tm.values() ){
            if(client.getGameCharacter().getPoint().equals(point)){
                return false;
            }
        }
        return true;
    }
   
   public void clear()
   {
	   tm.clear();
   }
   
}