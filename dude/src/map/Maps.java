package map;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

import javax.imageio.ImageIO;

/**
 * The Maps class has a number of tiles
 * @file Maps.java
 * @version 0.3
 * @author Josef Johansson, Teddie Heikkinen, Roberth Allevik
 *
 */
public class Maps {
    private TreeMap<String,MapPiece> map;
    private BufferedImage grass;
    private BufferedImage black;
    private BufferedImage tree;
    private boolean isLoaded;
    private static Maps maps;
    /**
     * Constructor for objects of class Maps
     * Creates a TreeMap for (String Object name) - (MapPiece the tile)
     * Reading some tile-images
     */
    private Maps()
    {
    	map = new TreeMap<String,MapPiece>();
    	isLoaded = false;
    	try { grass = ImageIO.read(new File("mapimages/grass.gif"));
        } catch (NullPointerException e) { 
          e.printStackTrace();
          }
            catch (IOException e) {
            e.printStackTrace();
            }
            
        try { black = ImageIO.read(new File("mapimages/black.gif"));
        } catch (NullPointerException e) { 
          e.printStackTrace();
          }
            catch (IOException e) {
            e.printStackTrace();
            }
        
         try { tree = ImageIO.read(new File("mapimages/tree.gif"));
         } catch (NullPointerException e) { 
           e.printStackTrace();
           }
             catch (IOException e) {
             e.printStackTrace();
             }
    }
    public static Maps getInstance()
    {
    	if(maps == null){
    		maps = new Maps();
    	}
    	return maps;
    }
    /**
     * Checks if a spot on the map is free to walk on
     * @param int z
     * @param int x
     * @param int y
     * @return true if it's walkable, false if not
     */
    public boolean checkSpot(int z,int x,int y)
    {
    	//Converting from world coordinates to tile coordinates
    	int xpos = (x+440)/MapPiece.xsize;
    	int ypos = (y+340)/MapPiece.ysize;
    	//Getting the tile at window position (0,0) by truncating the x/y positions
        	// xrest and yrest variables are needed for offsetting the tile images correctly
    	
    	//String temp = (String)(z+"-"+xpos+"-"+ypos);
    	//System.out.println("\n"+temp);
    	MapPiece piece = getMapPiece(z, xpos, ypos);
    	piece.toString();
        //MapPiece piece = map.get((String)(z+"-"+xpos+"-"+ypos));
        if (piece != null)
        {
            if (piece.isWalkable())
            {
                return true;
            }
        }
        return false;
    }
    /**
     * loads a map into the program
     * a file should contain
     * #comment
     * #background
     * 0 1 2 1 tree 1
     * #background x y z Object isWalkAble
     * 1 1 3 1 pot 0
     * #item x y z Object isWalkAble
     * @param String
     * @return boolean true if succes false if not
     * @throws IOException
     */
    public boolean loadMap(String mapname)
    {
        isLoaded = false;
        boolean isFail = false;
        String s;
        String[] split;
        Scanner scanner = null;
 
        //MapPiece piece;
    
        File f = new File( "src/maps/" , mapname);
        try {
            
            //Construct the BufferedReader object
            scanner = new Scanner(new BufferedReader(new FileReader(f)));
            scanner.useDelimiter("\n");

            while (scanner.hasNext()) {
            	s = scanner.next(); 
            	if(s.charAt(0) == '#' || s.charAt(0) == '\r'){
            		continue;
            	}
            	else{
            		split = s.split("\\s");
            		
            		int z = 0,x = 0,y = 0 ,walkable = 1;
            		String objectName = "";
            		for (int i=0;i<split.length;i++)
            		{
            			switch(i)
            			{
            			case 0:
            				try {
            					x = Integer.parseInt(split[i]);
            				}
            				catch (Exception e) {
            					isFail = true;
            				}
            				break;
            			case 1:
            				try {
            					y = Integer.parseInt(split[i]);
            				} catch (Exception e) {
            					isFail = true;
            				}
            				break;
            			case 2:
            				try {
            					z = Integer.parseInt(split[i]);
            				} catch (Exception e) {
            					isFail = true;
            				}
            				break;
            			case 3:
            				objectName = split[i];
            				break;
            			case 4:
            				try {
            					walkable = Integer.parseInt(split[i]);
            				} catch (Exception e) {
            					isFail = true;
            				}
            			}
            		}
            		if ( !isFail && (objectName.length()>0) && !map.containsKey(generateKey(z,x,y)))
                    {
                    	MapPiece piece = new MapPiece(x,y,z,objectName,walkable);
                        map.put(piece.toString(),piece);
                        isLoaded = true;
                    }
            	}
                         
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        
        
        return isLoaded;
    }
    /**
     * returns the current map
     * @return TreeMap<String,MapPiece> The internal Map
     */
    public TreeMap<String,MapPiece> getMap()
    {
        return map;
    }
    /**
     * returns a mappiece at a certain position
     * @param int a field of ints {z,x,y}
     * @return MapPiece
     */
    public MapPiece getMapPiece(int z, int x, int y)
    {
        return map.get(generateKey(z,x,y));
    }
    /**
     * generates the hashkey for our map-treemap
     * @param int z 
     * @param int x 
     * @param int y
     * @return String The hashkey
     */
    private String generateKey(int z,int x,int y)
    {
        return (String)(z+"-"+x+"-"+y);
       
    }
    /**
     * generates a string from our map
     * @return String - that describes our map
     */
    public String toString()
    {
    	String tmp = new String();
    	for(String s : map.keySet() )
    	{
    		System.out.println( map.get(s).toString());
    	}
    	return tmp;
    }
    /**
     * generates the Background for our gui
     * @param Point playerPoint - players current position in worldcoordinates 
     * @return BufferedImage - a backgroundimage drawn based on the players position
     */
    public BufferedImage getCurrentBackground(Point playerPoint)
    {
    	//Converting from world coordinates to tile coordinates
    	Double xpos = playerPoint.getX()/MapPiece.xsize;
    	Double ypos = playerPoint.getY()/MapPiece.ysize;
    	//Getting the tile at window position (0,0) by truncating the x/y positions
    	int xtile = xpos.intValue();
    	int ytile = ypos.intValue();
    	int ztile = 0;
    	// xrest and yrest variables are needed for offsetting the tile images correctly
    	double xrest = xtile - xpos;
    	double yrest = ytile - ypos;
    	
    	
    	///Creating a new BufferedImage and graphics
    	
    	BufferedImage theCurrentBackground;
    	Graphics2D drawGraphics;
    	theCurrentBackground = new BufferedImage(880,680,BufferedImage.TYPE_INT_RGB );
    	drawGraphics = (Graphics2D) theCurrentBackground.createGraphics();
    	
    	BufferedImage tileImage = null;
    	
    	String tmp = "black";
          	
    	for(int y=0; y<17;y++)
    	{
    		
    		for(int x=0; x<22;x++)
    		{
    			try{ tmp = getMapPiece(ztile,(x+xtile),(y+ytile)).getObjName();
    			}catch(NullPointerException e){
    				tmp = "black.gif";
    			}
    			 if( tmp.equals("grass.gif")){
    				 tileImage = grass;
    			 }
    			 else{
    				 tileImage = black;
    			 }
    		//drawGraphics.drawImage(tileImage,((int)(40*xrest)+(40*x)),(int)(40*yrest)+(40*y),null);
    		   drawGraphics.drawImage(tileImage,((int)(MapPiece.xsize*xrest)+(MapPiece.xsize*x)),(int)(MapPiece.ysize*yrest)+(MapPiece.ysize*y),null);
    		}
    	}
    	tileImage = null;
    	ztile++;
    	for(int y=0; y<17;y++)
    	{
    		
    		for(int x=0; x<22;x++)
    		{
    			try{ tmp = getMapPiece(ztile,(x+xtile),(y+ytile)).getObjName();
    			}catch(NullPointerException e){
    				tmp = "notfound";
    			}
    			 if( tmp.equals("tree.gif")){
    				 tileImage = tree;
    				 //drawGraphics.drawImage(tileImage,((int)(40*xrest)+(40*x)),(int)(40*yrest)+(40*y),null);
    				 drawGraphics.drawImage(tileImage,((int)(MapPiece.xsize*xrest)+(MapPiece.xsize*x)),(int)(MapPiece.ysize*yrest)+(MapPiece.ysize*y),null);
    			 }
    			 else{
    				 tileImage = black;
    			 }
    		         		   
    		}
    	}
    	
    	return theCurrentBackground;
    }
}

 
