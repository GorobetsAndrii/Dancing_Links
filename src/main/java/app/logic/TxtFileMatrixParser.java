package app.logic;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TxtFileMatrixParser implements MatrixParser {

    private int[][] matrix;

    @Override
    public int[][] parseMatrix(File file) throws Exception {
        List<int[]> rows = new LinkedList<>();
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            rows.add(getMatrixRow(myReader.nextLine()));
        }
        myReader.close();
        matrix = new int[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            matrix[i] = rows.get(i);
        }
        return matrix;
    }


    private int[] getMatrixRow(String stringRow) throws Exception {
        char[] chars = stringRow.toCharArray();
        int[] row = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            row[i] = getCellValue(chars[i]);
        }
        return row;
    }

    private int getCellValue(char c) throws Exception {
        int i = Integer.parseInt(String.valueOf(c));
        if (i != 0 && i != 1) {
            throw new Exception("");
        }
        return i;
    }
}
