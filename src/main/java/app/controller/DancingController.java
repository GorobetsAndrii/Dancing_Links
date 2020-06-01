package app.controller;

import app.model.Generator;
import app.model.Solver;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class DancingController implements Initializable {

    private Solver solver;

    private Generator generator;

    @FXML
    private Canvas canvas;

    @FXML
    private Label resultLabel;

    private GraphicsContext gc;

    private int[][] arr;

    private double width;
    private double height;

    private int colSize;
    private int rowSize;


    public DancingController(Solver solver, Generator generator){
        this.solver = solver;
        this.generator = generator;
    }

    @FXML
    private void generate() {
        arr = generator.generate(4,4);
        drawMatrix();

    }


    @FXML
    private void solve() throws Exception {
        solver.solve(arr);
        resultLabel.setText("Rows :" + solver.getSolves().toString());

        gc.setGlobalAlpha(0.3);
        gc.setFill(Color.GREEN);
        for (int i : solver.getSolves()) {
            gc.fillRect(0, i * rowSize, width, (rowSize));
        }
    }

    private void drawMatrix() {

        width = canvas.getWidth();
        height = canvas.getHeight();

        colSize = (int) (width / arr.length);
        rowSize = (int) (height / arr[0].length);

        gc.clearRect(0, 0, width, height);
        gc.setGlobalAlpha(1);
        gc.setFill(Color.BLACK);
        for (int i = 1; i < arr.length; i++) {
            gc.strokeLine(i * colSize, 0, i * colSize, height);
        }

        for (int i = 1; i < arr[0].length; i++) {
            gc.strokeLine(0, i * rowSize, width, i * rowSize);
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                gc.fillText(String.valueOf(arr[j][i]), i * colSize + (colSize / 2), j * rowSize + (rowSize / 2 + 5));
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
    }


}
