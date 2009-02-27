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
 
    public void execute(Client client)
    {
    	client.togglePaused();
    	System.out.println("Nu ska vi pausa:" + client.isPaused() );
    	if( !client.isPaused() ) {
    		for( Client c : engine.getAllClients() ){
    			c.resumeThread();
    		}
    	}
    	
    }
}
