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
	private static final int globalStepSize = 10;
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
	int stepSize = globalStepSize * client.getSpeed();	
            switch(client.getDirection())
            {
                case "NORTH": y-=stepSize; break;
                case "SOUTH": y+=stepSize; break;
                case "EAST": x+=stepSize; break;
                case "WEST": x-=stepSize; break;
            }
            if (engine.checkSpot( int[]{0,x,y} ) && engine.checkSpot( int[]{1,x,y} ))
            {
            	System.out.println("Position ändras. Från " + client.getPoint() + " till " + new Point(x,y));
                client.updateCoordinates(new Point(x,y));
            }
    	}
    		
    }

}
 
