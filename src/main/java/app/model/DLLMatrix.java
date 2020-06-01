package app.model;

public class DLLMatrix {
    private DLLHeader head;

    public DLLMatrix(int[][] matrix) throws Exception {
        createHeaders(matrix[0].length);
        init(matrix);
        setLR();
        removeZeros();
    }

    // Headers initialization
    private void createHeaders(int length) {
        DLLHeader header = new DLLHeader(0);
        head = header;
        for (int i = 1; i < length; ++i) {
            header.setRight(new DLLHeader(i));
            header.getRight().setLeft(header);
            header = (DLLHeader) header.getRight();
        }
    }

    // Creating down-up pointers
    private void init(int[][] matrix) throws Exception {
        DLLHeader node = head;

        while (node != null) {
            int counter = 0;
            DLLNode tmp = node;

            for (int i = 0; i < matrix.length; ++i) {
                if (matrix[i][node.getColumn()] == 1) {
                    tmp.setDown(new DLLNode(i, node.getColumn()));
                    counter++;
                } else {
                    tmp.setDown(new DLLNode(-1, node.getColumn()));
                }
                tmp.getDown().setUp(tmp);
                tmp = tmp.getDown();
            }
            if (counter == 0) throw new Exception("Does not have solve");
            node.setNumberOfElements(counter);
            node = (DLLHeader) node.getRight();
        }
    }

    // Creating left-right pointers
    private void setLR() {
        DLLHeader tmp = head;

        while (tmp.getRight() != null) {
            DLLNode D1 = tmp.getDown();
            DLLNode D2 = tmp.getRight().getDown();
            while (D1 != null) {
                D1.setRight(D2);
                D2.setLeft(D1);
                D1 = D1.getDown();
                D2 = D2.getDown();
            }
            tmp = (DLLHeader) tmp.getRight();
        }
    }

    private void removeZeros() {
        DLLHeader tmp = head;
        while (tmp != null) {
            DLLNode node = tmp.getDown();
            while (node != null) {
                if (node.getRow() == -1) {
                    if (node.getDown() != null) {
                        node.getUp().setDown(node.getDown());
                        node.getDown().setUp(node.getUp());
                    } else {
                        node.getUp().setDown(null);
                    }
                    if (node.getLeft() != null && node.getRight() != null) {
                        node.getRight().setLeft(node.getLeft());
                        node.getLeft().setRight(node.getRight());
                    } else if (node.getLeft() == null && node.getRight() != null) {
                        node.getRight().setLeft(null);
                    } else if (node.getLeft() != null) {
                        node.getLeft().setRight(null);
                    }
                }
                node = node.getDown();
            }
            tmp = (DLLHeader) tmp.getRight();
        }
    }

    public DLLHeader getHead() {
        return head;
    }

    public void setHead(DLLHeader head) {
        this.head = head;
    }

    public DLLHeader getHeaderFromNode(DLLNode node) {
        DLLNode tmp = node;
        while (tmp.getUp() != null) tmp = tmp.getUp();
        return (DLLHeader) tmp;
    }
}
