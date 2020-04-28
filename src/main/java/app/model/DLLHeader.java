package app.model;

public class DLLHeader extends DLLNode {
    private int numberOfElements;

    public DLLHeader(int column) {
        super(-1, column);
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

}
