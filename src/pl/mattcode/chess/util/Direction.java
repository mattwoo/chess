package pl.mattcode.chess.util;

public class Direction {

    public static final String N = "n";
    public static final String S = "s";
    public static final String W = "w";
    public static final String E = "e";
    public static final String NONE = "none";

    public static String determine(Point origin, Point destination){
        if(origin.getY() == destination.getY() && origin.getX()== destination.getX()){
            return Direction.NONE;
        }
        return null;
    }

}
