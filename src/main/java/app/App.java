package app;

import app.model.Solver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("Dancing links");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        int[][] arr = {{0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 1, 1}};

        int[][] arr1 = {{1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}};

        int[][] arr2 = {
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 1}};

        int[][] arr3 = {
                {0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 1}};

        int[][] arr4 = {
                {1, 0, 0, 1, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 0, 1, 0, 0, 1}};

        Solver solver = new Solver();
        solver.solve(arr4);
        solver.solves.stream()
                .forEach(System.out::println);

        launch(args);
    }
}
