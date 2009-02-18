package command;

import game.Client;
import game.Engine;

import java.util.ArrayList;


public class CommandAttack extends Command 
{
	public CommandAttack(Engine engine)
	{
		 super(engine);
	}
	

	/**
	 * If any clients are within attack range while attacking they will decrease in health points
	 */
	 public void execute(Client client)
	 {
		 ArrayList<Client> targets=engine.nearbyClients(client, client.getGameCharacter().getAttackRange);
		 if (targets.isEmpty())
			 return;
		 else
			 for (Client c : targets)
			 {
				 c.changeHealth(-10);
				 c.setAnimationType("hit");
			 }
		 
				 
		 
	 }
}
