package main;

import gui.Gui;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * @file Main.java
 * @version 0.2
 * @author Raul Bache
 */
public class Main
{
    /**
     * Constructor for objects of class Main
     */
    public static void main(String [] args )
    {
    	HashMap<String,ArrayList<String>> anim = new HashMap<String,ArrayList<String>>();
    	anim.put("north", new ArrayList<String>());
    	anim.put("south", new ArrayList<String>());
    	anim.put("east", new ArrayList<String>());
    	anim.put("west", new ArrayList<String>());
    	
    	anim.get("north").add("bin/images/warrior/walk/north/nn00.gif");
    	anim.get("north").add("bin/images/warrior/walk/north/nn01.gif");
    	anim.get("north").add("bin/images/warrior/walk/north/nn02.gif");
    	anim.get("north").add("bin/images/warrior/walk/north/nn03.gif");
    	anim.get("north").add("bin/images/warrior/walk/north/nn04.gif");
    	anim.get("north").add("bin/images/warrior/walk/north/nn05.gif");
    	anim.get("north").add("bin/images/warrior/walk/north/nn06.gif");
    	anim.get("north").add("bin/images/warrior/walk/north/nn07.gif");
    	
    	anim.get("south").add("bin/images/warrior/walk/south/ss00.gif");
    	anim.get("south").add("bin/images/warrior/walk/south/ss01.gif");
    	anim.get("south").add("bin/images/warrior/walk/south/ss02.gif");
    	anim.get("south").add("bin/images/warrior/walk/south/ss03.gif");
    	anim.get("south").add("bin/images/warrior/walk/south/ss04.gif");
    	anim.get("south").add("bin/images/warrior/walk/south/ss05.gif");
    	anim.get("south").add("bin/images/warrior/walk/south/ss06.gif");
    	anim.get("south").add("bin/images/warrior/walk/south/ss07.gif");
    	
    	anim.get("east").add("bin/images/warrior/walk/east/ee00.gif");
    	anim.get("east").add("bin/images/warrior/walk/east/ee01.gif");
    	anim.get("east").add("bin/images/warrior/walk/east/ee02.gif");
    	anim.get("east").add("bin/images/warrior/walk/east/ee03.gif");
    	anim.get("east").add("bin/images/warrior/walk/east/ee04.gif");
    	anim.get("east").add("bin/images/warrior/walk/east/ee05.gif");
    	anim.get("east").add("bin/images/warrior/walk/east/ee06.gif");
    	anim.get("east").add("bin/images/warrior/walk/east/ee07.gif");
    	
    	anim.get("west").add("bin/images/warrior/walk/west/ww00.gif");
    	anim.get("west").add("bin/images/warrior/walk/west/ww01.gif");
    	anim.get("west").add("bin/images/warrior/walk/west/ww02.gif");
    	anim.get("west").add("bin/images/warrior/walk/west/ww03.gif");
    	anim.get("west").add("bin/images/warrior/walk/west/ww04.gif");
    	anim.get("west").add("bin/images/warrior/walk/west/ww05.gif");
    	anim.get("west").add("bin/images/warrior/walk/west/ww06.gif");
    	anim.get("west").add("bin/images/warrior/walk/west/ww07.gif");
    	
    	new Gui();
    }
}