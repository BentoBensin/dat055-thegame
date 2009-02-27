package command;
import game.Engine;

import game.Client;
import game.Player;
import gamecharacter.GameCharacter;
import game.IDGen;
import java.io.*;

/**
 * This class resets the engine and reloads the players saved in the sav-file and puts them
 * in the game engine
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
	 * The command specific method doing 
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
			while(true){ 
				engine.addClient(new Player(IDGen.generateID(),(GameCharacter)in.readObject())); //TODO Mecka nr det ndrats till GC
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
