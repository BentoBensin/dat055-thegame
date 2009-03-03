package command;

import game.Client;
import game.Engine;

import java.util.Scanner;

/**
 * @file CommandUpdate
 * @version 0.1
 * @author mathal
 *
 *	CommandUpdate handles the update for all clients, returning all the nearby Gamecharacters
 *	
 */

public class CommandUpdate extends Command{

	public CommandUpdate(Engine engine){
        super(engine);
    }
 /**
  * Executes the update command
  */
    public void execute(Client client)
    {
    	System.out.println("Client requests an update");
    	
    	Scanner sc = new Scanner(param);
    	if( sc.hasNextInt() && client != null){
    		engine.nearbyClients(client, sc.nextInt() );
    	}
    }
}
