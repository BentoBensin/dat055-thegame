package command;
import java.io.*;
import game.Client;
import game.Player;
import game.Engine;

import java.util.ArrayList;

/**
 * Saves the game enviroment into a file that is loadable.
 * @author teddie
 *
 */
public class CommandSave extends Command {

	public CommandSave (Engine engine)
    {
        super(engine);
        
    }
	@Override
	/**
	 * Performs the saving, serializing the gamecharacters and putting them to a file
	 * @param client
	 */
	public void execute(Client client) {
		
		ArrayList<Client> savebrust=engine.getAllClients();
		if(!client.isPaused()) client.togglePaused();
		
		try{
			ObjectOutputStream out=new ObjectOutputStream
			(new FileOutputStream("save.sav"));
			for (Client c : savebrust)
			{
				if( c instanceof Player) out.writeObject(c.getGameCharacter());
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		if(client.isPaused()) client.togglePaused();
	}
	

}
