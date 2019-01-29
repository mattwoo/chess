package pl.mattcode.chess.figure;

import pl.mattcode.chess.util.Point;

import java.util.ArrayList;

abstract public class Figure implements FigureInterface {

    public static final int COLOR_WHITE = 1;
    public static final int COLOR_BLACK = 2;

    private final int color;
    private Point position;
    private Boolean hasMoved = false;

    Figure(int color, Point position) {
        this.color = color;
        this.position = position;
    }

    public void moveTo(Point point) {
        if (!isMoveAvailable(point)) {
            throw new RuntimeException(
                    String.format("Cannot move figure of class %s from %s to %s - invalid move.",
                            this.getClass().toString(),
                            getPosition().toString(),
                            point.toString()
                    )
            );
        }
        hasMoved = true;
        position = point;
    }

    ArrayList<Point> filterNulls(ArrayList<Point> points) {
        ArrayList<Point> out = new ArrayList<>();
        for(Point p : points){
            if(null != p){
                out.add(p);
            }
        }

        return out;
    }

    public int getColor() {
        return color;
    }

    int getColorOffset(){
        return getColor() == Figure.COLOR_BLACK ? 330 : 0;
    }

    @Override
    public Boolean canJumpOver() {
        return false;
    }

    public Boolean isMoveAvailable(Point p){
        return getAvailableMoves().contains(p);
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public Boolean hasMoved() {
        return this.hasMoved;
    }
}
