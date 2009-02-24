package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @file Maps.java
 * @version 0.3
 * @author Josef Johansson, Teddie Heikkinen
 *
 */
public class Maps {
    private TreeMap<String,MapPiece> map;
    public Maps()
    {
    	map = new TreeMap<String,MapPiece>();
    }
    /**
     * Checks if a spot on the map is free to walk on
     * @param v a field of ints {z,x,y}
     * @return true if it's walkable, false if not
     */
    public boolean checkSpot(int [] v)
    {
	return true;
	/*
        MapPiece piece = map.get((String)(v[0]+"-"+v[1]+"-"+v[2]));
        if (piece != null)
        {
            if (piece.isWalkable())
            {
                return true;
            }
        }
        return false;*/
    }
    /**
     * loads a map into the program
     * @param String
     * @return boolean true if succes false if not
     * @throws IOException
     */
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
			MapPiece piece = new MapPiece(x,y,z,name,walkable);
                    map.put(piece.toString(),piece);
                    isLoaded = true;
                }
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
    public MapPiece getMapPiece(int[] v)
    {
        return map.get(generateKey(v[0],v[1],v[2]));
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
}

 
