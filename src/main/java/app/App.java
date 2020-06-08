package app;

import app.controller.DancingController;
import app.logic.TxtFileMatrixParser;
import app.model.Generator;
import app.model.SecondSolver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        loader.setControllerFactory(c -> new DancingController(new SecondSolver(), new Generator(), new TxtFileMatrixParser()));

        Parent root = loader.load();
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
        int[][] arr = {
                {0, 0, 0, 1, 0, 0, 1, 0}, //0
                {1, 0, 0, 0, 1, 0, 0, 1}, //1
                {0, 1, 0, 0, 1, 0, 0, 0}, //2
                {0, 1, 0, 0, 0, 0, 0, 0}, //3
                {0, 0, 0, 0, 0, 1, 0, 1}, //4
                {0, 0, 0, 0, 0, 0, 0, 0}, //5
                {0, 0, 1, 0, 0, 0, 0, 1}, //6
                {0, 0, 0, 1, 1, 0, 0, 0}};//7

        SecondSolver solver = new SecondSolver();
        for (int i = 0; i < 100; ++i) {
            solver.solve(arr);
            System.out.println(solver.getSolves());
        }
        launch(args);
    }
}
