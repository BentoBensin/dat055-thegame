package map;

/**
 * Write a description of class MapPiece here.
 *
 * @author Gammel Teddie
 * @version gammel version kanske`?
 */
import java.awt.*;


//TODO KOMMENTERA!!!!!!!
public class MapPiece
{
    private Point point;
    private String objectName;
    private boolean walkable;
    private int z;
    private Dimension size;
    public static int xsize = 40;
    public static int ysize = 40;
    /**
     * Constructor for objects of class MapPiece
     */
    public MapPiece(int x, int y, int z, String obstacle, boolean walk)
    {
       this.z = z;
       this.point = new Point(x,y);
       this.objectName = obstacle;
       this.walkable = walk; 
       if(z == 0)
    	   this.size = new Dimension(xsize,ysize);
       else
    	   this.size = new Dimension(0,0);
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