package command;

import game.Client;
/**
 * @file CommandInterf.java
 * @version 0.1
 * @author mathal
 *	
 *	This is the interface for Commands in this game
 */
public interface CommandInterf {

    /**
     * executes the command
     * @param client the client that the command is executed on
     */
    public void execute(Client client);
}
