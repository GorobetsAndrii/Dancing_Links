package app.model;

public class Solver {
    private DLLMatrix matrix;
    private int[][] arr;

    public Solver(int[][] tab) throws Exception {
        matrix = new DLLMatrix(tab);
        arr = tab;
    }


    private DLLHeader getHeaderWithMinQuantityElem() {
        DLLHeader tmp = matrix.getHead();
        DLLHeader result = tmp;
        int min = Integer.MAX_VALUE;
        int col = 0;
        while (tmp != null) {
            if (tmp.getNumberOfElements() < min) {
                min = tmp.getNumberOfElements();
                col = tmp.getColumn();
            }
            tmp = (DLLHeader) tmp.getRight();
        }

        while (col > 0) {
            result = (DLLHeader) result.getRight();
            col--;
        }
        return result;
    }

}
