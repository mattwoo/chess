package pl.mattcode.chess.util;

import pl.mattcode.chess.Board;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        if (x < 1 || y < 1) {
            throw new IllegalArgumentException("Point cannot be less than 1");
        }
        if (x > Board.BOARD_SIZE) {
            throw new IllegalArgumentException(String.format("Point x cannot be greater than %d", Board.BOARD_SIZE));
        }

        if (y > Board.BOARD_SIZE) {
            throw new IllegalArgumentException(String.format("Point y cannot be greater than %d", Board.BOARD_SIZE));
        }

        this.x = x;
        this.y = y;
    }

    public Boolean isNextTo(Point p) {
        int xDistance = Math.abs(p.x - x);
        int yDistance = Math.abs(p.y - y);
        return xDistance <= 1 && yDistance <= 1;
    }

    public Boolean isInBoard(Point p) {
        try {
            moveTo(p.getX(), p.getY());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveN() {
        return moveY(1);
    }

    public Point moveN(int step) {
        step = Math.abs(step);
        return moveY(step);
    }

    public Point moveS() {
        return moveY(-1);
    }

    public Point moveS(int step) {
        step = Math.abs(step);
        return moveY(-step);
    }

    public Point moveE() {
        return moveX(1);
    }

    public Point moveE(int step) {
        step = Math.abs(step);
        return moveX(step);
    }

    public Point moveW() {
        return moveX(-1);
    }

    public Point moveW(int step) {
        step = Math.abs(step);
        return moveX(-step);
    }

    public Point moveNE() {
        return moveTo(1, 1);
    }

    public Point moveNE(int step) {
        step = Math.abs(step);
        return moveTo(step, step);
    }

    public Point moveSE() {
        return moveTo(1, -1);
    }

    public Point moveSE(Integer step) {
        step = Math.abs(step);
        return moveTo(step, -step);
    }

    public Point moveSW() {
        return moveTo(-1, -1);
    }

    public Point moveSW(int step) {
        step = Math.abs(step);
        return moveTo(-step, -step);
    }

    public Point moveNW() {
        return moveTo(-1, 1);
    }

    public Point moveNW(int step) {
        step = Math.abs(step);
        return moveTo(-step, step);
    }

    public Point moveTo(int x, int y) {
        try {
            return new Point(this.x + x, this.y + y);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Point moveX(int x) {
        return moveTo(x, 0);
    }

    public Point moveY(int y) {
        return moveTo(0, y);
    }

    @Override
    public String toString() {
        return x + ":" + y;
    }

    public static Point createFromString(String pointString) {
        String[] split = pointString.split(":");
        if (split.length != 2) {
            throw new IllegalArgumentException(String.format("Invalid argument \'%s\' passed to %s.", pointString, Point.class));
        }

        return new Point(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            return ((Point) obj).getX() == getX() && ((Point) obj).getY() == getY();
        }

        return false;
    }
}
