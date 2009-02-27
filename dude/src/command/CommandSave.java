package command;
import java.io.*;
import game.Client;
import game.Player;
import game.Engine;


import java.util.ArrayList;


public class CommandSave extends Command {

	public CommandSave (Engine engine)
    {
        super(engine);
        
    }
	@Override
	public void execute(Client client) {
		// TODO Auto-generated method stub
		ArrayList<Client> savebrust=engine.getAllClients();
		if(!client.isPaused()) client.togglePaused();
		//String date=getDateTime();
		try{
			ObjectOutputStream out=new ObjectOutputStream
			(new FileOutputStream("BertilThomas.sav"));
			for (Client c : savebrust)
			{
				System.out.println("BT");
				if( c instanceof Player) out.writeObject(c.getGameCharacter());
			}
			
		}
		catch (IOException e)
		{
			System.out.println("Det sket sig");
			e.printStackTrace();
			System.exit(0);
		}
		if(client.isPaused()) client.togglePaused();
	}
	

}
