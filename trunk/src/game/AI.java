
 
package game;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import command.Commands;

/**
 * AI is responsible for the monsters decisions; whether it is going to follow a
 * given moving pattern or follow nearby clients to be able to attack them
 * @File    ai.java
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
    protected void decision(Client client, ArrayList nearbyClients)
    {
        Point position=client.getPoint();
        Player closest = null;
        double smallest= 1000;
        double temp = 0; 
        boolean containsPlayer=false;
        
        for (Object c : nearbyClients)
        {
            if (c instanceof Player)
            {
            	containsPlayer=true;
                // pythagoras theorem provides the actual size to the nearest player.
                temp=distance(position, (Player)c);
	            if (temp<smallest)  //<---- Meckat
	            {
	                temp=smallest;
	                closest=(Player)c;
	            }
            }
        }
        
        if ( !containsPlayer )// If no players are near-> move client along the pattern
        {
            if (client instanceof Monster)
        	{
            	followPattern((Monster)client);
            	return;
        	}
        }	
        
        /**
         * So let's se if the monster can attack anything
         */
        
        // Direction EAST
        if(closest != null && ((client.getPoint().x+client.getGameCharacter().getWidth())+(client.getGameCharacter().getAttackRange())) > closest.getPoint().x ){
        	 client.setDirection(Commands.EAST);
        	 client.addAction("attack");
        	 return;
        }
        // Direction WEST
        if(closest != null && ((client.getPoint().x - client.getGameCharacter().getAttackRange()) < (closest.getPoint().x+closest.getGameCharacter().getWidth())) ){
        	client.setDirection(Commands.WEST);
       	 	client.addAction("attack");
       	 	return;
        }
        // Direction SOUTH
        if( closest != null && ((client.getPoint().y+client.getGameCharacter().getHeight())+(client.getGameCharacter().getAttackRange())) > closest.getPoint().y ){
        	client.setDirection(Commands.SOUTH);
        	client.addAction("attack");
        	return;
        }
        // Direction NORTH
        if( closest != null && ((client.getPoint().y - client.getGameCharacter().getAttackRange()) < (closest.getPoint().y+closest.getGameCharacter().getHeight())) ){
        	client.setDirection(Commands.NORTH);
 	 		client.addAction("attack");
   	 		return;
    	}
        /**
         * Player not attackable
         * Start walk towards player
         */
        if( (client.getPoint().x - closest.getPoint().x) > (client.getPoint().y - closest.getPoint().y) )
        {
        	if (closest.getPoint().getX()>client.getPoint().getX())
                client.setDirection(Commands.EAST);
            else
                client.setDirection(Commands.WEST);	
        }else {
        	 if (closest.getPoint().getY()>client.getPoint().getY())
                 client.setDirection(Commands.SOUTH);
             else
                 client.setDirection(Commands.NORTH);
        }
        
        /*
        if ((Math.abs(closest.getPoint().getX()-position.getX()))<=(Math.abs(closest.getPoint().getY()-position.getY())))
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
        }*/
        client.addAction("walk");
     }  
    

      /**
      * When the monster got new info ask the AI for a decision
      * made in the decision as the monster and the monsters nearby clients as argument
      * @param O
      * @param arg
      */
        public void update(Observable O, Object arg)
        {
        	//followPattern((Monster)O);
        	//return;
        	if( O instanceof Client && arg instanceof ArrayList){
            	if( ((ArrayList<Client>)arg).size() > 1 )
            		decision((Client)O,(ArrayList)arg);  //TODO fixa ett try-catch
            	else
            		followPattern((Monster)O);
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
 			client.addAction("walk");
    	}
}  