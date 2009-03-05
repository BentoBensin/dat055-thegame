package command;

import game.Client;
import game.Engine;

import java.awt.Point;

import main.Strings;

/**
 * Makes the client move forward
 * @file CommandMove.java
 * @version 0.3
 * @author Josef Johansson
 *
 */

public class CommandMove extends Command
{
  	private static final double globalStepSize = 5;
    public CommandMove(Engine engine)
    {
        super(engine);
        
    }
    
    /**
     * Out execute command
     * @param client the input client 
     */
    
    public void execute(Client client)
    {
    	if( client != null) {
    		int x = client.getGameCharacter().getPoint().x;
    		int y = client.getGameCharacter().getPoint().y;
    		double stepSize = globalStepSize * client.getGameCharacter().getSpeed();

			if(client.getGameCharacter().getDirection().equals(Strings.North)) y-=stepSize;
			if(client.getGameCharacter().equals(Strings.South)) y+=stepSize;
			if(client.getGameCharacter().equals(Strings.East)) x+=stepSize;
			if(client.getGameCharacter().equals(Strings.West)) x-=stepSize;

			if(client.getGameCharacter().getDirection().equals(Strings.North)) y-=stepSize;
			if(client.getGameCharacter().getDirection().equals(Strings.South)) y+=stepSize;
			if(client.getGameCharacter().getDirection().equals(Strings.East)) x+=stepSize;
			if(client.getGameCharacter().getDirection().equals(Strings.West)) x-=stepSize;
			// tog bort && engine.checkSpot( 1,x,y ) frn nedanstende s lnge
			if (engine.checkSpot( 0, x, y ) && engine.checkSpot( 1, x, y ))
            {
            	//System.out.println("Position ndras. Frn " + client.getGameCharacter().getPoint() + " till " + new Point(x,y));
                client.getGameCharacter().updateCoordinates(new Point(x,y));
            }
    	}
    		
    }

}
 
