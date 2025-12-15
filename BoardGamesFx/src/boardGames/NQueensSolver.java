package boardGames;

import java.util.ArrayList;
import java.util.List;

public class NQueensSolver extends PuzzleSolver {

    public NQueensSolver(Board aBoard) {
        super(aBoard);
    }

    @Override
    public boolean hasSolvedPuzzle() {
        if (board.size() == moves.size()) {
            return true;
        }
        return false;
    }

    @Override
    public List<Move> nextLegalMoves() {
        List<Move> nextMoves = new ArrayList<Move>();
        int currentRankIndex = moves.size();
        for (int index = 0; index < board.size(); ++index) {
            if (this.isSafe(new Coordinate(index, currentRankIndex))) {
                Coordinate aCoordinate = new Coordinate(index, currentRankIndex);
                nextMoves.add(new Move(new Queen(), null, aCoordinate));
            }
        }
        return nextMoves;
    }

    public boolean isSafe(Coordinate aCoordinate) {
        for (Move eachMove : moves) {
            if (isThreat(aCoordinate, eachMove.getEnd())) {
                return false;
            }
        }
        return true;
    }

    public boolean isThreat(Coordinate aCoordinate, Coordinate queenCoordinate) {
        return aCoordinate.fileIndex == queenCoordinate.fileIndex
                || aCoordinate.rankIndex == queenCoordinate.rankIndex
                || Math.abs(aCoordinate.rankIndex - queenCoordinate.rankIndex) == Math
                        .abs(queenCoordinate.fileIndex - aCoordinate.fileIndex);
    }
}