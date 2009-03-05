package map;


import java.awt.*;
/**
 * This class represents a tile in the map.
 * The tile is represented in x,y and z coordinates.
 * The tile has an object name which is what it represents on the map
 * for instance a tile of grass.
 * it also has a boolean variable that says if you can walk on the tile or not.
 * Each tile in the z-layer 0 has a fixed size 40x40.
 * 
 * @file MapPiece.java
 * @version 0.4
 * @author Robban,Josef,Teddie
 *
 */

public class MapPiece
{
    private Point point;
    private String objectName;
    private boolean walkable;
    private int z;
    protected static int xsize = 40;
    protected static int ysize = 40;
	 
  /**
    *Constructor for objects of class MapPiece
	 *@param int x - the x position of the MapPiece
 	 *@param int y - the y position of the MapPiece
 	 *@param int z - the z position of the MapPiece
	 *@param String obstacle  - the name of the MapPiece (Whats on the mappiece)
	 *@param boolean walk - if you can or cant walk on this mappiece
	 */
    public MapPiece(int x, int y, int z, String obstacle, boolean walk)
    {
       this.z = z;
       this.point = new Point(x,y);
       this.objectName = obstacle;
       this.walkable = walk;    
    }
	 /**
    *Constructor for objects of class MapPiece
	 *@param int x - the x position of the MapPiece
 	 *@param int y - the y position of the MapPiece
 	 *@param int z - the z position of the MapPiece
	 *@param String obstacle  - the name of the MapPiece (Whats on the mappiece)
	 *@param int walk - if you can or cant walk on this mappiece (1/0)
	 */
    public MapPiece(int x, int y, int z, String obstacle, int walk)
    {
       this.z = z;
       this.point = new Point(x,y);
       this.objectName = obstacle;
       if( walk == 1) {
    	   this.walkable = true; 
       }else{
    	   this.walkable = false;
       }
       
    }
    /**
     * Constructor for cloning objects of class MapPiece
     */
    public MapPiece(MapPiece mpiece)
    {
       this.point = (Point)mpiece.getPoint().clone();
       this.objectName = new String(mpiece.getObjName());
       this.walkable = mpiece.isWalkable();
    }
    
    /**
	 *Returns the z-value of this MapPiece
	 *@return int z - the z-value
	 */
    public int getZ(){
        return z;
    }
	 
    /**
	 *Returns the Point of this MapPiece
	 *@return Point point - the point of this mappiece
	 */
    public Point getPoint(){
       return point;
    }
    
    /**
	 *Returns the name/obstacle of this MapPiece
	 *@return String objectname - the objectname of this mappiece
	 */
    public String getObjName(){
        return objectName;
    }
    
    /**
	 *Returns the walkable value of this MapPiece
	 *@return boolean walkable - if the mappiece is walkable or not
	 */
    public boolean isWalkable(){
        return walkable;
    }
    
    /**
	 *Returns the String of this MapPiece
	 *@return String astring - a String that tells the z,x,y value of this MapPiece
	 */
    public String toString(){
    	 int x = (int)point.getX();
    	 int y = (int)point.getY();
    	 
    	 return (String)(z+"-"+x+"-"+y);
    }
       
       
    /**
	 *Returns a clone of this MapPiece
	 *@return MapPiece mappiece - a clone of this MapPiece
	 */
    public Object clone(){
        return new MapPiece(this);
    }
}