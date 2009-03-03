package gamecharacter;

import java.io.Serializable;

public class MovePattern implements Serializable{
	static final long serialVersionUID = 123999;  
    public int length;
    public String direction;
    
    public MovePattern(int l, String d)
    {
        length=l;
        direction=d;
    }

}
