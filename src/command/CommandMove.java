package command;

import game.Client;
import game.Engine;

import java.awt.Point;

import javax.swing.SwingUtilities;

/**
 * @author Josef Johansson
 *
 */

// TODO KOMMENTERA!!!!!!!
public class CommandMove extends Command
{
    private int direction;
    private String dir;
    private Client client;
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
           /* if(direction.equals(Commands.NORTH)) {
            	y-= STEPSIZE;
            }else if(direction.equals(Commands.SOUTH)) {
            	y += STEPSIZE;
            }else if( direction.equals(Commands.WEST)){
            	x -= STEPSIZE;
            }else if( direction.equals(Commands.EAST)) {
            	x += STEPSIZE;
            }
            */
            switch(direction)
            {
                case 0: y-=STEPSIZE; break;
                case 1: y+=STEPSIZE; break;
                case 2: x+=STEPSIZE; break;
                case 3: x-=STEPSIZE; break;
            }
            //int v[] = {0,x,y} , u[] = {1,x,y}; //TODO snygga upp
            //if (engine.checkSpot( v ) && engine.checkSpot( u ))
            //{
            	System.out.println("Position ändras. Från " + client.getPoint() + " till " + new Point(x,y));
                client.updateCoordinates(new Point(x,y));
                if( !client.getAnimationType().equals(dir)) 
                	client.setAnimationType(dir);
            //}
    	}
    		
    }

}
 