package app.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solver {
    private DLLMatrix matrix;
    private int[][] arr;
    public LinkedList<Integer> solves;

    public Solver() {
        solves = new LinkedList<>();
    }

    public void solve(int[][] tab) {
        init(tab);
        LinkedList<Set<DLLNode>> removedRows = new LinkedList<>();

        while (!checkSolve()) {
            if (checkZerosColumn()) {
                break;
            }
            DLLHeader start = getHeaderWithMinQuantityElem();
            DLLNode row = getRowWithMaxQuantityElem(start);
            solves.add(row.getRow());
            removedRows.add(removeRowsAndColumns(row));
        }
    }

    public void init(int[][] tab) {
        arr = tab;
        try {
            matrix = new DLLMatrix(tab);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        while (col != result.getColumn()) {
            result = (DLLHeader) result.getRight();
        }
        return result;
    }

    private DLLNode getRowWithMaxQuantityElem(DLLHeader header) {
        DLLNode node = header.getDown();
        DLLNode result = node;
        int index = -1;
        int maxQuantity = -1;
        while (node != null) {
            int quantity = 0;
            DLLNode tmp = node;
            while (tmp != null) {
                quantity++;
                tmp = tmp.getRight();
            }
            if (quantity > maxQuantity) {
                index = node.getRow();
                maxQuantity = quantity;
            }
            node = node.getDown();
        }


        while (result.getRow() != index && result != null) result = result.getDown();

        return result;
    }

    private boolean checkSolve() {
        return matrix.getHead() == null;
    }

    private boolean checkZerosColumn() {
        DLLHeader header = matrix.getHead();
        while (header != null) {
            if (header.getNumberOfElements() == 0) return true;
            if (header.getDown() == null) return true;
            header = (DLLHeader) header.getRight();
        }
        return false;
    }

    public Set<DLLNode> removeRowsAndColumns(DLLNode node) {
        Set<DLLHeader> headers = new HashSet<>();
        Set<DLLNode> removedNodes = new HashSet<>();

        while (node.getRight() != null) node = node.getRight();

        DLLNode tmp = node;

        while (tmp != null) {
            headers.add(matrix.getHeaderFromNode(tmp));
            tmp = tmp.getLeft();
        }

        for (DLLNode h : headers) {
            DLLNode curr = h.getDown();
            if (curr == null) continue;
            while (curr.getLeft() != null) curr = curr.getLeft();
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
        }

        removeColumns(headers);
        return removedNodes;
    }

    private void removeColumns(Set<DLLHeader> headers) {
        for (DLLHeader header : headers) {
            if (header.getLeft() != null && header.getRight() != null) {
                header.getLeft().setRight(header.getRight());
                header.getRight().setLeft(header.getLeft());
            } else if (header.getLeft() == null && header.getRight() != null) {
                header.getRight().setLeft(null);
                matrix.setHead((DLLHeader) header.getRight());
            } else if (header.getLeft() != null) {
                header.getLeft().setRight(null);
            }
        }
    }

    public void returnRowsAndColumns(Set<DLLNode> rows) {
        Set<DLLHeader> headers = new HashSet<>();

        for (DLLNode node : rows) {
            while (node != null) {
                headers.add(matrix.getHeaderFromNode(node));
                node = node.getRight();
            }
        }

        returnColumns(headers);

        for (DLLNode row : rows) {
            while (row != null) {
                if (row.getDown() != null) {
                    row.getUp().setDown(row);
                    row.getDown().setUp(row);
                } else {
                    row.getUp().setDown(row);
                }
                row = row.getRight();
            }
        }
    }

    private void returnColumns(Set<DLLHeader> headers) {
        for (DLLHeader header : headers) {
            if (header.getLeft() != null && header.getRight() != null) {
                header.getLeft().setRight(header);
                header.getRight().setLeft(header);
            } else if (header.getLeft() == null) {
                header.getRight().setLeft(header);
            } else {
                header.getLeft().setRight(header);
            }
        }
    }

    public DLLMatrix getMatrix() {
        return matrix;
    }
}
