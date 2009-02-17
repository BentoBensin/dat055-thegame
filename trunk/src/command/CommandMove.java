package command;

import game.Client;
import game.Engine;

import java.awt.Point;

import javax.swing.SwingUtilities;

/**
 * @file CommandMove.java
 * @version 0.3
 * @author Josef Johansson
 *
 */

// TODO KOMMENTERA!!!!!!!
public class CommandMove extends Command
{
    private int direction;
    private String dir;
    private Client client;
	private static final int globalStepSize = 3;
    public CommandMove(Engine engine, int direction, String dir)
    {
        super(engine);
        this.direction = direction;
        this.dir = dir;
    }
    
    /*
     * this is where the actual moving forward
     */
    public void execute(Client client)
    {
    	this.client = client;
    	if( client != null) {
    		int x = client.getPoint().x;
		int y = client.getPoint().y;
		int stepSize = globalStepSize * client.getGameCharacter().getSpeed();	
           /* switch(client.getDirection())
            {
                case "NORTH": y-=stepSize; break;
                case "SOUTH": y+=stepSize; break;
                case "EAST": x+=stepSize; break;
                case "WEST": x-=stepSize; break;
            }*/
			if(client.getDirection().equals(Commands.NORTH)) y-=stepSize;
			if(client.getDirection().equals(Commands.SOUTH)) y+=stepSize;
			if(client.getDirection().equals(Commands.EAST)) x+=stepSize;
			if(client.getDirection().equals(Commands.WEST)) x-=stepSize;
			int[] u = {0,x,y}, v = { 1,x,y};
            if (engine.checkSpot( u ) && engine.checkSpot( v ))
            {
            	System.out.println("Position �ndras. Fr�n " + client.getPoint() + " till " + new Point(x,y));
                client.updateCoordinates(new Point(x,y));
            }
    	}
    		
    }

}
 