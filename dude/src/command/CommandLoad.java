package command;
import game.Engine;

import game.Client;
import java.io.*;

public class CommandLoad extends Command {

	public CommandLoad (Engine engine)
	{
		super(engine);
		
	}
	@Override
	public void execute(Client client) 
	{
		Client.paused=true;
		engine.reset();
		try{
			ObjectInputStream in= 
				new ObjectInputStream(
						new FileInputStream("save.sav"));
			while(true)
				engine.addClient((Client)in.readObject()); //TODO Mecka när det ändrats till GC
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Nåt annat sket sig");
			System.exit(0);
		}
		Client.paused=false;
	}

}
