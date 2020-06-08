package app.model;

import app.model.solver.Solver;

import java.util.LinkedHashSet;
import java.util.Set;

public class SolverExactCover extends Solver {

    public RowsAndColumns removeRowsAndColumns(DLLNode node) {
        Set<DLLHeader> headers = new LinkedHashSet<>();
        Set<DLLNode> removedNodes = new LinkedHashSet<>();
        RowsAndColumns rowsAndColumns = new RowsAndColumns();

        node = node.getEndOfRight();

        while (node != null) {
            headers.add(matrix.getHeaderFromNode(node));
            node = node.getLeft();
        }

        rowsAndColumns.headers = headers;

        for (DLLNode h : headers) {
            DLLNode down = h.getDown();
            while (down != null) {
                DLLNode curr = down.getEndOfLeft();
                removedNodes.add(curr);
                while (curr != null) {
                    if (curr.getDown() != null) {
                        curr.getUp().setDown(curr.getDown());
                        curr.getDown().setUp(curr.getUp());
                    } else {
                        curr.getUp().setDown(null);
                    }
                    matrix.getHeaderFromNode(curr).setNumberOfElements(matrix.getHeaderFromNode(curr).getNumberOfElements() - 1);
                    curr = curr.getRight();
                }
                down = down.getDown();
            }
        }

        rowsAndColumns.rows = removedNodes;
        removeColumns(headers);
        return rowsAndColumns;
    }


}
