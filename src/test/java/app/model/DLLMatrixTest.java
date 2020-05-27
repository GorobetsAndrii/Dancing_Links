package app.model;

import org.junit.Assert;
import org.junit.Test;

public class DLLMatrixTest {
    private DLLMatrix matrix;

    @Test
    public void testIfDLLMatrixCreated() throws Exception {
        int[][] arr = {{1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}};

        matrix = new DLLMatrix(arr);
        Assert.assertEquals(0, matrix.getHead().getDown().getColumn());
        Assert.assertEquals(2, matrix.getHead().getNumberOfElements());
        Assert.assertEquals(2, matrix.getHead().getRight().getRight().getDown().getColumn());
    }

    @Test
    public void testExceptionCreatedMatrix() {
        int[][] arr = {{1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}};
        try {
            matrix = new DLLMatrix(arr);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Does not have solve");
        }
    }

    /*
    @Test
    public void testLast2FirstUDLink() throws Exception {
        int[][] arr = {{1, 0, 1},
                {0, 1, 0},
                {1, 0, 1}};

        matrix = new DLLMatrix(arr);

        Assert.assertEquals(matrix.getHead(), matrix.getHead().getDown().getDown().getDown());
    }
     */
}
