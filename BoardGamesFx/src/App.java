import boardGames.Board;
import boardGames.BoardView;
import boardGames.Coordinate;
import boardGames.KnightsTourSolver;
import boardGames.Move;
import boardGames.PuzzleSolver;
//import boardGames.NQueensSolver;

import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class App extends Application {
    private Board board = new Board(8, Color.DARKBLUE, Color.CYAN);
    private PuzzleSolver solver = new KnightsTourSolver(board, new Coordinate(0, 0));
    //private PuzzleSolver solver = new NQueensSolver(board);
    private ListView<Move> moveListView;
    private int nextStep = 0;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage aStage) throws Exception {
        solver.solve();
        Scene aScene = new Scene(buildClientArea(), 600, 380);
        aStage.setScene(aScene);
        aStage.setTitle("Chess Puzzles");
        aStage.show();
    }

    private VBox buildClientArea() {
        VBox root = new VBox();
        root.getChildren().add(buildBoardAndMoveList());
        return root;
    }

    private VBox buildBoardAndButtons() {
        VBox column = new VBox();
        BoardView boardView = new BoardView(solver.getBoard(), 320, 320);
        column.getChildren().add(boardView);
        column.getChildren().add(buildButtonRow());
        return column;
    }

    private HBox buildButtonRow() {
        HBox buttons = new HBox();
        Button resetButton = new Button("reset");
        resetButton.setOnAction((event) -> resetGame());
        Button stepButton = new Button("step");
        stepButton.setOnAction((event) -> handleStep());
        buttons.getChildren().add(resetButton);
        buttons.getChildren().add(stepButton);
        return buttons;
    }

    private void resetGame() {
        board.clearBoard();
        nextStep = 0;
    }

    private void handleStep() {
        if (nextStep == solver.getMoves().size()) {
            return;
        }
        Move aMove = solver.getMoves().get(nextStep++);
        aMove.doOn(board);
    }

    private HBox buildBoardAndMoveList() {
        HBox row = new HBox();
        ObservableList<Move> moves = FXCollections.observableArrayList(solver.getMoves());
        moveListView = new ListView<Move>(moves);
        row.getChildren().add(buildBoardAndButtons());
        row.getChildren().add(moveListView);
        return row;
    }
}