package pl.mattcode.chess.figure;

import pl.mattcode.chess.Board;
import pl.mattcode.chess.util.ImageCoords;
import pl.mattcode.chess.util.Point;

import java.util.ArrayList;

public class Rook extends Figure{

    public Rook(int color, Point position) {
        super(color, position);
    }

    @Override
    public ArrayList<Point> getAvailableMoves() {
        ArrayList<Point> points = new ArrayList<>();

        for(int i=1;i< Board.BOARD_SIZE;i++){
            points.add(getPosition().moveN(i));
            points.add(getPosition().moveE(i));
            points.add(getPosition().moveS(i));
            points.add(getPosition().moveW(i));
        }


        return filterNulls(points);
    }

    @Override
    public char getStringIcon() {
        return 'R';
    }

    @Override
    public ImageCoords getSrcImageCoords() {
        return new ImageCoords(1355, 20 + getColorOffset(), 290, 320);
    }
}
