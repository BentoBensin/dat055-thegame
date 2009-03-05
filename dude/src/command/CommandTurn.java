package command;

import game.Client;
import game.Engine;

/**
 * GUI handles the turning of the client.. so this is useless
 * @author Josef Johansson
 *
 */


public class CommandTurn extends Command
{
    private int command;
    public CommandTurn(Engine engine, int command){
        super(engine);
        this.command = command;
    }

    /**
     * Executes the turn command
     * @param client
     */
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
