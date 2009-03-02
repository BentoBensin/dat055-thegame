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

/**
 * Constructor for objects of class MapPiece
 */
public class MapPiece
{
    private Point point;
    private String objectName;
    private boolean walkable;
    private int z;
    protected static int xsize = 40;
    protected static int ysize = 40;
 
    public MapPiece(int x, int y, int z, String obstacle, boolean walk)
    {
       this.z = z;
       this.point = new Point(x,y);
       this.objectName = obstacle;
       this.walkable = walk;    
    }
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
   
    public int getZ(){
        return z;
    }
    public Point getPoint(){
       return point;
    }
   
    public String getObjName(){
        return objectName;
    }
   
    public boolean isWalkable(){
        return walkable;
    }
   
    public String toString(){
    	 int x = (int)point.getX();
    	 int y = (int)point.getY();
    	 
    	 return (String)(z+"-"+x+"-"+y);
    }
       
       
   
    public Object clone(){
        return new MapPiece(this);
    }
}