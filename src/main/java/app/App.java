package app;

import app.Model.DLLMatrix;
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

    public static void main(String[] args) {
        launch(args);
        int arr[][] = {{1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}};

        DLLMatrix m = new DLLMatrix(arr);
        m.display();
        //System.out.println(m.getHead().getDown().getDown().getRight().getUp().getRow());
    }
}
