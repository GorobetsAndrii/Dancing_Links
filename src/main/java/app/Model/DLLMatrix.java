package app.Model;

public class DLLMatrix {
    private DLLHeader head;

    public DLLMatrix(int[][] matrix) {
        createHeaders(matrix[0].length);
        init(matrix);
        setLU();
    }

    private void createHeaders(int length) {
        DLLHeader header = new DLLHeader(0);
        head = header;
        for (int i = 1; i < length; ++i) {
            header.setRight(new DLLHeader(i));
            header.getRight().setLeft(header);
            header = header.getRight();
        }
    }

    private void init(int[][] matrix) {
        DLLHeader node = head;
        DLLNode tmp;
        int counter = 1;

        while (node.getRight() != null) {
            node.setDown(new DLLNode(matrix[0][node.getColumn()] == 1, 0, node.getColumn()));
            tmp = node.getDown();
            for (int i = 1; i < matrix.length; ++i) {
                if (matrix[i][node.getColumn()] == 1) {
                    tmp.setDown(new DLLNode(matrix[i][node.getColumn()] == 1, i, node.getColumn()));
                    tmp.getDown().setUp(tmp);
                    counter++;
                }
            }
            node.setNumberOfElements(counter);
            node = node.getRight();
        }
    }

    private void setLU() {
        DLLHeader tmp = head;

        while (tmp.getRight() != null) {
            DLLNode D1 = tmp.getDown();
            DLLNode D2 = tmp.getRight().getDown();
            while (D1 != null && D2 != null) {
                if (D1.getRow() == D2.getRow()) {
                    D1.setRight(D2);
                    D2.setLeft(D1);
                }
                if (D1.getRow() > D2.getRow()) {
                    D2 = D2.getDown();
                } else if (D1.getRow() < D2.getRow()) {
                    D1 = D1.getDown();
                } else {
                    D1 = D1.getDown();
                    D2 = D2.getDown();
                }
            }
            tmp = tmp.getRight();
        }
    }

    public void display() {
        DLLNode D = head.getDown();
        while (D != null) {
            DLLNode R = D;

            while (R != null) {
                System.out.print(R.getData() + " (" + R.getRow() + ") ");
                R = R.getRight();
            }
            System.out.println();
            D = D.getDown();
        }
    }

    public DLLHeader getHead() {
        return head;
    }
}
