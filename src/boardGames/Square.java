package boardGames;

import javafx.scene.paint.Color;

public class Square {
    private Color color;
    private Piece piece;

    public Square(Color aColor) {
        color = aColor;
    }

    public Color getColor() {
        return color;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece aPiece) {
        piece = aPiece;
    }

    public void removePiece() {
        piece = null;
    }
}