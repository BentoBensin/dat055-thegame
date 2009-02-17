package game;

/**
 * Write a description of class IDGen here.
 *
 * @author Gammel Teddie
 * @version Gammel version
 */

//TODO KOMMENTERA!!!!!!!
public class IDGen
{
   private static IDGen instance = new IDGen();
   private static int idCount = 1;

    /**
     * Constructor for objects of class IDGen
     */
    private IDGen()
    {
       idCount = 1;
    }
   
    public IDGen getInstance(){
        return instance;
        }
   
    public static int generateID(){
        return idCount++;
    }
   
    public int numberOfIDs(){
        return idCount;
    }

}