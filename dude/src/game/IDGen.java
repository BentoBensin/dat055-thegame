package game;

/**
 * Generates a unique id-number for every client using the singleton design pattern.
 * 
 * @author Raul
 * @version 1.0
 */

public class IDGen
{
   private static IDGen instance = null;
   private static int idCount = 1;

    /**
     * Constructor for objects of class IDGen
     */
    private IDGen()
    {
    	idCount = 1;
    }
   
    /**
     * 
     * @return IDGen
     */
    public IDGen getInstance(){
    	if (instance==null)
    		instance=new IDGen();
        return instance;
    }
   
    /**
     * Generates a new unique id-number
     * @return ID-number
     */
    public static int generateID(){
        return idCount++;
    }
   
    public int numberOfIDs(){
        return idCount;
    }

}