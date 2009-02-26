package command;
import game.Engine;

/**
 * The abstract class that defines a Command
 * @file Command.java
 * @version 0.3
 * @author Josef Johansson
 *
 */

public abstract class Command implements CommandInterf
{
	/**
	 * a shortcut to the engine
	 */
	protected Engine engine;
	protected String param;
    
	/**
	 * Constructor
	 * @param engine add shortcut to engine
	 */
    public Command(Engine engine)
    {
        this.engine = engine;
    }
    /**
     * Adds an parameter
     * @param param
     */
    public void setParam(String param){
    	this.param = param;
    }
}

