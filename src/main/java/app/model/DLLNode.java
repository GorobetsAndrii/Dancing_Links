package app.model;

public class DLLNode {
    private DLLNode right;
    private DLLNode left;
    private DLLNode up;
    private DLLNode down;
    private int row;
    private int column;

    public DLLNode(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public DLLNode getRight() {
        return right;
    }

    public DLLNode getLeft() {
        return left;
    }

    public DLLNode getUp() {
        return up;
    }

    public DLLNode getDown() {
        return down;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRight(DLLNode right) {
        this.right = right;
    }

    public void setLeft(DLLNode left) {
        this.left = left;
    }

    public void setUp(DLLNode up) {
        this.up = up;
    }

    public void setDown(DLLNode down) {
        this.down = down;
    }

    public DLLNode getEndOfRight() {
        DLLNode node = this;
        while (node.getRight() != null) node = node.getRight();
        return node;
    }

    public DLLNode getEndOfLeft() {
        DLLNode node = this;
        while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

}
