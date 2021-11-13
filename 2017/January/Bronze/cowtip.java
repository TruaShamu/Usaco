import java.io.*;

public class Main {
    public static int rows;
    public static int[][] gridArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowtip.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));
        rows = Integer.parseInt(br.readLine());
        gridArray = new int[rows][rows];
        int times = 0;
        for (int i = 0; i < rows; i++) {
            String input = br.readLine();
            for (int j = 0; j < rows; j++) {
                int c = input.charAt(j) - 48;
                gridArray[i][j] = c;
            }
        }
        for (int row = rows - 1; row >= 0; row--) {
            for (int column = rows - 1; column >= 0; column--) {
                if (gridArray[row][column] == 1) {
                    flip(row, column);
                    System.out.println("ROW: " + "COLUMN:" + column);
                    times++;
                    for (int i = 0; i < rows; i++) {

                    }
                }
            }

        }
        pw.println(times);
        pw.close();
    }

    public static void flip(int fRow, int fColumn) {
        for (int column = 0; column <= fColumn; column++) {
            for (int row = 0; row <= fRow; row++) {
                if (gridArray[row][column] == 1) {
                    gridArray[row][column] = 0;
                } else {
                    gridArray[row][column] = 1;
                }
            }

        }
    }
}
