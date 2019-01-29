package pl.mattcode.chess.util;

public class ImageCoords {

    private final int x;
    private final int y;
    private final int w;
    private final int h;

    public ImageCoords(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
