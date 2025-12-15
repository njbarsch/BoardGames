package boardGames;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class BoardView extends Canvas {
    double squareSize;

    public BoardView(Board aBoard, double width, double height) {
        super(width, height);
        aBoard.addListener((board) -> redraw(board));
        redraw(aBoard);

    }

    public void redraw(Object aBoard) {
        Board board = (Board) aBoard;
        squareSize = getWidth() / board.size();
        for (int rankIndex = 0; rankIndex < board.size(); ++rankIndex) {
            for (int fileIndex = 0; fileIndex < board.size(); ++fileIndex) {
                Coordinate aCoordinate = new Coordinate(fileIndex, rankIndex);
                drawSquareAt(board, aCoordinate);
            }
        }
    }

    public void drawSquareAt(Board aBoard, Coordinate aCoordinate) {
        Square aSquare = aBoard.getSquare(aCoordinate);
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(aSquare.getColor());
        double x = aCoordinate.fileIndex * squareSize;
        double y = (aBoard.size() - aCoordinate.rankIndex - 1) * squareSize;
        gc.fillRect(x, y, squareSize, squareSize);
    }
}
