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
 * TranspContainer �r en komponent som inneh�ller en buffrad bild med
 * genomskinlig bakgrund och dess grafikkontext (den grafik man kan skapa fr�n
 * bilden). R�R EJ I DENNA KLASSEN UTAN ATT T�NKA EFTER F�RST!!!!!!!!!!!!!!
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
	 * Constructor for objects of class TranspContainer s�tt storlek f�r
	 * container (Arean vi vill kunna rita p�)
	 */
	public TranspContainer(int height, int width) {
		this.height = height;
		this.width = width;
		this.setLayout(null);
		setSize(this.height, this.width);
	}

	/**
	 * �verskuggar paint i superklass, det �r denna metod som anropas varje g�ng
	 * vi tar (instans av denna klass).repaint()
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(bimage, 0, 0, null);
	}

	/**
	 * initierar v�r container med specifik h�jd/bredd container blir en
	 * genomskinlig bild. Vi m�ste initiera v�r container efter frame.pack()
	 * ingen aning varf�r, men om vi inte g�r det efter s� skapas ingen grafik
	 * fr�n bilden.
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
	 * Returnera grafikkontexten f�r det containern inneh�ller Allts� det vi
	 * vill rita p�.
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
