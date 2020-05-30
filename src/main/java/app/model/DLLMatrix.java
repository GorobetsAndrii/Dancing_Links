package app.model;

public class DLLMatrix {
    private DLLHeader head;

    public DLLMatrix(int[][] matrix) throws Exception {
        createHeaders(matrix[0].length);
        init(matrix);
        setLR();
        //last2FirstUD();
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

    // Creating left-right pointers
    private void setLR() {
        DLLHeader tmp = head;

        while (tmp.getRight() != null) {
            DLLNode D1 = tmp.getDown();
            DLLNode D2 = tmp.getRight().getDown();

            while (D1 != null) {
                if (D1.getRow() == D2.getRow()) {
                    D1.setRight(D2);
                    D2.setLeft(D1);
                } else {
                    while (D2.getDown() != null && D2.getRow() < D1.getRow()) D2 = D2.getDown();
                    if (D1.getRow() != D2.getRow()) {
                        while (D2.getUp() != null) D2 = D2.getUp();
                        if (D2.getRight() == null) D2 = null;
                        else D2 = D2.getRight().getDown();
                    }
                }
                if (D2 == null || D1.getRow() == D2.getRow()) {
                    D1 = D1.getDown();
                    D2 = tmp.getRight().getDown();
                }
            }
            tmp = (DLLHeader) tmp.getRight();
        }
    }

    /*
        private void last2FirstUD(){
            DLLHeader node = head;

            while(node != null) {
                DLLNode tmp = node;
                while(tmp.getDown() != null) tmp = tmp.getDown();
                tmp.setDown(node);
                node.setUp(tmp);

                node = (DLLHeader) node.getRight();
            }
        }
    */
    public DLLHeader getHead() {
        return head;
    }

    public DLLHeader getHeaderFromNode(DLLNode node) {
        DLLNode tmp = node;
        while (tmp.getUp() != null) tmp = tmp.getUp();
        return (DLLHeader) tmp;
    }
}
