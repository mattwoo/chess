package pl.mattcode.chess.figure;

import pl.mattcode.chess.util.ImageCoords;
import pl.mattcode.chess.util.Point;

import java.util.ArrayList;

interface FigureInterface {

    Boolean hasMoved();
    ArrayList<Point> getAvailableMoves();
    char getStringIcon();
    ImageCoords getSrcImageCoords();
    Boolean canJumpOver();

}
