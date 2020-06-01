package app;

import app.model.Generator;
import app.model.Solver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
public class App extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));

        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

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

        //answer: 3,5 (row indexes)
        int[][] arr3 = {
                {0, 1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 1}};

        //answer: 3,4 (row indexes)
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

        int[][] arr7 = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {0, 0, 0, 0}};

        int[][] arr8 = {
                {0, 1, 1, 1},
                {0, 1, 0, 0},
                {1, 1, 0, 1},
                {0, 0, 0, 1}};

        int[][] arr9 = {
                {1, 1, 0, 0},
                {0, 1, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 1, 1}};

        int[][] arr10 = {
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0}};

        int[][] arr11 = {
                {0, 0, 1, 1, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 0, 1}};

        Solver solver = new Solver();
        solver.solve(arr);
        solver.getSolves().stream()
                .forEach(System.out::println);


        Generator generator = new Generator();
        for (int i = 0; i < 5; ++i) {
            generator.generate(5, 5);
            for (int j = 0; j < 5; ++j) {
                for (int k = 0; k < 5; ++k) {
                    System.out.print(generator.getArr()[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println(generator.getAnswer());
        }
        launch(args);
    }
}
