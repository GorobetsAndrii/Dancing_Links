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
        //answer: 2,3 (row indexes)
        int[][] arr = {
                {0, 1, 0, 1, 0, 0},
                {1, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 1, 1}};

        //answer: 0,1 (row indexes)
        int[][] arr1 = {
                {1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}};

        //answer: 1,3,5 (row indexes)
        int[][] arr2 = {
                {1, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1},
                {0, 0, 1, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 1}};

        //answer: 2,4 (row indexes)
        int[][] arr3 = {
                {0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 1}};

        //answer: 1,3,7 (row indexes)
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

        //answer: 1,3,6,9 (row indexes)
        int[][] arr5 = {
                {0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0},
                {1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1}};

        int[][] arr6 = {
                {0, 0, 1},
                {0, 1, 0},
                {1, 1, 1}};


        Solver solver = new Solver();
        solver.solve(arr5);
        solver.solves.stream()
                .forEach(System.out::println);

        launch(args);
    }
}
