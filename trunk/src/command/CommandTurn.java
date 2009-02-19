package command;

import game.Client;
import game.Engine;

/**
 * should this be used?
 * @author Josef Johansson
 *
 */

//TODO KOMMENTERA!!!!!!!
public class CommandTurn extends Command
{
    private int command;
    public CommandTurn(Engine engine, int command){
        super(engine);
        this.command = command;
    }
 
    public void execute(Client client)
    {
        switch(command)
        {
            case 0:break;
            case 1:break;
            case 2:break;
            case 3:break;
        }
    }
}
