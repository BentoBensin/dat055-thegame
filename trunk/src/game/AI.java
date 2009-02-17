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
     *    Generates a random direction to move the monster
     * @return an integer between 0 and 3
     */
     public String randomDirectionGenerator()
       {   
         switch (new Random().nextInt(4))
         {
             case 0: return Commands.NORTH;
             case 1: return Commands.SOUTH;
             case 2: return Commands.EAST;
             case 3: return Commands.WEST;
             default: return "";
         }
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
        if ( nearbyClients.isEmpty() )// If no clients are close=>move the monster randomly
            client.interpretCommand(randomDirectionGenerator()); 
        else
        {
            for (Client c : nearbyClients)
            {
                if (c instanceof Player )
                {
                    // pythagoras theorem provides the actual size to the nearest player.
                    temp=distance(position, c);
                }
                if (temp>smallest) 
                {
                    temp=smallest;
                    closest=c;
                }
            }
            //If no enemy is close return move randomly
            if(closest == null) {
                client.interpretCommand(randomDirectionGenerator());
                return;
            }
            
           if (distance(position,closest)<=10){ //TODO Fixa mecket RANGE i gamecharacters
                //client.interpretCommand("attack");
        	   return;
           }   
            else if ((Math.abs(closest.getPoint().getX()-position.getX()))<=(Math.abs(closest.getPoint().getY()-position.getY())))
            {
                //if the nearest client is closer in vertical direction than horisontal, move the monster vertically
                if (closest.getPoint().getX()>position.getX())
                    client.interpretCommand(Commands.EAST);
                else
                    client.interpretCommand(Commands.WEST);
            } 
            else    //same as above,
            {
                if (closest.getPoint().getY()>position.getY())
                    client.interpretCommand(Commands.SOUTH);
                else
                    client.interpretCommand(Commands.NORTH);
            }  
            
        }  
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
         * Distance to client
         * @param clients
         * @return distance
         */
         private double distance(Point position, Client c)
         {
             return (Math.sqrt((Math.pow((position.getX()-c.getPoint().getX()), 2))+
                               Math.pow((position.getY()-c.getPoint().getY()),2)));
         }
}  