package command;

import game.Engine;

import java.util.HashMap;


/**
 * @author Josef Johansson
 *
 */

//TODO KOMMENTERA!!!!!!!
public class Commands
    {
        /**
         * This variable contains our commands
         */
		public static final int FORWARD = 0;
		public static final int BACK = 0;
		public static final int STRAFELEFT = 0;
		public static final int STRAFERIGHT = 0;
		
		public static final String NORTH = "north";
		public static final String SOUTH = "south";
		public static final String EAST = "east";
		public static final String WEST = "west";
		
		public static enum DIRECTION  { NORTH, SOUTH, EAST, WEST };
	
        private HashMap<String,Command> commands;
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
            commands.put("move",new CommandMove(engine));
  
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
        * @return true if key in use
        */
       public boolean isCommand(String key)
       {
           return commands.containsKey(key);
       }
       /**
        * Return's the command that is linked to the key.
        * @return Command
        */
       public Command getCommand(String key) throws IllegalArgumentException
       {
           if( !isCommand(key))
        	   throw new IllegalArgumentException("The key: " + key + " is not a valid command in Commands.java");
           return commands.get(key);
       }
    }

