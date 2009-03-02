
 
package game;
import gamecharacter.GameCharacter;
import items.Weapon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import main.Envelope;
import main.Strings;
/**
 * AI is responsible for the monsters decisions; whether it is going to follow a
 * given moving pattern or follow nearby clients to be able to attack them
 * @File    AI.java
 * @version 0.4
 * @author Raul
 *
 */
public class AI implements GenericInterface, Observer
{
 
    public AI()
    {
        //Nothing todo at moment
    }


     /**
      * Checks whether the monster will walk randomly or towards another player
      * @param position
      * @param nearbyClients
      */
    protected void decision(Client client, ArrayList<GameCharacter> nearbyClients)
    {
    	if( client == null || client.getGameCharacter() == null) {
    		return;
    	}
    	GameCharacter clGC = client.getGameCharacter();
    	Point clPos= clGC.getPoint();
        Weapon clWeapon = clGC.getPrimary();
        GameCharacter closest = null;
        double rangeTagEnemy = 1000;
        String action = null;
        String direction = null; 

        
        for (GameCharacter gc : nearbyClients)
            if (gc.isPlayer() && gc.isAlive()){
            	double i = clPos.distance(gc.getPoint());
	            if (i<rangeTagEnemy){
	            	rangeTagEnemy = i;
	            	closest=gc;
	            }
            }
    	if ( nearbyClients.size() == 0 || closest == null) // If no players are near-> move client along the pattern
            if (client instanceof Monster)
        	{
            	followPattern((Monster)client);
            	return;
        	}
        /**
         * So let's see if the monster can attack anything
         */
        
        /**
         * ***********************
         *           
         *     __       __
         *    |en|  w<-|cl|
         *    |__|     |__|
         * 
         * ***********************
         */
        /**Case One
         * We find a nearby player and we try to fight him
         * find direction
         * Case One Subcase One
         * the player was within range and attackable ATTACK
         * case one subcase two
         * the player was outside range and we move
         * Case Two
         * We don't find a nearby player and move according to pattern
         */        
        int enX = closest.getPoint().x;
        int enY = closest.getPoint().y;
        int enX2 = enX + closest.getWidth();
        int enY2 = enY + closest.getHeight();
        if( clPos.x > enX && clPos.x < enX2 && clPos.y<enY) direction = Strings.North;
        if( clPos.x > enX && clPos.x < enX2 && clPos.y>enY) direction = Strings.South;
        if( clPos.y > enY && clPos.y < enY2 && clPos.x<enX) direction = Strings.East;
        if( clPos.y > enY && clPos.y < enY2 && clPos.x>enX) direction = Strings.West;
        if (direction == null) {
        	double dx = enX - clPos.x;
        	double dy = enY - clPos.y;
        	if( Math.sqrt(Math.pow(dx, 2)) < Math.sqrt(Math.pow(dy, 2))){
        		if (dx>0) direction = Strings.West;
        		if (dx<0) direction = Strings.East;
        		if (dx==0) direction = Strings.East;
        	} else {
        		if (dy>0) direction = Strings.South;
        		if (dy<0) direction = Strings.North;
        		if (dy==0) direction = Strings.North;
        	}
        }else {
        	// attakera?
        	double cx = clPos.x+clGC.getWidth()/2;
        	double cy = clPos.y+clGC.getHeight()/2;
        	double ex = enX+(enX2-enX)/2;
        	double ey = enY+(enY2-enY)/2;
        	if( Point.distance(cx, cy, ex, ey) <= clWeapon.getRange() ){
        		action = Strings.Attack;
        	}
        	// we are at the same position as the enemy! 
        }	
        if (action == null) {
        	action = Strings.Move;
        }
        
        clGC.setDirection(direction);
        clGC.addAction(action);
        
        
        
     }  
    

      /**
      * When the monster got new info ask the AI for a decision
      * made in the decision as the monster and the monsters nearby clients as argument
      * @param O
      * @param arg
      */
        public void update(Observable o, Object arg) 
        {
        	if( o instanceof Client && arg instanceof Envelope){
        		Envelope env = (Envelope) arg;
        		ArrayList<GameCharacter> argC = new ArrayList<GameCharacter>();
        		for(Object a : env.getGuiLinkedList())
        			if (a instanceof GameCharacter){
        				argC.add((GameCharacter)a);
        			}
            	if(argC.size()>1) decision((Client)o,argC);  
            	else
            		followPattern((Monster)o);
        	}
        }
     
        
         /**
          * Makes the monster follow the monster specific moving pattern
          * @param client
          */
     	public void followPattern(Monster client)
    	{
     		client.getGameCharacter().followPattern();
    	}
}  