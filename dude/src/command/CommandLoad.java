package command;
import game.Client;
import game.Engine;
import gamecharacter.GameCharacter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class resets the engine and reloads the players saved in the sav-file and puts them
 * in the game engine
 * @file CommandLoad.java
 * @version 0.1
 * @author raul
 *
 */
public class CommandLoad extends Command {

	public CommandLoad (Engine engine)
	{
		super(engine);
		
	}
	@Override
	 /**
	  * Executes the load command
	  * @param client
	  */
	public void execute(Client client) 
	{
		//pause clients
		if(!client.isPaused()) client.togglePaused(); 
		//remove every client
		engine.reset();
		try{   						// read clients from sav-file and put them into engine
			ObjectInputStream in= 
				new ObjectInputStream(
						new FileInputStream("save.sav"));
			Object loadedGameChar=in.readObject();
			while(loadedGameChar!=null){ 
				client.setGameCharacter((GameCharacter)loadedGameChar);
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Nt annat sket sig");
			System.exit(0);
		}
		if(client.isPaused()) client.togglePaused();
	}

}
