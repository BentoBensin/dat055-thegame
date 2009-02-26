package command;

import game.Engine;
import main.strings;
import java.util.HashMap;


/**
 * This is the class that handles and creates all of the commands
 * @file Commands.java
 * @version 0.1
 * @author Josef Johansson
 */
public class Commands
    {
        /**
         * This variable contains our commands
         */
        private HashMap<String,Command> commands;
        /**
         * Constructor, runs inializeCommands
         * @param engine
         */
        public Commands(Engine engine)
        {
            commands = new HashMap<String,Command>();
            initializeCommands(engine);
        }
        /**
         * Used by Constructor to create new CommandObjects, these are binded to a specific command in the HashMap commands.
         * @param engine
         */
        private void initializeCommands(Engine engine)
        {
            commands.put(strings.Move,new CommandMove(engine));
            commands.put(strings.Attack,new CommandAttack(engine));
            commands.put("pause",new CommandPauseToggle(engine));
            commands.put("save", new CommandSave(engine));
            commands.put("load", new CommandLoad(engine));
        }
        /**
         * Returns all the commands we have.
         * @return HashMap<String,Command>
         */
        public HashMap<String,Command> getAllCommands()
        {
            return commands;
        }
        /**
         * Returns an string containing all commands.
         * @return String of commands
         */
        public String showAll()
        {
            String tmp = new String();
            for( String key : commands.keySet() ) {
            	tmp += " " + key;
            }
            return tmp;
        }
        
       /**
        * Returns true if the key has an command.
        * @param key the command we want to check if it exists
        * @return true if key in use
        */
       public boolean isCommand(String key)
       {
           return commands.containsKey(key);
       }
       /**
        * Return's the command that is linked to the key.
        * @return Command
        * @throws IllegalArgumentException
        */
       public Command getCommand(String key) throws IllegalArgumentException
       {
           if( !isCommand(key))
        	   throw new IllegalArgumentException("The key: " + key + " is not a valid command in Commands.java");
           return commands.get(key);
       }
    }

