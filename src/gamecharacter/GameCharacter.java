package gamecharacter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

/**
 * This class contains information such as speed, height and width, about a character.
 * @file GameCharacter.java
 * @version 0.3
 * @author Teddie
 *
 */
public abstract class GameCharacter extends TimerTask {
	
	private HashMap<String,HashMap<String, ArrayList<BufferedImage>>> image;
	private LinkedList<MovePattern> patterns;
	private double speed;
	private int width;
	private int height;
	private int attackRange;   
	private Timer stunTimer;
	private boolean isStunned;
	private Item weapon;
	
	public GameCharacter(Item weapon, double speed, int width, int height, int health){
		image = new HashMap<String,HashMap<String, ArrayList<BufferedImage>>>();
		patterns = new LinkedList<MovePattern>();
		this.weapon = weapon;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.attackRange = 20;
		stunTimer = new Timer();
		isStunned = false;
	}
	
	/**
	 * Loads new images.
	 * @param HashMap containing images.
	 */
	public void loadImages(HashMap<String,HashMap<String, ArrayList<String>>> images){		
		if( images == null)
			throw new NullPointerException();
		for(String action: images.keySet()){
			System.out.println(action);
			image.put(action, new HashMap<String, ArrayList<BufferedImage>>());
			for(String direction: images.get(action).keySet()){
				image.get(action).put(direction, new ArrayList<BufferedImage>());
				for(int i=0; i<images.get(action).get(direction).size(); i++){
					try {
						image.get(action).get(direction).add( ImageIO.read(new File(images.get(action).get(direction).get(i))));
					} catch (NullPointerException e) {
						System.out.println("NullPointer found in GameCharacter.loadImages() while loading image:" + images.get(action).get(direction).get(i));
						e.printStackTrace();
					}catch( IOException e) {
						System.out.println("IOException found in GameCharacter.loadImages() while loading image:" + images.get(action).get(direction).get(i));
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * Gets an action, a direction and an index and returns next image in sequence.
	 * @param action
	 * @param direction
	 * @param index
	 * @return Next image in sequence.
	 */
	public BufferedImage getNextImage(String action, String direction, int index){
		if(action == null && direction == null){
			System.out.println("NullPointer in getNextImage trying to get the i: " + index);
			throw new NullPointerException();
		}
		
		if( (index < 0) || (index > image.get(action).get(direction).size())){
			throw new IllegalArgumentException();
		}
		
		return image.get(action).get(direction).get(index);
	}
	
	public int getAnimationLength(String action, String direction){
		if(action == null && direction == null){
			throw new NullPointerException();
		}
		return image.get(action).get(direction).size();
		
	}
	
	/**
	 * Finds next move pattern.
	 * @param index
	 * @return Next move pattern.
	 */
	public MovePattern getPattern(int index){
		
		if((index < 0) || !(index < patterns.size())){
			throw new IllegalArgumentException();
		}
		
		return patterns.get(index);
	}

	/**
	 * Adds a new pattern to pattern list.
	 * @param pattern
	 */
	public void addPattern(MovePattern pattern){
		if(pattern == null){
			throw new NullPointerException();
		}
		patterns.add(pattern);
	}
	
	/**
	 * Adds an stunn to the GameCharacter
	 * param is the stunn time in miliseconds
	 * @param time
	 */
	public void stunn( int time) {
		isStunned = true;
		stunTimer.schedule(this, time, time);
	}
	/**
	 * Checks if the GameCharacter is stunned
	 * @return true if stunned
	 */
	public boolean isStunned() {
		return isStunned;
	}
	
	/**
	 * Is called when the stunnTimer is done
	 * removes stunn and cancels the timer
	 */
	public void run() {
		isStunned = false;
		stunTimer.cancel();
	}
	
	/**
	 * Returns number of patterns
	 * @return Number of patterns.
	 */
	public int numberOfPatterns(){
		return patterns.size();
	}
	
	/**
	 * Returns speed of character.
	 * @return Speed of character.
	 */
	public double getSpeed(){
		return speed;
	}
	
	/**
	 * Returns width of character.
	 * @return Width of character.
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Returns height of character.
	 * @return Height of character.
	 */
	public int getHeight(){
		return height;
	}
	
	public Item getPrimary() {
		return weapon;
	}
	
	/**
	 * Returns the attackrange for the specifik character
	 * @return distance
	 */
	public int getAttackRange()
	{
		return attackRange;
	}
	
}
