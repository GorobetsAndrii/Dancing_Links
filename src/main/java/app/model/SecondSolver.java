package app.model;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class SecondSolver {
    private DLLMatrix matrix;
    private LinkedList<Integer> solves;
    private LinkedList<Integer> currentSolves;

    public SecondSolver() {

    }

    public void solve(int[][] tab) throws Exception {
        init(tab);
        process();
    }

    private void process() {
        if (checkZerosColumn()) {
            return;
        }
        if (checkSolve()) {
            if ((solves.size() == 0 || currentSolves.size() < solves.size()) && currentSolves.size() != 0) {
                solves = new LinkedList<>(currentSolves);
            }
            return;
        }

        Set<DLLHeader> headers = getHeadersWithMinQuantityElem();
        for (DLLHeader header : headers) {
            Set<DLLNode> rows = getRowsWithMaxQuantityElem(header);
            for (DLLNode row : rows) {
                RowsAndColumns removed = removeRowsAndColumns(row);
                currentSolves.add(row.getRow());
                process();
                returnRowsAndColumns(removed);
                for (int i = 0; i < currentSolves.size(); ++i) {
                    if (currentSolves.get(i) == row.getRow()) {
                        currentSolves.remove(i);
                        break;
                    }
                }
            }
        }
    }

    public void init(int[][] tab) throws Exception {
        matrix = new DLLMatrix(tab);
        solves = new LinkedList<>();
        currentSolves = new LinkedList<>();
    }

    private Set<DLLHeader> getHeadersWithMinQuantityElem() {
        DLLHeader tmp = matrix.getHead();
        Set<DLLHeader> result = new LinkedHashSet<>();
        int min = getMinHeader();
        while (tmp != null) {
            if (tmp.getNumberOfElements() == min) {
                result.add(tmp);
            }
            tmp = (DLLHeader) tmp.getRight();
        }
        return result;
    }

    private int getMinHeader() {
        DLLHeader tmp = matrix.getHead();
        int min = Integer.MAX_VALUE;
        while (tmp != null) {
            if (tmp.getNumberOfElements() < min) {
                min = tmp.getNumberOfElements();
            }
            tmp = (DLLHeader) tmp.getRight();
        }
        return min;
    }

    private Set<DLLNode> getRowsWithMaxQuantityElem(DLLHeader header) {
        DLLNode node = header.getDown();
        Set<DLLNode> result = new LinkedHashSet<>();
        int maxQuantity = getMaxQuantityInRows(header);

        while (node != null) {
            int quantity = 0;
            DLLNode tmp = node;
            while (tmp != null) {
                quantity++;
                tmp = tmp.getRight();
            }

            if (quantity == maxQuantity) {
                result.add(node);
            }
            node = node.getDown();
        }

        return result;
    }

    private int getMaxQuantityInRows(DLLHeader header) {
        DLLNode node = header.getDown();
        int maxQuantity = -1;
        while (node != null) {
            int quantity = 0;
            DLLNode tmp = node;
            while (tmp != null) {
                quantity++;
                tmp = tmp.getRight();
            }
            if (quantity > maxQuantity) {
                maxQuantity = quantity;
            }
            node = node.getDown();
        }
        return maxQuantity;
    }

    private boolean checkSolve() {
        return matrix.getHead() == null;
    }

    private boolean checkZerosColumn() {
        DLLHeader header = matrix.getHead();
        while (header != null) {
            if (header.getNumberOfElements() == 0) return true;
            header = (DLLHeader) header.getRight();
        }
        return false;
    }

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
            } else {
                matrix.setHead(null);
            }
        }
    }

    public void returnRowsAndColumns(RowsAndColumns rowsAndColumns) {
        returnColumns(rowsAndColumns.headers);

        for (DLLNode row : rowsAndColumns.rows) {
            while (row != null) {
                if (row.getDown() != null) {
                    row.getUp().setDown(row);
                    row.getDown().setUp(row);
                } else {
                    row.getUp().setDown(row);
                }
                matrix.getHeaderFromNode(row).setNumberOfElements(matrix.getHeaderFromNode(row).getNumberOfElements() + 1);
                row = row.getRight();
            }
        }
    }

    private void returnColumns(Set<DLLHeader> headers) {
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

    public LinkedList<Integer> getSolves() {
        return solves;
    }
}