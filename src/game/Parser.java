package game;

import java.util.HashMap;
import java.util.Scanner;

import command.Command;
import command.Commands;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Parser 
{
    private Commands commands;  // holds all valid command words

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser(Engine engine) 
    {
        commands = new Commands(engine);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand(String inputLine) 
    {
        //String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();      // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if(commands.isCommand(word1)) {
            if( word2 != null )
            	commands.getCommand(word1).setParam(word2);
            return commands.getCommand(word1);
        }
        else {
            return null; 
        }
    }

    /**
     * Return a list of valid command words.
     */
    public String showCommands()
    {
        return commands.showAll();
    }
    /**
     * Returns all the commands we have.
     * @return HashMap<String,Command>
     */
    public HashMap<String,Command> getAllCommands()
    {
        return commands.getAllCommands();
    }
}
