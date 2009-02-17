package game;

/**
 * @file LogicModel.java
 * @version 0.2
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
     * Get's the nearby client to an Coordinate position and
     * a radius around the position
     * @param Client the client that's requesting
     * @param int the radius of the search
     * @return ArrayList with nearby Clients, empty if no one is there
     */
    public ArrayList<Client> nearbyClients( Client c, int r) {
    	//System.out.println("letar klienter");
    	ArrayList<Client> clientList = new ArrayList<Client>();
	Point p = c.getPoint();
        double cx = p.x + c.getWidth/2;
        double cy = p.y + c.getHeight/2;
        for( Client t : tm.values() ) {
            Point tP = t.getPoint();
		/*
*******************
*  _|t |___ | |
* | |__|   |
* |  cx,cy |
  |c       |
  |________|
       <--->
         r
  x<cx+r
  y<cy+r
  x+b>cx-r
  y+h>cy-r
		*/

            if( tP.x > cx+r && tP.y < cy+r && tP.x+t.getWidth() > cx-r && tP.y+t.getHeight() > cy-r )
                        clientList.add(t);
            }    
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
     * Checks if there is a client at that spot
     * @param point
     * @return false if client found at the spot
     */
    public boolean checkSpot(Point point){
        for( Client client : tm.values() ){
            if(client.getPoint().equals(point)){
                return false;
            }
        }
        return true;
    }
    
}

