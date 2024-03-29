﻿
 
package game;
import gamecharacter.GameCharacter;
import items.Weapon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import main.strings;
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
    protected void decision(Client client, ArrayList<Client> nearbyClients)
    {
    	Point clPos=client.getPoint();
        GameCharacter clGC = client.getGameCharacter();
        Weapon clWeapon = clGC.getPrimary();
        Player closest = null;
        double rangeTagEnemy = 1000;
        String action = null;
        String direction = null; 

        
        for (Client c : nearbyClients)
            if (c instanceof Player){
            	double i = clPos.distance(c.getPoint());
	            if (i<rangeTagEnemy){
	            	rangeTagEnemy = i;
	            	closest=(Player)c;
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
        int enX2 = enX + closest.getGameCharacter().getWidth();
        int enY2 = enY + closest.getGameCharacter().getHeight();
        if( clPos.x > enX && clPos.x < enX2 && clPos.y<enY) direction = strings.North;
        if( clPos.x > enX && clPos.x < enX2 && clPos.y>enY) direction = strings.South;
        if( clPos.y > enY && clPos.y < enY2 && clPos.x<enX) direction = strings.East;
        if( clPos.y > enY && clPos.y < enY2 && clPos.x>enX) direction = strings.West;
        if (direction == null) {
        	double dx = enX - clPos.x;
        	double dy = enY - clPos.y;
        	if( Math.sqrt(Math.pow(dx, 2)) < Math.sqrt(Math.pow(dy, 2))){
        		if (dx>0) direction = strings.West;
        		if (dx<0) direction = strings.East;
        	} else {
        		if (dy>0) direction = strings.South;
        		if (dy<0) direction = strings.North;
        		if (dy==0) direction = strings.North;
        	}
        }else {
        	// attakera?
        	double cx = clPos.x+clGC.getWidth()/2;
        	double cy = clPos.y+clGC.getHeight()/2;
        	double ex = enX+(enX2-enX)/2;
        	double ey = enY+(enY2-enY)/2;
        	if( Point.distance(cx, cy, ex, ey) <= clWeapon.getRange() ){
        		action = strings.Attack;
        	}
        	// we are at the same position as the enemy! 
        }	
        if (action == null) {
        	action = strings.Move;
        }
        client.setDirection(direction);
        client.addAction(action);
     }  
    

      /**
      * When the monster got new info ask the AI for a decision
      * made in the decision as the monster and the monsters nearby clients as argument
      * @param O
      * @param arg
      */
        public void update(Observable o, Object arg)
        {
        	if( o instanceof Client && arg instanceof ArrayList){
        		ArrayList<Client> argC = new ArrayList<Client>();
        		for(Object a : (ArrayList<Object>)arg)
        			if (a instanceof Client) argC.add((Client)a);
            	if(argC.size()>1) decision((Client)o,argC);  //TODO fixa ett try-catch -- tog bort <Client> i typomvandlingen på andra stället med.. //josef
            	else
            		followPattern((Monster)o);
        	}
        }
     
        /**
         * Distance between client
         * @param the clients whose distance is measured
         * @return distance in pixels
         */
         private double distance(Point position, Client c)
         {
             return (Math.sqrt((Math.pow((position.getX()-c.getPoint().getX()), 2))+
                               Math.pow((position.getY()-c.getPoint().getY()),2)));
         }
         
         /**
          * Makes the monster follow the monster specific moving pattern
          * @param client
          */
     	public void followPattern(Monster client)
    	{
     		System.out.println("followPattern()");
     		if (!client.hasPattern())
     			client.setPattern();
 			client.decreaseRemaning();
 			client.addAction(strings.Move);
    	}
}  