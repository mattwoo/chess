package pl.mattcode.chess;

import pl.mattcode.chess.figure.*;
import pl.mattcode.chess.util.ConsoleOutput;
import pl.mattcode.chess.util.Point;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    public static final int BOARD_SIZE = 8;

    public static final Color WHITE = Color.WHITE;
    public static final Color GRAY = Color.GRAY;
    public static final Color GREEN = Color.LIGHTGREEN;
    public static final Color RED = Color.RED;
    public static final Color BLUE = Color.BLUE;

    private HashMap<String, Figure> map = new HashMap<>();
    private HashMap<String, Color> colorsMap = new HashMap<>();
    private Point selectedPoint;
    private int moveColor = Figure.COLOR_WHITE;

    Board() {
        resetMap();
    }

    public void switchMoves() {
        moveColor = moveColor == Figure.COLOR_WHITE ? Figure.COLOR_BLACK : Figure.COLOR_WHITE;
    }

    public int getMoveColor() {
        return moveColor;
    }

    private void resetMap() {
        resetFieldsBackground();
        initEmptyFields();
        initPawns();
        initFigures();
    }

    public HashMap<String, Figure> getMap() {
        return map;
    }

    public void markAvailableMoves(Point point) {
        Figure figure = getFigureAt(point);
        markFigureSelected(point);
        ArrayList<Point> moves = figure.getAvailableMoves();
        for (Point p : moves) {
            if (isEmpty(p)) {
                setFieldColor(p, Board.GREEN);
            } else if (hasEnemyAt(p, figure) && !(figure instanceof Pawn)) {
                setFieldColor(p, Board.RED);
            }
        }
        if (figure instanceof Pawn) {
            for (Point p : ((Pawn) figure).getSlantMoves()) {
                if (hasEnemyAt(p, figure)) {
                    setFieldColor(p, Board.RED);
                }
            }
        }
        clearOverlappingMoves();
    }

    public void clearOverlappingMoves() {
        Figure selectedFigure = getSelectedFigure();
        ArrayList<Point> moves = selectedFigure.getAvailableMoves();
        if (selectedFigure.canJumpOver()) {
            return;
        }
        Point point, lastPoint;
        Boolean clearNext = false;
        lastPoint = selectedFigure.getPosition();
        while(null != (point = lastPoint.moveN())){
            if(clearNext){
                resetFieldBackground(point);
            }
            if(hasFigureAt(point) && moves.contains(point)){
                clearNext = true;
            }
            lastPoint = point;
        }

        clearNext = false;
        lastPoint = selectedFigure.getPosition();
        while(null != (point = lastPoint.moveS())){
            if(clearNext){
                resetFieldBackground(point);
            }
            if(hasFigureAt(point) && moves.contains(point)){
                clearNext = true;
            }
            lastPoint = point;
        }

        clearNext = false;
        lastPoint = selectedFigure.getPosition();
        while(null != (point = lastPoint.moveE())){
            if(clearNext){
                resetFieldBackground(point);
            }
            if(hasFigureAt(point) && moves.contains(point)){
                clearNext = true;
            }
            lastPoint = point;
        }

        clearNext = false;
        lastPoint = selectedFigure.getPosition();
        while(null != (point = lastPoint.moveW())){
            if(clearNext){
                resetFieldBackground(point);
            }
            if(hasFigureAt(point) && moves.contains(point)){
                clearNext = true;
            }
            lastPoint = point;
        }

        clearNext = false;
        lastPoint = selectedFigure.getPosition();
        while(null != (point = lastPoint.moveSE())){
            if(clearNext){
                resetFieldBackground(point);
            }
            if(hasFigureAt(point) && moves.contains(point)){
                clearNext = true;
            }
            lastPoint = point;
        }

        clearNext = false;
        lastPoint = selectedFigure.getPosition();
        while(null != (point = lastPoint.moveSW())){
            if(clearNext){
                resetFieldBackground(point);
            }
            if(hasFigureAt(point) && moves.contains(point)){
                clearNext = true;
            }
            lastPoint = point;
        }

        clearNext = false;
        lastPoint = selectedFigure.getPosition();
        while(null != (point = lastPoint.moveNE())){
            if(clearNext){
                resetFieldBackground(point);
            }
            if(hasFigureAt(point) && moves.contains(point)){
                clearNext = true;
            }
            lastPoint = point;
        }

        clearNext = false;
        lastPoint = selectedFigure.getPosition();
        while(null != (point = lastPoint.moveNW())){
            if(clearNext){
                resetFieldBackground(point);
            }
            if(hasFigureAt(point) && moves.contains(point)){
                clearNext = true;
            }
            lastPoint = point;
        }
    }

    public void resetFieldBackground(Point p) {
        Color color = (p.getX() + p.getY()) % 2 != 0 ? Color.GRAY : Color.WHITE;
        setFieldColor(p, color);
    }

    public Figure getSelectedFigure() {
        return getFigureAt(selectedPoint);
    }

    public void moveSelectedFigure(Point p) {
        Figure f = getFigureAt(selectedPoint);
        if (this.moveColor != f.getColor()) {
            return;
        }
        f.moveTo(p);
        clearSelectedField();
        if (null != getFigureAt(p) && getFigureAt(p).getColor() != this.moveColor && getFigureAt(p) instanceof King) {
            String which = getFigureAt(p).getColor() == Figure.COLOR_BLACK ? "białe" : "czarne";
            String msg = String.format("Koniec gry, wygrały %s", which);
            showAlert(msg);
            resetFieldsBackground();
            resetMap();
            moveColor = Figure.COLOR_WHITE;
            return;
        }
        map.put(p.toString(), f);
        switchMoves();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(message);
        alert.setHeaderText(message);

        alert.showAndWait();
    }

    private void clearSelectedField() {
        clearField(selectedPoint);
    }

    private void clearField(Point p) {
        map.put(p.toString(), null);
    }

    private void markFigureSelected(Point p) {
        selectedPoint = p;
        setFieldColor(p, Board.BLUE);
    }

    boolean hasEnemyAt(Point p, Figure currentFigure) {
        Figure f = this.getFigureAt(p);
        if (null != f && f.getColor() != currentFigure.getColor()) {
            return true;
        }

        return false;
    }

    boolean isEmpty(Point p) {
        return null == getFigureAt(p);
    }

    private void setFieldColor(Point p, Color color) {
        colorsMap.put(p.toString(), color);
    }

    public void resetFieldsBackground() {
        int ycount = 0;
        Color color;
        for (int y = 1; y <= Board.BOARD_SIZE; y++) {
            for (int x = 1; x <= Board.BOARD_SIZE; x++) {
                if ((x + ycount) % 2 != 0) {
                    color = Board.WHITE;
                } else {
                    color = Board.GRAY;
                }
                Point p = new Point(x, y);
                colorsMap.put(p.toString(), color);
            }
            ycount++;
        }
    }

    private void initPawns() {
        for (int i = 1; i <= BOARD_SIZE; i++) {
            Point blackPoint = new Point(i, 2);
            Point whitePoint = new Point(i, 7);

            map.put(blackPoint.toString(), new Pawn(Figure.COLOR_BLACK, blackPoint));
            map.put(whitePoint.toString(), new Pawn(Figure.COLOR_WHITE, whitePoint));
        }
    }

    private void initEmptyFields() {
        for (int x = 1; x <= BOARD_SIZE; x++) {
            for (int y = 1; y <= BOARD_SIZE; y++) {
                Point point = new Point(x, y);
                map.put(point.toString(), null);
            }
        }
    }

    private void initFigures() {
        Point p;

        p = new Point(5, 8);
        map.put(p.toString(), new King(Figure.COLOR_WHITE, p));
        p = new Point(5, 1);
        map.put(p.toString(), new King(Figure.COLOR_BLACK, p));

        p = new Point(4, 8);
        map.put(p.toString(), new Queen(Figure.COLOR_WHITE, p));
        p = new Point(4, 1);
        map.put(p.toString(), new Queen(Figure.COLOR_BLACK, p));

        p = new Point(1, 1);
        map.put(p.toString(), new Rook(Figure.COLOR_BLACK, p));

        p = new Point(8, 1);
        map.put(p.toString(), new Rook(Figure.COLOR_BLACK, p));

        p = new Point(1, 8);
        map.put(p.toString(), new Rook(Figure.COLOR_WHITE, p));

        p = new Point(8, 8);
        map.put(p.toString(), new Rook(Figure.COLOR_WHITE, p));

        p = new Point(7, 8);
        map.put(p.toString(), new Knight(Figure.COLOR_WHITE, p));

        p = new Point(2, 8);
        map.put(p.toString(), new Knight(Figure.COLOR_WHITE, p));

        p = new Point(7, 1);
        map.put(p.toString(), new Knight(Figure.COLOR_BLACK, p));

        p = new Point(2, 1);
        map.put(p.toString(), new Knight(Figure.COLOR_BLACK, p));

        p = new Point(3, 8);
        map.put(p.toString(), new Bishop(Figure.COLOR_WHITE, p));

        p = new Point(6, 8);
        map.put(p.toString(), new Bishop(Figure.COLOR_WHITE, p));

        p = new Point(3, 1);
        map.put(p.toString(), new Bishop(Figure.COLOR_BLACK, p));

        p = new Point(6, 1);
        map.put(p.toString(), new Bishop(Figure.COLOR_BLACK, p));
    }

    void print() {
        System.out.println("_________________");
        for (int y = 1; y <= BOARD_SIZE; y++) {

            System.out.print('|');
            for (int x = 1; x <= BOARD_SIZE; x++) {
                Point p = new Point(x, y);
                Figure f = map.get(p.toString());
                if (null == f) {
                    System.out.print(" |");
                    continue;
                }
                printFigure(f);
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    public Figure getFigureAt(Point point) {
        return null == point ? null : map.get(point.toString());
    }

    public boolean hasFigureAt(Point point) {
        return null != getFigureAt(point);
    }

    public boolean isMarkedAvailable(Point p) {
        return Board.GREEN == getColorAt(p);
    }

    public boolean isMarkedRed(Point p) {
        System.out.println(p.toString());
        return Board.RED == getColorAt(p);
    }

    public Color getColorAt(Point p) {
        return colorsMap.get(p.toString());
    }

    public void printAvailableMoves(Point point) {
        Figure f = map.get(point.toString());
        if (null != f) {
            for (Point p : f.getAvailableMoves()) {
                if (null != p)
                    map.put(p.toString(), new King(Figure.COLOR_BLACK, p));
            }
        }

        print();
    }

    private void printFigure(Figure f) {
        String color = translateColor(f.getColor());
        String icon = String.valueOf(f.getStringIcon());

        ConsoleOutput.print(icon, color);
        System.out.print('|');
    }

    private String translateColor(int color) {
        return color == Figure.COLOR_BLACK ? ConsoleOutput.RED : ConsoleOutput.YELLOW;
    }
}
