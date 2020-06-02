package app.model;

import java.util.Random;

public class Generator {

    public Generator() {

    }

    public int[][] generate(int row, int column) {
        int[][] matrix = new int[row][column];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                matrix[i][j] = getNumber();
            }
        }
        return matrix;
    }

    private int getSimpleNumber() {
        Random random = new Random();
        return random.nextInt(2);
    }

    private int getNumber() {
        Random random = new Random();
        return (random.nextInt(2) | random.nextInt(2)) == 1 ? 0 : 1;
    }
}
