package boardGames;

import java.util.List;
import java.util.ArrayList;

public abstract class PuzzleSolver {
    protected Board board;
    protected List<Move> moves;

    public PuzzleSolver(Board aBoard){
        board = aBoard;
        moves = new ArrayList<Move>();
    }

    public abstract boolean hasSolvedPuzzle();
    public abstract List<Move> nextLegalMoves();

    public boolean solve(){
        if(hasSolvedPuzzle()){
            return true;
        }
        List<Move> nextMoves = nextLegalMoves();
        for(Move eachMove : nextMoves ){
            make(eachMove);
            if(solve()){
                return true;
            }
            unmakeLastMove();
        }
        return false;
    }

    public void make(Move aMove){
        aMove.doOn(board);
        moves.add(aMove);
    }

    public void unmakeLastMove(){
        Move aMove = moves.remove(moves.size() - 1);
        aMove.undoOn(board);
    }

    public List<Move> getMoves(){
        return moves;
    }

    public Board getBoard(){
        return board;
    }
}
