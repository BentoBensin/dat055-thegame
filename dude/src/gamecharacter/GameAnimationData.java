/**
 * 
 */
package gamecharacter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import main.Strings;
import javax.imageio.ImageIO;


/**
 * @author josjoh
 *
 */
public abstract class GameAnimationData {
	private HashMap<String,HashMap<String, ArrayList<BufferedImage>>> image;
	
	public GameAnimationData() {
		image = new HashMap<String,HashMap<String, ArrayList<BufferedImage>>>();
	}
	
	public abstract void initImages();
	/**
	 * Gets an action, a direction and an index and returns next image in sequence.
	 * @param action
	 * @param direction
	 * @param index
	 * @return Next image in sequence.
	 */
	public BufferedImage getNextImage(String action, String direction, int index) {
		action = (action != null)?action:Strings.Still;
		direction = (direction != null)?direction:Strings.North; 
		System.out.println("Storlek: " + image.size() );
		if (image.get(action) == null 
		|| image.get(action).get(direction) == null ){
			if(Strings.Debug) System.out.println("NullPointer in getNextImage trying to get the i: " + index + " action: " + action + " direction " + direction);
			return null;
		}
		
		if( (index < 0) || (index > image.get(action).get(direction).size())){
			throw new IllegalArgumentException();
		}
		
		return image.get(action).get(direction).get(index);
	}
	
	public int getAnimationLength(String action, String direction){
		if( image.get(action) != null && image.get(action).get(direction) != null ) {
			if(Strings.Debug) System.out.println("Frsker hmta action : " + action + " direction: " + direction);
			if(Strings.Debug) System.out.println("finns action? : " + image.containsKey(action) + " och direction? " + image.get(action).containsKey(direction) );
			return image.get(action).get(direction).size();
		}
		return 1;
	}
	public int getNextIndex(String action, String direction, int index)
	{
		ArrayList<BufferedImage> step2;
		if ( image.get(action) != null 
		&& (step2 = image.get(action).get(direction)) != null 
		&& image.get(action).get(direction).get(index) != null ) {
			index++;
			if (step2.get(index) != null) return index;
		}
		return 0;
	}

	public void loadImages(HashMap<String,HashMap<String, ArrayList<String>>> images) throws NullPointerException {		
		if( images == null)
			throw new NullPointerException();
		for(String action: images.keySet()){
			if(Strings.Debug) System.out.println(action);
			image.put(action, new HashMap<String, ArrayList<BufferedImage>>());
			for(String direction: images.get(action).keySet()){
				image.get(action).put(direction, new ArrayList<BufferedImage>());
				for(int i=0; i<images.get(action).get(direction).size(); i++){
					try {
						image.get(action).get(direction).add( ImageIO.read(new File(images.get(action).get(direction).get(i))));
					} catch (NullPointerException e) {
						if(Strings.Debug) System.out.println("NullPointer found in GameCharacter.loadImages() while loading image:" + images.get(action).get(direction).get(i));
						if(Strings.Debug) e.printStackTrace();
					}catch( IOException e) {
						if(Strings.Debug) System.out.println("IOException found in GameCharacter.loadImages() while loading image:" + images.get(action).get(direction).get(i));
						if(Strings.Debug) e.printStackTrace();
					}
				}
			}
		}
	}
}
