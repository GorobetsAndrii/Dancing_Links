package app.Model;

public class DLLHeader {
    private int column;
    private int numberOfElements;
    private DLLHeader right;
    private DLLHeader left;
    private DLLNode down;

    public DLLHeader(int column) {
        this.column = column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setRight(DLLHeader right) {
        this.right = right;
    }

    public void setLeft(DLLHeader left) {
        this.left = left;
    }

    public void setDown(DLLNode down) {
        this.down = down;
    }

    public int getColumn() {
        return column;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public DLLHeader getRight() {
        return right;
    }

    public DLLHeader getLeft() {
        return left;
    }

    public DLLNode getDown() {
        return down;
    }
}
