package command;

import game.Client;
import game.Engine;
import main.Strings;
import java.awt.Point;

/**
 * Makes the client move forward
 * @file CommandMove.java
 * @version 0.3
 * @author Josef Johansson
 *
 */

public class CommandMove extends Command
{
  	private static final double globalStepSize = 10;
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
    	System.out.println("frsker kra move fr: " + client.getName());
    	if( client != null) {
    		int x = client.getPoint().x;
    		int y = client.getPoint().y;
    		double stepSize = globalStepSize * client.getGameCharacter().getSpeed();
		
           /* switch(client.getDirection())
            {
                case "NORTH": y-=stepSize; break;
                case "SOUTH": y+=stepSize; break;
                case "EAST": x+=stepSize; break;
                case "WEST": x-=stepSize; break;
            }*/
			if(client.getDirection().equals(Strings.North)) y-=stepSize;
			if(client.getDirection().equals(Strings.South)) y+=stepSize;
			if(client.getDirection().equals(Strings.East)) x+=stepSize;
			if(client.getDirection().equals(Strings.West)) x-=stepSize;
			int[] u = {0,x,y}, v = { 1,x,y};
            if (engine.checkSpot( u ) && engine.checkSpot( v ))
            {
            	System.out.println("Position ndras. Frn " + client.getPoint() + " till " + new Point(x,y));
                client.updateCoordinates(new Point(x,y));
            }
    	}
    		
    }

}
 
