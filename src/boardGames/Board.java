package boardGames;

import javafx.scene.paint.Color;
import javafx.beans.Observable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;

public class Board implements Observable {

    private Square[][] squares;
    private List<InvalidationListener> listeners = new ArrayList<InvalidationListener>();

    public Board(int size, Color darkColor, Color lightColor) {
        Color[] colors = { darkColor, lightColor };
        squares = new Square[size][size];
        for (int fileIndex = 0; fileIndex < squares.length; ++fileIndex) {
            for (int rankIndex = 0; rankIndex < squares[fileIndex].length; ++rankIndex) {
                squares[fileIndex][rankIndex] = new Square(colors[(fileIndex + rankIndex) % 2]);
            }
        }
    }

    public void clearBoard() {
        for (int fileIndex = 0; fileIndex < squares.length; ++fileIndex) {
            for (int rankIndex = 0; rankIndex < squares[fileIndex].length; ++rankIndex) {
                squares[fileIndex][rankIndex].removePiece();
            }
        }
        notifyListeners();
    }

    public int size() {
        return squares.length;
    }

    public Square getSquare(Coordinate aCoord) {
        return squares[aCoord.fileIndex][aCoord.rankIndex];
    }

    public boolean isEmpty(Coordinate aCoord) {
        return getSquare(aCoord).isEmpty();
    }

    public Color getColor(Coordinate aCoord) {
        return getSquare(aCoord).getColor();
    }

    public Piece getPiece(Coordinate aCoord) {
        return getSquare(aCoord).getPiece();
    }

    public void setPiece(Coordinate aCoord, Piece aPiece) {
        getSquare(aCoord).setPiece(aPiece);
        notifyListeners();
    }

    public void removePiece(Coordinate aCoord) {
        getSquare(aCoord).removePiece();
        notifyListeners();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (InvalidationListener eachListener : listeners) {
            eachListener.invalidated(this);
        }
    }
}
