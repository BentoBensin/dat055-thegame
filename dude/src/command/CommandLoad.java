package command;
import game.Engine;

import game.Client;
import game.Player;
import gamecharacter.GameCharacter;
import game.IDGen;
import java.io.*;

public class CommandLoad extends Command {

	public CommandLoad (Engine engine)
	{
		super(engine);
		
	}
	@Override
	public void execute(Client client) 
	{
		if(!client.isPaused()) client.togglePaused();
		engine.reset();
		try{
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
