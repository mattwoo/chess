package pl.mattcode.chess;

import pl.mattcode.chess.figure.Figure;
import pl.mattcode.chess.util.ImageCoords;
import pl.mattcode.chess.util.Point;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class BoardView implements EventHandler<MouseEvent> {

    private final Board board;
    private final GraphicsContext gc;
    private final int fieldSize;
    private final int boardSize;

    BoardView(Board board, GraphicsContext gc) {
        this.board = board;
        this.gc = gc;
        fieldSize = ((int) Math.round(gc.getCanvas().getWidth() / Board.BOARD_SIZE));
        boardSize = fieldSize * Board.BOARD_SIZE;
        initialize();
    }

    @Override
    public void handle(MouseEvent event) {
        Point p = getPointFromEvent(event);

        if(board.hasFigureAt(p)){
            if(board.isMarkedRed(p)){
                System.out.println("RED");
                board.resetFieldsBackground();
                board.moveSelectedFigure(p);
            }
            else {
                board.resetFieldsBackground();
                board.markAvailableMoves(p);
                if (board.getFigureAt(p).getColor() != board.getMoveColor()) {
                    return;
                }
            }
        }
        else if(board.isMarkedAvailable(p)){
            board.resetFieldsBackground();
            board.moveSelectedFigure(p);
        }
        repaint();
    }

    private Point getPointFromEvent(MouseEvent event) {
        int x = ((int) event.getX() / fieldSize) + 1;
        int y = ((int) event.getY() / fieldSize) + 1;
        return new Point(x, y);
    }

    void repaint() {
        drawBoardBackground();
        drawFigures();
    }

    private void initialize() {
        gc.getCanvas().addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        repaint();
    }

    private void drawBoardBackground() {
        int ycount = 1, xcount = 1;
        for (int y = 0; y < boardSize; y += fieldSize) {
            for (int x = 0; x < boardSize; x += fieldSize) {
                gc.setFill(board.getColorAt(new Point(xcount, ycount)));
                gc.fillRect(x, y, x + fieldSize, y + fieldSize);
                xcount++;
            }
            xcount = 1;
            ycount++;
        }
    }

    private void drawFigures() {
        Image image = new Image("resources/figures.png");
        int dx, dy;
        for (int y = 1; y <= Board.BOARD_SIZE; y++) {
            for (int x = 1; x <= Board.BOARD_SIZE; x++) {
                Point p = new Point(x, y);
                Figure f = board.getMap().get(p.toString());
                if (null != f && null != f.getSrcImageCoords()) {
                    ImageCoords co = f.getSrcImageCoords();
                    dx = (f.getPosition().getX() - 1) * fieldSize;
                    dy = (f.getPosition().getY() - 1) * fieldSize;
                    gc.drawImage(image, co.getX(), co.getY(), co.getW(), co.getH(), dx, dy, fieldSize, fieldSize);
                }
            }
        }

    }
}
