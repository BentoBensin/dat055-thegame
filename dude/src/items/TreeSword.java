package items;

import java.io.Serializable;
/**
*Class TreeSword is the default weapon in our 
*game.
*@file TreeSword.java
*@version 1.0
*@author bache
*/
public class TreeSword extends Weapon implements Serializable{
	static final long serialVersionUID = 124999;
 
 /**
   *Constructor for Class TreeSword.
	*Calls superclass with predefined values.
   */
	public TreeSword()
	{
		super(30,100,10,null,500);
	}

}
