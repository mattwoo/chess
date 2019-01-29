package pl.mattcode.chess;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class BoardController {

    @FXML
    private Canvas canvas;

    private BoardView view;

    @FXML
    public void initialize()
    {
        view = new BoardView(new Board(), canvas.getGraphicsContext2D());
    }

}
