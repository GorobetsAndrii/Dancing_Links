package app.controller;

import app.logic.MatrixParser;
import app.model.Generator;
import app.model.SecondSolver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DancingController implements Initializable {

    private SecondSolver solver;

    private Generator generator;

    private MatrixParser parser;

    @FXML
    private Canvas canvas;

    @FXML
    private Label resultLabel;

    @FXML
    private ChoiceBox<Integer> rowBox, colBox;

    private GraphicsContext gc;

    private int[][] arr;

    private double width;
    private double height;

    private double colSize;
    private double rowSize;

    private boolean isClickedOnSolve;


    public DancingController(SecondSolver solver, Generator generator, MatrixParser parser) {
        this.solver = solver;
        this.generator = generator;
        this.parser = parser;
        this.isClickedOnSolve = false;
    }

    @FXML
    private void generate() {
        isClickedOnSolve = false;
        arr = generator.generate(rowBox.getValue(), colBox.getValue());
        drawMatrix();
    }

    @FXML
    private void chooseFromFile(){
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text file","*.txt"));
            File file=fileChooser.showOpenDialog(new Stage());
            arr = parser.parseMatrix(file);
            drawMatrix();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @FXML
    private void solve() throws Exception {
        if (!isClickedOnSolve) {
            try {
                solver.solve(arr);
                if (solver.getSolves().size() == 0) {
                    drawError();
                    return;
                }
                drawResult();
            } catch (Exception ex) {
                drawError();
            } finally {
                isClickedOnSolve = true;
            }
        }
    }

    private void drawResult() {
        resultLabel.setText("Rows :" + solver.getSolves().toString());

        gc.setGlobalAlpha(0.3);
        gc.setFill(Color.GREEN);
        for (int i : solver.getSolves()) {
            gc.fillRect(0, i * rowSize, width, (rowSize));
        }
    }

    private void drawError() {
        resultLabel.setText("EXCEPTION : Matrix has not solution");
        gc.setGlobalAlpha(0.3);
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, width, height);

    }


    private void drawMatrix() {

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        width = canvas.getWidth();
        height = canvas.getHeight();

        gc.clearRect(0, 0, width, height);
        gc.setGlobalAlpha(1);
        gc.setFill(Color.BLACK);

        rowSize = height / arr.length;
        colSize = width / arr[0].length;

        for (int i = 1; i < arr.length; i++) {
            gc.strokeLine(0, i * rowSize, width, i * rowSize);
        }

        for (int i = 1; i < arr[0].length; i++) {
            gc.strokeLine(i * colSize, 0, i * colSize, height);
        }


        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                gc.fillText(String.valueOf(arr[i][j]), j * colSize + (colSize / 2), i * rowSize + (rowSize / 2));

            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();

        ObservableList<Integer> options = FXCollections.observableArrayList(4, 6, 8, 10, 16, 20, 40);

        rowBox.setValue(8);
        colBox.setValue(8);
        rowBox.setItems(options);
        colBox.setItems(options);
    }


}
