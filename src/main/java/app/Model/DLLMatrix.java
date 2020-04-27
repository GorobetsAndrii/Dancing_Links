package app.Model;

public class DLLMatrix {
    private DLLNode head;

    public DLLMatrix(int[][] matrix) {
        head = init(matrix, 0, 0);
        setLU();
    }

    private DLLNode init(int[][] matrix, int i, int j) {
        if (i > matrix.length - 1 || j > matrix[0].length - 1) return null;
        DLLNode node = new DLLNode(matrix[i][j] == 1, i, j);
        DLLNode D = node;


        for (i = 0; i < matrix.length; ++i) {
            j = 0;
            if (i < matrix.length - 1) {
                D.setDown(new DLLNode(matrix[i + 1][j] == 1, i + 1, j));
                D.getDown().setUp(D);
            }
            DLLNode R = D;
            for (j = 1; j < matrix[0].length; ++j) {
                R.setRight(new DLLNode(matrix[i][j] == 1, i, j));
                R.getRight().setLeft(R);
                R = R.getRight();
            }
            D = D.getDown();
        }

        return node;
    }

    private void setLU() {
        DLLNode tmp = head;

        while (head.getDown() != null) {
            DLLNode R1 = head.getRight();
            DLLNode R2 = head.getDown().getRight();
            while (R1 != null) {
                if (R1.getDown() == null) {
                    R1.setDown(R2);
                    R2.setUp(R1);
                }
                R1 = R1.getRight();
                R2 = R2.getRight();
            }
            head = head.getDown();
        }

        head = tmp;
    }

    public void display() {
        DLLNode D = head;
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

    public DLLNode getHead() {
        return head;
    }
}
