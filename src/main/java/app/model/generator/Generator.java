package app.model.generator;

public class Generator {

    public Generator() {

    }

    public int[][] generate(int row, int column, GenerateMethod method) {
        int[][] matrix = new int[row][column];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                matrix[i][j] = method.generate();
            }
        }
        return matrix;
    }
}
