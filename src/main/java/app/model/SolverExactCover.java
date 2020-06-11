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

    protected void removeColumns(Set<DLLHeader> headers) {
        for (DLLHeader header : headers) {
            if (header.getLeft() != null && header.getRight() != null) {
                header.getLeft().setRight(header.getRight());
                header.getRight().setLeft(header.getLeft());
            } else if (header.getLeft() == null && header.getRight() != null) {
                header.getRight().setLeft(null);
                matrix.setHead((DLLHeader) header.getRight());
            } else if (header.getLeft() != null) {
                header.getLeft().setRight(null);
            } else {
                matrix.setHead(null);
            }
        }
    }

    protected void returnColumns(Set<DLLHeader> headers) {
        for (DLLHeader header : headers) {
            if (header.getLeft() != null && header.getRight() != null) {
                header.getLeft().setRight(header);
                header.getRight().setLeft(header);
            } else if (header.getLeft() == null && header.getRight() != null) {
                header.getRight().setLeft(header);
                matrix.setHead(header);
            } else if (header.getLeft() != null) {
                header.getLeft().setRight(header);
            } else {
                matrix.setHead(header);
            }
        }
    }

}
