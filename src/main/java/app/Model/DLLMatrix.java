package app.Model;

public class DLLMatrix {
    private DLLNode first;

    public DLLMatrix(int[][] matrix) {
        first = init(matrix, 0, 0);
    }

    private DLLNode init(int[][] matrix, int i, int j) {
        if (i > matrix.length - 1 || j > matrix[0].length - 1) return null;

        DLLNode node = new DLLNode(matrix[i][j] == 1 ? true : false, i, j);
        node.setRight(init(matrix, i, j + 1));
        node.setDown(init(matrix, i + 1, j));

        return node;
    }

    public void display() {
        DLLNode D = first;
        while (D != null) {
            DLLNode R = D;

            while (R != null) {
                System.out.print(R.getData() + " ");
                R = R.getRight();
            }
            System.out.println();
            D = D.getDown();
        }
    }
}
