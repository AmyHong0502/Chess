package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChessGame extends Application {

    Group root;

    Layout layout;

    public ChessGame() {
        layout = new Layout();

        root = new Group();
        root.getChildren().addAll(layout);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 601, 480));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
