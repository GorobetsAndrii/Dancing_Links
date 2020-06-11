package app.model;

import app.model.solver.Solver;

import java.util.LinkedHashSet;
import java.util.Set;

public class SolverMinimalCover extends Solver {

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

        while (node2delete != null) {
            removedNodes.add(node2delete);
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

            DLLNode node = header.getDown();
            while (node != null) {
                if (node.getLeft() != null && node.getRight() != null) {
                    node.getLeft().setRight(node.getRight());
                    node.getRight().setLeft(node.getLeft());
                } else if (node.getLeft() == null && node.getRight() != null) {
                    node.getRight().setLeft(null);
                } else if (node.getLeft() != null) {
                    node.getLeft().setRight(null);
                }
                node = node.getDown();
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

            DLLNode node = header.getDown();
            while (node != null) {
                if (node.getLeft() != null && node.getRight() != null) {
                    node.getLeft().setRight(node);
                    node.getRight().setLeft(node);
                } else if (node.getLeft() == null && node.getRight() != null) {
                    node.getRight().setLeft(node);
                } else if (node.getLeft() != null) {
                    node.getLeft().setRight(node);
                }
                node = node.getDown();
            }
        }
    }
}