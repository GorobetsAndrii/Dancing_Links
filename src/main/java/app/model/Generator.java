package app.model;

import java.util.LinkedList;
import java.util.Random;

public class Generator {
    private int[][] arr;
    private LinkedList<Integer> answer;

    private Solver solver;

    public Generator() {
        solver = new Solver();
        answer = new LinkedList<>();
    }

    public void generate(int row, int column) {
        reset();
        createArray(row, column);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        try {
            solver.solve(arr);
            answer = solver.getSolves();
        } catch (Exception e) {

        }
    }

    private void reset() {
        solver = new Solver();
        answer = new LinkedList<>();
    }

    private void createArray(int row, int column) {
        int[][] matrix = new int[row][column];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < column; ++j) {
                matrix[i][j] = getSimpleNumber();
            }
        }
        arr = matrix;
    }

    private int getNumber() {
        Random random = new Random();
        return (random.nextInt(2) | random.nextInt(2)) == 1 ? 0 : 1;
    }

    private int getSimpleNumber() {
        Random random = new Random();
        return random.nextInt(2);
    }

    public LinkedList<Integer> getAnswer() {
        return answer;
    }

    public int[][] getArr() {
        return arr;
    }
}
