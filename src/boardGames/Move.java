package boardGames;

import java.io.StringWriter;

public class Move {
    private Piece piece;
    private Coordinate start;
    private Coordinate end;

    public Move(Piece aPiece, Coordinate aStartPos, Coordinate anEndPos) {
        piece = aPiece;
        start = aStartPos;
        end = anEndPos;
    }

    public Piece getPiece() {
        return piece;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public void doOn(Board aBoard) {
        if (start != null) {
            aBoard.removePiece(start);
        }
        aBoard.setPiece(end, piece);
    }

    public void undoOn(Board aBoard) {
        aBoard.removePiece(end);
        if (start != null) {
            aBoard.setPiece(start, piece);
        }
    }

    @Override
    public String toString() {
        StringWriter writer = new StringWriter();
        writer.write(piece.toString());
        writer.write(" ");
        if (start != null) {
            writer.write(start.toString());
            writer.write(" -> ");
        }
        writer.write(end.toString());
        return writer.toString();
    }
}
