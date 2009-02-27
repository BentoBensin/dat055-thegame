package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * TranspContainer is a graphical component that contains a BufferedImage
 * with a transparent background. 
 * @file TranspContainer.java
 * @version 0.6
 * @author Robban
 */
public class TranspContainer extends JPanel {
	static final long serialVersionUID = 2233333;
	private BufferedImage bimage;
	private BufferedImage currentimage;
	private Graphics2D graphics;
	private int height, width;
	private Rectangle wrongtangle;

	/**
	 * Constructor for objects of class TranspContainer 
	 * @param int height - the height of the TranspContainer
	 * @param int windth - the width of the TranspContainer	 
	 */
	public TranspContainer(int height, int width) {
		this.height = height;
		this.width = width;
		this.setLayout(null);
		initContainer();
		setSize(this.height, this.width);
	}

	/**
	 * Overrides paint in superclass, its this method that is invoked everytime
	 * we repaint our TranspContainer
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bimage, 0, 0, null);
	}

	/**
	 * Initiates our TranspContainer with a specific height/width and creates
	 * the transparant alphacomposite.
	 */
	public void initContainer() {
		// Create image with RGB and alpha channel
		this.bimage = new BufferedImage(height, width,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = this.bimage.createGraphics(); // Context for buffered
														// image
		// plus the rest of the method...
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR,0.0f));
		Rectangle rect = new Rectangle(0, 0, height, width);
		g2d.fill(rect);
		graphics = (Graphics2D) this.bimage.getGraphics();
	}

	/**
	 * Returns the current graphiccontext for the container (which is used to draw on).
	 * @return Graphics2D - This containers graphics.
	 */
	public Graphics2D getGraphics() {
		return graphics;
	}

	/**
	 * Returns a new graphiccontext for the container (which is used to draw on).
	 * @return Graphics2D - new transparant graphics.
	 */
	public Graphics2D getNewGraphics() {
		// Create image with RGB and alpha channel
		this.bimage = new BufferedImage(height, width,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = this.bimage.createGraphics(); // Context for buffered
														// image
		// plus the rest of the method...
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR,0.0f));
		Rectangle rect = new Rectangle(0, 0, height, width);
		g2d.fill(rect);
		return (Graphics2D) this.bimage.getGraphics();
	}
	
	/**
	*Sets the image from a String
	*@param String - the searchpath to the image
	*/
	public void setImage(String imagename) {
		Graphics2D graphic = getNewGraphics();

		try {
			currentimage = ImageIO.read(new File(imagename));
		} catch (NullPointerException e) {
			System.out.println("Image not found\n");
			wrongtangle = new Rectangle(0, 0, 20, 20);
			graphic.setColor(Color.BLACK);
			graphic.fill(wrongtangle);
			graphic.draw(wrongtangle);
		} catch (IOException e) {
			System.out.println("Image not found IOEXCEPTION \n");
			System.out.println( e.getMessage());
			e.printStackTrace();
			
		}
		
		repaint();
		graphic.drawImage(currentimage, 0, 0, null);

	}
	
	/**
	*Sets the image from a BufferedImage
	*@param BufferedImage - the image we want to display
	*/
	public void setImage(BufferedImage bimg) {
		if( bimg != null ){
			Graphics2D graphic = getNewGraphics();
			repaint();
			graphic.drawImage(bimg, 0, 0, null);
		}
	}
}
