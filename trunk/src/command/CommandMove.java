package command;

import game.Client;
import game.Engine;
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
  	private static final double globalStepSize = 3;
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
    		switch ( ){}
			if(client.getDirection().equals(Commands.NORTH)) y-=stepSize;
			if(client.getDirection().equals(Commands.SOUTH)) y+=stepSize;
			if(client.getDirection().equals(Commands.EAST)) x+=stepSize;
			if(client.getDirection().equals(Commands.WEST)) x-=stepSize;
			int[] u = {0,x,y}, v = { 1,x,y};
            if (engine.checkSpot( u ) && engine.checkSpot( v ))
            {
            	System.out.println("Position ändras. Från " + client.getPoint() + " till " + new Point(x,y));
                client.updateCoordinates(new Point(x,y));
            }
    	}
    		
    }

}
 
