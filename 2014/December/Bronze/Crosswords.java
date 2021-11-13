import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[][] inputArray;
    public static int[][] coordinateArray;
    public static int rows, columns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("crosswords.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crosswords.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        columns = Integer.parseInt(st.nextToken());
        inputArray = new int[rows][columns];
        coordinateArray = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            String inputString = br.readLine();
            for (int column = 0; column < columns; column++) {
                char curChar = inputString.charAt(column);
                if (curChar == '#') {
                    inputArray[row][column] = 1;
                } else {
                    inputArray[row][column] = 0;
                }
            }
        }
        //printMatrix(inputArray);
        int answer = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                System.out.println("ROW: " + row + " COLUMN: " + column);
                if (fulfillsD(row, column) || fulfillsE(row, column)) {
                    answer++;
                    coordinateArray[row][column] = 1;
                }

            }
        }
        pw.println(answer);
        //printMatrix(coordinateArray);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (coordinateArray[row][column] == 1) {
                    pw.println((row + 1) + " " + (column + 1));
                }
            }
        }
        pw.close();

    }

    public static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix)
                .forEach(
                        (row) -> {
                            System.out.print("[");
                            Arrays.stream(row)
                                    .forEach((el) -> System.out.print(" " + el + " "));
                            System.out.println("]");
                        }
                );
    }

    public static boolean fulfillsD(int row, int column) {
        if (inputArray[row][column] == 1) {
            return false;
        }
        if (row != 0 && inputArray[row - 1][column] == 0) {
            return false;
        }
        if (row >= rows - 2) {
            return false;
        }
        if (inputArray[row + 1][column] == 1 || inputArray[row + 2][column] == 1) {
            return false;
        }
        return true;
    }

    public static boolean fulfillsE(int row, int column) {
        if (inputArray[row][column] == 1) {
            return false;
        }
        if (column != 0 && inputArray[row][column - 1] == 0) {
            return false;
        }
        if (column >= columns - 2) {
            return false;
        }
        if (inputArray[row][column + 1] == 1 || inputArray[row][column + 2] == 1) {
            return false;
        }
        return true;
    }
}

