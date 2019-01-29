package pl.mattcode.chess.figure;

import pl.mattcode.chess.util.ImageCoords;
import pl.mattcode.chess.util.Point;

import java.util.ArrayList;

public class Pawn extends Figure {

    public Pawn(int color, Point position) {
        super(color, position);
    }

    @Override
    public ArrayList<Point> getAvailableMoves() {
        ArrayList<Point> points = new ArrayList<>();
        int modifier = 1;
        if (Figure.COLOR_WHITE == getColor()) {
            modifier = -1;
        }
        points.add(getPosition().moveTo(0, modifier));

        if (!hasMoved()) {
            points.add(getPosition().moveTo(0, 2 * modifier));
        }

        return filterNulls(points);
    }

    @Override
    public Boolean isMoveAvailable(Point p) {
        ArrayList<Point> moves = getAvailableMoves();
        moves.addAll(getSlantMoves());
        return moves.contains(p);
    }

    public ArrayList<Point> getSlantMoves(){
        ArrayList<Point> moves = new ArrayList<>();
        if(this.getColor() == Figure.COLOR_WHITE){
            moves.add(getPosition().moveSE());
            moves.add(getPosition().moveSW());
        }
        else{
            moves.add(getPosition().moveNE());
            moves.add(getPosition().moveNW());
        }
        return moves;
    }

    @Override
    public char getStringIcon() {
        return 'o';
    }

    @Override
    public ImageCoords getSrcImageCoords() {
        return new ImageCoords(1695, 20 + getColorOffset(), 270, 320);
    }
}
