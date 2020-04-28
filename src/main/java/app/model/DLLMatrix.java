package app.model;

public class DLLMatrix {
    private DLLHeader head;

    public DLLMatrix(int[][] matrix) throws Exception {
        createHeaders(matrix[0].length);
        init(matrix);
        setLU();
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
                if (matrix[node.getColumn()][i] == 1) {
                    tmp.setDown(new DLLNode(i, node.getColumn()));
                    tmp.getDown().setUp(tmp);
                    tmp = tmp.getDown();
                    counter++;
                }
            }
            if (counter == 0) throw new Exception("Does not have solve");
            node.setNumberOfElements(counter);
            node = (DLLHeader) node.getRight();
        }
    }

    // Creating right-left pointers
    private void setLU() {
        DLLHeader tmp = head;

        while (tmp.getRight() != null) {
            DLLNode D1 = tmp.getDown();
            DLLNode D2 = tmp;
            for (int i = 0; i < tmp.getNumberOfElements(); ++i) {
                if (D2.getRight() == null) break;
                D2 = D2.getRight().getDown();
                while (D2.getRow() < D1.getRow() && D2.getDown() != null) D2 = D2.getDown();
                if (D1.getRow() == D2.getRow()) {
                    D1.setRight(D2);
                    D2.setLeft(D1);
                } else i--;
            }
            tmp = (DLLHeader) tmp.getRight();
        }
    }

    public void display() {
        DLLNode D = head.getDown();
        while (D != null) {
            DLLNode R = D;

            while (R != null) {
                System.out.print(R.getColumn() + " (" + R.getRow() + ") ");
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
