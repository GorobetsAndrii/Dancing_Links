package app.controller.logic;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class TxtFileMatrixParserTest {


    private File file;

    private final String RESOURCE_PATH = "src/test/resources/";
    TxtFileMatrixParser parser = new TxtFileMatrixParser();

    @Test
    public void testIfFileIsProperlyParsed() throws Exception {
        file = new File(RESOURCE_PATH + "matrix.txt");
        int[][] matrix = parser.parseMatrix(file);
        Assert.assertArrayEquals(new int[]{1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0}, matrix[0]);

        Assert.assertArrayEquals(new int[]{0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1}, matrix[4]);
    }

}