package command;

import game.Client;
import game.Engine;

/**
 * This Command toggles the pause function of all the
 * Client threads. Eg. pausing or unpausing the game.
 * 
 * @file CommandPauseToggle.java
 * @version 0.1
 * @author Mattias och Robban
 *
 */


public class CommandPauseToggle extends Command
{
    public CommandPauseToggle(Engine engine){
        super(engine);
    }
 
    /**
     * Executes the pause command
     * pauses all clients if they are unpaused and
     * unpauses if paused
     */
    public void execute(Client client)
    {
    	for( Client c : engine.getAllClients() ){
    		c.togglePaused();
    		if( !c.isPaused())
    			c.resumeThread();
    	}    	
    }
}
