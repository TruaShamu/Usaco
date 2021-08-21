import java.io.*;

/*
LANG: JAVA
PROG: range
*/
public class range {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("range.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
        int rows = Integer.parseInt(br.readLine());
        boolean[][] grid = new boolean[rows][rows]; //Is this cell a square
        for (int row = 0; row < rows; row++) {
            String input = br.readLine();
            for (int column = 0; column < rows; column++) {
                grid[row][column] = input.charAt(column) != '0';

            }
        }
        //Finish reading input

        int[] count = new int[rows + 1];
        for (int i = 2; i <= rows; i++) {
            boolean[][] next = new boolean[grid.length - 1][grid.length - 1];
            for (int x = 0; x < rows - i + 1; x++) {
                for (int y = 0; y < rows - i + 1; y++) {
                    if (grid[x][y] && grid[x + 1][y] && grid[x][y + 1] && grid[x + 1][y + 1]) {
                        //Do the 4 corners make a square
                        count[i]++;
                        next[x][y] = true; //It's a square
                    } else {
                        next[x][y] = false;
                    }
                }
            }
            grid = next;
        }
        for (int i = 2; i <= rows; i++) {
            if (count[i] != 0) {
                pw.println(i + " " + count[i]);
            }
        }
        pw.close();
        System.out.println(System.currentTimeMillis() - startTime);
        System.exit(0);

    }
}
