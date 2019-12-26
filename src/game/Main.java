package game;

import game.engine.Board;
import game.engine.tools.GameRules;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        GameRules gameRules = new GameRules();
        gameRules.setBoardHeight(10);
        gameRules.setBoardWidth(20);
        gameRules.setAmountOfCities(10);
        Board board = new Board(gameRules);
        board.printBoard();

//        Parent root = FXMLLoader.load(getClass().getResource("sample.sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
