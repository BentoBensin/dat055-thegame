package command;

import game.Client;
import game.Engine;

/**
 * @file Command.java
 * @version 0.3
 * @author Josef Johansson
 *
 */

//TODO KOMMENTERA!!!!!!!
public abstract class Command
{
    protected Engine engine;
    
    public Command(Engine engine)
    {
        this.engine = engine;
    }
    public abstract void execute(Client client);
    
}

