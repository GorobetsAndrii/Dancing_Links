package app.model;

public class Solver {
    private DLLMatrix matrix;

    public Solver(int[][] tab) throws Exception {
        matrix = new DLLMatrix(tab);
    }


}
