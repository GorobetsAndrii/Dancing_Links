package app.model;

import app.model.solver.Solver;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class SolverImpl2 extends Solver {

    public RowsAndColumns removeRowsAndColumns(DLLNode node) {
        Set<DLLHeader> headers = new LinkedHashSet<>();
        Set<DLLNode> removedNodes = new LinkedHashSet<>();
        RowsAndColumns rowsAndColumns = new RowsAndColumns();

        DLLNode node2delete = node.getEndOfLeft();

        node = node.getEndOfRight();

        while (node != null) {
            headers.add(matrix.getHeaderFromNode(node));
            node = node.getLeft();
        }

        rowsAndColumns.headers = headers;

        removedNodes.add(node2delete);
        while (node2delete != null) {
            if (node2delete.getDown() != null) {
                node2delete.getUp().setDown(node2delete.getDown());
                node2delete.getDown().setUp(node2delete.getUp());
            } else {
                node2delete.getUp().setDown(null);
            }
            matrix.getHeaderFromNode(node2delete).setNumberOfElements(matrix.getHeaderFromNode(node2delete).getNumberOfElements() - 1);
            node2delete = node2delete.getRight();
        }


        rowsAndColumns.rows = removedNodes;
        removeColumns(headers);
        return rowsAndColumns;
    }
}