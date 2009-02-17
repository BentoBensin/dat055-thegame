package game;

/**
 * Logic Model contains all client's in the game,
 * Can search for Clients with ID, x,y cordinates
 * Clients can be added, returned and removed
 * 
 * @file LogicModel.java
 * @version 0.3
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
    	ArrayList<Client> clientList = new ArrayList<Client>();
    	Point p = c.getPoint();
        double cx = p.x + c.getGameCharacter().getWidth()/2;
        double cy = p.y + c.getGameCharacter().getHeight()/2;
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
            if( tP.x > cx+r && tP.y < cy+r && tP.x+t.getGameCharacter().getWidth() > cx-r && tP.y+t.getGameCharacter().getHeight() > cy-r )
                        clientList.add(t);
            }    
        return clientList;
    }
    
    public ArrayList<Client> nearbyClients( Point point, int radius) {
    	ArrayList<Client> clientList = new ArrayList<Client>();
        //TODO put the control in Cordinates object?
        assert(point != null);   
        double maxX = point.getX() + radius;
        double minX = point.getX() - radius;
        double maxY = point.getY() + radius;
        double minY = point.getY() - radius;
        
        for( Client cl : tm.values() ) {
            Point clPoint = cl.getPoint();
            
            if( clPoint.getX() > minX && clPoint.getX() < maxX ) {
                if( clPoint.getY() > minY && clPoint.getY() < maxY ) {
                        clientList.add(cl);
                }
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

