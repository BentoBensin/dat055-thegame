package command;

import game.Client;
/**
 * This is the interface for Commands in this game
 * @file CommandInterf.java
 * @version 0.1
 * @author mathal
 *	
 */
public interface CommandInterf {

    /**
     * executes the command
     * @param client the client that the command is executed on
     */
    public void execute(Client client);
}
