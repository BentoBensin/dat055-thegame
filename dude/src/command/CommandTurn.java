package command;

import game.Client;
import game.Engine;

/**
 * should this be used?
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
     * Känns rätt hopplöst att kommentera denna atm
     * Should turn the client towars a direction.
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
