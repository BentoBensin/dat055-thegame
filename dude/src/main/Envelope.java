package main;

import gamecharacter.GameCharacter;

import java.util.ArrayList;
/**
 * A class that wraps up a LinkedList
 * @file Envelope.java
 * @version 0.1
 * @author dude
 *
 */
public class Envelope {
	private ArrayList<GameCharacter> l;
	public Envelope(ArrayList<GameCharacter> l)
	{
		this.l = l;
	}
	/**
	 * 
	 * @return ArrayList of GameCharacters
	 */
	public ArrayList<GameCharacter> getGuiLinkedList()
	{
		return l;
	}
}
