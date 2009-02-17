package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @author Josef Johansson, Teddie Heikkinen
 *
 */
//TODO KOMMENTERA!!!!!!!
public class Maps {
    /**
     * will be implemented later
     */
    private TreeMap<String,MapPiece> map;
    public Maps()
    {
    	map = new TreeMap<String,MapPiece>();
    }
    /**
     *
     * @param z
     * @param point
     * @return
     */
    public boolean checkSpot(int [] v)
    {
        MapPiece piece = map.get((String)(v[0]+"-"+v[1]+"-"+v[2]));
        if (piece != null)
        {
            if (piece.isWalkable())
            {
                return true;
            }
        }
        return false;
    }
   
    public boolean loadMap(String path) throws IOException
    {
        boolean isLoaded = false;
        boolean isFail = false;
        String s;
        int l;
        //MapPiece piece;
        File f = new File( path , "map.csv");
       	BufferedReader r = new BufferedReader(new FileReader(f));
        while ( (s = r.readLine() ) != null )
        {
            switch (s.trim().charAt(0))
            {
            case '#':continue;
            case '0':/*background*/
                l = 0;
                break;
            case '1':/*item*/
                l = 1;
                break;
            default:continue;
            }
            if ((l == 0 || l == 1))
            {
                String split[] = s.split("([\\s]+)?,([\\s]+)?");
                int z = 0,
                	x = 0,y = 0 ,walkable = 1;
                String name = "";
                for (int i=0;i<split.length;i++)
                {
                    switch(i)
                    {
                    case 0:
                        try {
                            z = Integer.parseInt(split[i]);
                        }
                        catch (Exception e) {
                            isFail = true;
                        }
                        break;
                    case 1:
                        try {
                            x = Integer.parseInt(split[i]);
                        } catch (Exception e) {
                            isFail = true;
                        }
                        break;
                    case 2:
                        try {
                            y = Integer.parseInt(split[i]);
                        } catch (Exception e) {
                            isFail = true;
                        }
                        break;
                    case 3:
                        name = split[i];
                        break;
                    case 4:
                        try {
                            walkable = Integer.parseInt(split[i]);
                        } catch (Exception e) {
                            isFail = true;
                        }
                    }
                }
                if ( !isFail && name.length()>0 && map.containsKey(generateKey(z,y,x)))
                {
                    map.put((String)(z+"-"+x+"-"+y),new MapPiece(x,y,z,name,walkable));
                    isLoaded = true;
                }
            }
        }
        return isLoaded;
    }
    /**
     *
     * @param map
     * @return
     */
    public TreeMap<String,MapPiece> getMap()
    {
        return map;
    }
    /**
     *
     * @param v
     * @return
     */
    public MapPiece getMapPiece(int[] v)
    {
        return map.get(generateKey(v[0],v[1],v[2]));
    }
    private String generateKey(int z,int x,int y)
    {
        return (String)(z+"-"+x+"-"+y);
    }
}

 