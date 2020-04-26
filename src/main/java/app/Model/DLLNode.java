package app.Model;

public class DLLNode {
    private DLLMatrix right;
    private DLLMatrix left;
    private DLLMatrix up;
    private DLLMatrix down;
    private boolean data;

    public DLLNode(Boolean data) {
        this.data = data;
    }

    public DLLMatrix getRight() {
        return right;
    }

    public DLLMatrix getLeft() {
        return left;
    }

    public DLLMatrix getUp() {
        return up;
    }

    public DLLMatrix getDown() {
        return down;
    }

    public boolean isData() {
        return data;
    }

    public void setRight(DLLMatrix right) {
        this.right = right;
    }

    public void setLeft(DLLMatrix left) {
        this.left = left;
    }

    public void setUp(DLLMatrix up) {
        this.up = up;
    }

    public void setDown(DLLMatrix down) {
        this.down = down;
    }

    public void setData(boolean data) {
        this.data = data;
    }
}
