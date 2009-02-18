package command;

import game.Client;
import game.Engine;

import java.util.ArrayList;


public class CommandAttack extends Command 
{
	public static final String ATTACK = "attack";
	
	public CommandAttack(Engine engine)
	{
		 super(engine);
	}
	
	

	/**
	 * If any clients are within attack range while attacking they will decrease in health points
	 */
	 public void execute(Client client)
	 {
		 ArrayList<Client> targets=engine.nearbyClients(client, client.getGameCharacter().getAttackRange());
		 if( !client.getAnimationType().equals(ATTACK))
			 client.setAnimationType(ATTACK);
		 if (targets.isEmpty())
			 return;
		 else
			 for (Client c : targets)
			 {
				 if( c != client){
					 c.changeHealth(-10);
					 if( !c.getAnimationType().equals("hit"))
						 c.setAnimationType("hit");
				 }
			 }
		 
				 
		 
	 }
}
