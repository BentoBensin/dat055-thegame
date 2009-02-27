/**
 * 
 */
package main;

import gamecharacter.GameCharacter;
import java.util.LinkedList;
/**
 * A class that wraps up a LinkedList
 * @file Envelope.java
 * @version 0.1
 * @author dude
 *
 */
public class Envelope {
	private LinkedList<GameCharacter> l;
	public Envelope(LinkedList<GameCharacter> l)
	{
		this.l = l;
	}
	public LinkedList<GameCharacter> getGuiLinkedList()
	{
		return l;
	}
}
