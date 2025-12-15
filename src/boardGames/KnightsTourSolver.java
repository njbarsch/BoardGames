package boardGames;

import java.util.List;
import java.util.ArrayList;

public class KnightsTourSolver extends PuzzleSolver {

    private int[] fileDeltas = { 1, 2, 2, 1, -1, -2, -2, -1 };
    private int[] rankDeltas = { 2, 1, -1, -2, -2, -1, 1, 2 };

    public KnightsTourSolver(Board aBoard, Coordinate startPosition) {
        super(aBoard);
        Move firstMove = new Move(new Knight(), null, startPosition);
        make(firstMove);
    }

    @Override
    public boolean hasSolvedPuzzle() {
        return moves.size() == board.size() * board.size();
    }

    private boolean isOffBoard(int fileIndex, int rankIndex) {
        return fileIndex < 0 || rankIndex < 0
                || fileIndex >= board.size() || rankIndex >= board.size();
    }

    @Override
    public List<Move> nextLegalMoves() {
        List<Move> nextMoves = new ArrayList<Move>();
        Move lastMove = moves.get(moves.size() - 1);
        Piece knight = lastMove.getPiece();
        Coordinate currentPosition = lastMove.getEnd();
        for (int i = 0; i < fileDeltas.length; ++i) {
            int fileIndex = currentPosition.fileIndex + fileDeltas[i];
            int rankIndex = currentPosition.rankIndex + rankDeltas[i];
            if (isOffBoard(fileIndex, rankIndex))
                continue;
            Coordinate aCoord = new Coordinate(fileIndex, rankIndex);
            if (hasVisited(aCoord))
                continue;
            Move aMove = new Move(knight, currentPosition, aCoord);
            nextMoves.add(aMove);
        }
        return nextMoves;
    }

    public boolean hasVisited(Coordinate aCoord) {
        for (Move eachMove : moves) {
            if (aCoord.equals(eachMove.getEnd())) {
                return true;
            }
        }
        return false;
    }

}
