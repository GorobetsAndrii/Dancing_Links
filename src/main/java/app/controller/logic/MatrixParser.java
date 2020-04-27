package app.controller.logic;

import java.io.File;

public interface MatrixParser {

    int[][] parseMatrix(File file) throws Exception;
}
