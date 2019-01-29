package pl.mattcode.chess.figure;

import pl.mattcode.chess.util.ImageCoords;
import pl.mattcode.chess.util.Point;

import java.util.ArrayList;

public class Knight extends Figure {

    public Knight(int color, Point position) {
        super(color, position);
    }

    @Override
    public ArrayList<Point> getAvailableMoves() {
        ArrayList<Point> points = new ArrayList<>();

        points.add(getPosition().moveTo(1,2));
        points.add(getPosition().moveTo(2,1));

        points.add(getPosition().moveTo(-1,2));
        points.add(getPosition().moveTo(-2,1));

        points.add(getPosition().moveTo(1,-2));
        points.add(getPosition().moveTo(2,-1));

        points.add(getPosition().moveTo(-1,-2));
        points.add(getPosition().moveTo(-2,-1));

        return filterNulls(points);
    }

    @Override
    public char getStringIcon() {
        return 'K';
    }

    @Override
    public ImageCoords getSrcImageCoords() {
        return new ImageCoords(1025, 20 + getColorOffset(), 290, 320);
    }

    @Override
    public Boolean canJumpOver() {
        return true;
    }
}
