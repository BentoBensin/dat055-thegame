package command;
import game.Client;
import game.Engine;

/**
 * The abstract class that defines a Command
 * @file Command.java
 * @version 0.3
 * @author Josef Johansson
 *
 */

public abstract class Command
{
	/**
	 * a shortcut to the engine
	 */
	protected Engine engine;
    
	/**
	 * Constructor
	 * @param engine add shortcut to engine
	 */
    public Command(Engine engine)
    {
        this.engine = engine;
    }
    
    /**
     * executes the command
     * @param client the client that the command is executed on
     */
    public abstract void execute(Client client);
}

