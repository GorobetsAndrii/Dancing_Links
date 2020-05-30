package app.model;

import java.util.HashSet;
import java.util.Set;

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

    private boolean checkSolve() {
        DLLHeader header = matrix.getHead();
        while (header.getRight() != null) {
            if (header.getNumberOfElements() == 0) return false;
            header = (DLLHeader) header.getRight();
        }
        return true;
    }

    private void removeRowsAndColumns(DLLNode node) {
        Set<DLLHeader> headers = new HashSet<>();
        while (node.getRight() != null) node = node.getRight();

        while (node.getLeft() != null) {
            headers.add(matrix.getHeaderFromNode(node));
            node = node.getLeft();
        }

        //TODO
        //remove rows

        removeColumns(headers);
    }


    private void removeColumns(Set<DLLHeader> headers) {
        //TODO
        //remove columns
    }

}
