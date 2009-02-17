package gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * TranspContainer är en komponent som innehåller en buffrad bild med
 * genomskinlig bakgrund och dess grafikkontext (den grafik man kan skapa från
 * bilden). RÖR EJ I DENNA KLASSEN UTAN ATT TÄNKA EFTER FÖRST!!!!!!!!!!!!!!
 * 
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
	 * Constructor for objects of class TranspContainer sätt storlek för
	 * container (Arean vi vill kunna rita på)
	 */
	public TranspContainer(int height, int width) {
		this.height = height;
		this.width = width;
		this.setLayout(null);
		setSize(this.height, this.width);
	}

	/**
	 * Överskuggar paint i superklass, det är denna metod som anropas varje gång
	 * vi tar (instans av denna klass).repaint()
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bimage, 0, 0, null);
	}

	/**
	 * initierar vår container med specifik höjd/bredd container blir en
	 * genomskinlig bild. Vi måste initiera vår container efter frame.pack()
	 * ingen aning varför, men om vi inte gör det efter så skapas ingen grafik
	 * från bilden.
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
	 * Returnera grafikkontexten för det containern innehåller Alltså det vi
	 * vill rita på.
	 */
	public Graphics2D getGraphics() {
		return graphics;
	}

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
			//setLocation(new Point(20, 20));
		} catch (IOException e) {
			System.out.println("Image not found IOEXCEPTION \n");
			System.out.println( e.getMessage());
			e.printStackTrace();
			wrongtangle = new Rectangle(0, 0, 20, 20);
			graphic.setColor(Color.BLACK);
			graphic.fill(wrongtangle);
			graphic.draw(wrongtangle);
		}
		
		repaint();
		graphic.drawImage(currentimage, 0, 0, null);

	}

}
