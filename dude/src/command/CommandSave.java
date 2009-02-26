package command;
import java.io.*;
import game.Client;
import game.Engine;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		Client.paused = true;
		//String date=getDateTime();
		try{
			ObjectOutputStream out=new ObjectOutputStream
			(new FileOutputStream("BertilThomas.sav"));
			for (Client c : savebrust)
			{
				System.out.println("BT");
				out.writeObject(c);
			}
			
		}
		catch (IOException e)
		{
			System.out.println("Det sket sig");
			e.printStackTrace();
			System.exit(0);
		}
		Client.paused=false;
		
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-HH-mm");
        Date date = new Date();
        return dateFormat.format(date+".sav");
    } 
}
