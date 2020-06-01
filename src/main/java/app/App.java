package app;

import app.controller.DancingController;
import app.model.Generator;
import app.model.Solver;
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
        loader.setControllerFactory(c -> {
            return new DancingController(new Solver(),new Generator());
        });
//        Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
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

        launch(args);
    }
}
