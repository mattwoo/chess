package pl.mattcode.chess.figure;

import pl.mattcode.chess.Board;
import pl.mattcode.chess.util.ImageCoords;
import pl.mattcode.chess.util.Point;

import java.util.ArrayList;

public class Bishop extends Figure {

    public Bishop(int color, Point position) {
        super(color, position);
    }

    @Override
    public ArrayList<Point> getAvailableMoves() {
        ArrayList<Point> points = new ArrayList<>();

        for(int i=1;i< Board.BOARD_SIZE;i++){
            points.add(getPosition().moveNW(i));
            points.add(getPosition().moveNE(i));
            points.add(getPosition().moveSW(i));
            points.add(getPosition().moveSE(i));
        }

        return filterNulls(points);
    }

    @Override
    public char getStringIcon() {
        return 'B';
    }

    @Override
    public ImageCoords getSrcImageCoords() {
        return new ImageCoords(690, 20 + getColorOffset(), 290, 320);
    }
}
