package gamecharacter;

import java.io.Serializable;
/**
 * This contains information for movement pattern that AI uses to add new movement 
 * @file MovePattern.java
 * @version 0.1
 * @author Mattias
 *
 */
public class MovePattern implements Serializable{
	static final long serialVersionUID = 123999;  
    public int length;
    public String direction;
    
    /**
     * Creates an pattern that are used as movement pattern
     * Takes Length of steps and direction of movement
     * @param length 
     * @param direction
     */
    public MovePattern(int length, String direction)
    {
        this.length=length;
        this.direction=direction;
    }

}
