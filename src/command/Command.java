package command;

import game.Client;
import game.Engine;

/**
 * @author Josef Johansson
 *
 */

//TODO KOMMENTERA!!!!!!!
public abstract class Command
{
    protected Engine engine;
    private String param;
    public static final int STEPSIZE = 3;
    
    public Command(Engine engine)
    {
        this.engine = engine;
    }
    public abstract void execute(Client client);
       
    public void setParam(String param){
    	this.param = param;
    }
    
    protected String getParam() {
    	return param;
    }
}

