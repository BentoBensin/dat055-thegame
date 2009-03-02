/**
 * 
 */
package gamecharacter;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 * Handles the images in The Game
 * this class is a singleton
 * typical implementation is
 * GameAnimationData images = GameAnimationData.getInstance();
 * BufferedImage image;
 * if(images.size(skin,action,direction) > index)
 * 	image = images.get(skin,action,direction,index);
 * else image = images.get(skin,action,direction, (index=0) );
 * 
 * @file GameAnimationData.java
 * @version 0.1
 * @revision 64
 * @author josjoh
 *
 */
public class GameAnimationData {
	private static GameAnimationData instance = null;
	private HashMap<String,Object> image;
	private static final String imageDirectory = "images/";
	private GameAnimationData()
	{
		image = new HashMap<String,Object>();
    	addImageSet("shroomsman/walk/north/nn","gif",8);
    	addImageSet("shroomsman/walk/south/ss","gif",8);
    	addImageSet("shroomsman/walk/east/ee","gif",8);
    	addImageSet("shroomsman/walk/west/ww","gif",8);
    	
    	addImageSet("shroomsman/attack/north/nn","gif",11);
    	addImageSet("shroomsman/attack/south/ss","gif",11);
    	addImageSet("shroomsman/attack/east/ee","gif",11);
    	addImageSet("shroomsman/attack/west/ww","gif",11);
    	
    	addImageSet("shroomsman/die/north/nn","gif",7);
    	addImageSet("shroomsman/die/south/ss","gif",7);
    	addImageSet("shroomsman/die/east/ee","gif",7);
    	addImageSet("shroomsman/die/west/ww","gif",7);
    	
    	addImageSet("shroomsman/hit/north/nn","gif",9);
    	addImageSet("shroomsman/hit/south/ss","gif",9);
    	addImageSet("shroomsman/hit/east/ee","gif",9);
    	addImageSet("shroomsman/hit/west/ww","gif",9);
    	
    	addImageSet("shroomsman/still/north/nn","gif",1);
    	addImageSet("shroomsman/still/south/ss","gif",1);
    	addImageSet("shroomsman/still/east/ee","gif",1);
    	addImageSet("shroomsman/still/west/ww","gif",1);
    	
    	addImageSet("warrior/walk/north/nn","gif",8);
    	addImageSet("warrior/walk/south/ss","gif",8);
    	addImageSet("warrior/walk/east/ee","gif",8);
    	addImageSet("warrior/walk/west/ww","gif",8);
    	
    	addImageSet("warrior/attack/north/nn","gif",12);
    	addImageSet("warrior/attack/south/ss","gif",12);
    	addImageSet("warrior/attack/east/ee","gif",12);
    	addImageSet("warrior/attack/west/ww","gif",12);
    	
    	addImageSet("warrior/die/north/nn","gif",12);
    	addImageSet("warrior/die/south/ss","gif",12);
    	addImageSet("warrior/die/east/ee","gif",12);
    	addImageSet("warrior/die/west/ww","gif",12);
    	
    	addImageSet("warrior/hit/north/nn","gif",8);
    	addImageSet("warrior/hit/south/ss","gif",8);
    	addImageSet("warrior/hit/east/ee","gif",8);
    	addImageSet("warrior/hit/west/ww","gif",8);
    	
    	addImageSet("warrior/still/north/nn","gif",1);
    	addImageSet("warrior/still/south/ss","gif",1);
    	addImageSet("warrior/still/east/ee","gif",1);
    	addImageSet("warrior/still/west/ww","gif",1);
	}
	public static GameAnimationData getInstance()
	{
		if( instance == null) instance = new GameAnimationData();
		return instance;
	}
	private BufferedImage whitePicture()
	{
		  int width = 100; // Dimensions of the image
		  int height = 100;
		  // Let's create a BufferedImage for a binary image.
		  BufferedImage im = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_BINARY);
		  // We need its raster to set the pixels' values.
		  WritableRaster raster = im.getRaster();
		  // Put the pixels on the raster. Note that only values 0 and 1 are used for the pixels.
		  // You could even use other values: in this type of image, even values are black and odd
		  // values are white.
		  for(int h=0;h<height;h++)
		  for(int w=0;w<width;w++)
		  raster.setSample(w,h,0,0);
		  return im;
	}
	/**
	 * returns a list of images in our HashMaplist
	 * @param skin
	 * @param action
	 * @param direction
	 * @return The list containing BufferedImages
	 */
	@SuppressWarnings("unchecked")
	private LinkedList<BufferedImage> getImageList(String skin, String action, String direction)
	{
		Object step = null;
		if((step = image.get(skin)) != null)
			if((step instanceof HashMap) && (step = ((HashMap<String,Object>)step).get(action)) != null)
				if((step instanceof HashMap) && (step = ((HashMap<String,Object>)step).get(direction)) != null)
					if( step instanceof LinkedList)
					return (LinkedList<BufferedImage>)step;
		return null;
	}
	/**
	 * Returns the size of the list that is given by the parameters
	 * @param skin
	 * @param action
	 * @param direction
	 * @return size
	 */
	public int size(String skin, String action, String direction){
		LinkedList<BufferedImage> ll = getImageList(skin,action,direction);
		if(ll != null) return ll.size();
		return 0;
	}
	/**
	 * gets the image that is given by the parameters
	 * @param skin
	 * @param action
	 * @param direction
	 * @param index
	 * @return image or whitepicture when the image is not found
	 */
	public BufferedImage getImage(String skin, String action, String direction, int index) {
		LinkedList<BufferedImage> ll = getImageList(skin,action,direction);
		BufferedImage stepImage;
		if( ll != null)
		{
			// doesn't really return null because the image is always found here
			if(ll.size()>index && (stepImage = ll.get(index)) != null)	
				return stepImage;
		}
		return whitePicture();
	}
	/**
	 * Adds images to our set, uses defaultImagePath
	 * path should be "skin/action/direction/dd"
	 * filetype accepted is jpg and gif
	 * num should be the number of images in the set
	 * path that is read is of the given parameters ("skin/action/direction/dd","gif",1)
	 * "images/skin/action/direction/dd00.gif"
	 * @param path
	 * @param filetype
	 * @param num
	 */
	@SuppressWarnings("unchecked")
	public void addImageSet(String path,String filetype,int num)
	{
		// sanitycheck
		if( !( 
				(num>0) 
				&&	(path.length()>0) 
				&&	( 
						(filetype.equals("gif")) 
						|| (filetype.equals("jpg"))
				)
			) 
		)
			return;
		HashMap<String,Object> stepStone = image;
		String splitPath[] = path.split("/");
		LinkedList<String> ll = new LinkedList<String>();
		for (String part:splitPath)
			ll.addLast(part);
		String fileName = ll.removeLast();
		String imageDirection = ll.removeLast();
		// shroomsman/walk/south/ss
		for(String part:ll)
		{
			//if(i+1==splitPath.length) stepImage = stepStone.put((Object)new ArrayList<BufferedImage>());
			stepStone.put(part,new HashMap<String,Object>());
			stepStone = (HashMap<String,Object>)stepStone.get(part);
		}
		LinkedList<BufferedImage> stepImage = new LinkedList<BufferedImage>();
		stepStone.put(imageDirection,stepImage);
		String fileNum = new String();
		for(int i = 0;i<num;i++)
		{
			if(i<10) fileNum = "0"+i;
			else fileNum = ""+i;
			try {
				stepImage.add( ImageIO.read(new File(imageDirectory+path+fileNum+"."+filetype)) );
			} catch (NullPointerException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
