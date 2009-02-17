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
     * @param cord
     * @param radius
     * @return ArrayList with nearby Clients, empty if no one is there
     */
    public ArrayList<Client> nearbyClients( Point point, int radius) {
    	//System.out.println("letar klienter");
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

