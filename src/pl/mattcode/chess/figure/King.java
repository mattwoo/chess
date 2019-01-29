package pl.mattcode.chess.figure;

import pl.mattcode.chess.util.ImageCoords;
import pl.mattcode.chess.util.Point;

import java.util.ArrayList;

public class King extends Figure {

    public King(int color, Point position) {
        super(color, position);
    }

    @Override
    public ArrayList<Point> getAvailableMoves() {
        ArrayList<Point> points = new ArrayList<>();
        points.add(getPosition().moveX(1));
        points.add(getPosition().moveX(-1));
        points.add(getPosition().moveY(1));
        points.add(getPosition().moveY(-1));
        points.add(getPosition().moveTo(1,1));
        points.add(getPosition().moveTo(-1,-1));
        points.add(getPosition().moveTo(1,-1));
        points.add(getPosition().moveTo(-1,1));

        return filterNulls(points);
    }

    @Override
    public char getStringIcon() {
        return '+';
    }

    @Override
    public ImageCoords getSrcImageCoords() {
        return new ImageCoords(20, 20 + getColorOffset(), 290, 320);
    }
}
