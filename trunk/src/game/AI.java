/**
 * @File    ai.java
 * @version 0.3
 * @author teddie
 *
 */
 
package game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import command.Commands;

//TODO KOMMENTERA i engelska samt snygga till koden!
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
    	
    	
        Point position=client.getPoint();
        Client closest = null;
        double smallest=0;
        double temp = 0; 
        boolean containsPlayer=false;
        
        for (Client c : nearbyClients)
        {
            if (c instanceof Player)
            {
            	containsPlayer=true;
                // pythagoras theorem provides the actual size to the nearest player.
                temp=distance(position, c);
            }
            if (temp>smallest) 
            {
                temp=smallest;
                closest=c;
            }
        }
        
        if ( !containsPlayer )// If no players are near-> move client along the pattern
            if (client instanceof Monster)
        	{
            	followPattern((Monster)client);
            	return;
        	}
        	
           
        if (distance(position,closest)<=10) //TODO Fixa mecket RANGE i gamecharacters
        { 
            //client.interpretCommand("attack"); //TODO se till att fanskapet attackerar spelaren
    	    return;
        }   
        
        else if ((Math.abs(closest.getPoint().getX()-position.getX()))<=(Math.abs(closest.getPoint().getY()-position.getY())))
        {
            //if the nearest client is closer in vertical direction than horisontal, move the monster vertically
            if (closest.getPoint().getX()>position.getX())
                client.setDirection(Commands.EAST);
            else
                client.setDirection(Commands.WEST);
        } 
    
        else    //same as above,
        {
            if (closest.getPoint().getY()>position.getY())
                client.setDirection(Commands.SOUTH);
            else
                client.setDirection(Commands.NORTH);
        }  
        client.interpretCommand("move");
     }  
    

      /**
      * When the monster got new info ask the AI for a decision
      * made in the decision as the monster and the monsters nearby clients as argument
      * @param O
      * @param arg
      */
        public void update(Observable O, Object arg)
        {
            System.out.println("monster skickar update");
           
            if( O instanceof Client && arg instanceof ArrayList )
            	decision((Client)O,(ArrayList<Client>)arg);  //TODO fixa ett try-catch
            	
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
     		if (!client.hasPattern())
     			client.setPattern();
 			client.decreaseRemaning();
 			client.interpretCommand("move");
    	}
}  