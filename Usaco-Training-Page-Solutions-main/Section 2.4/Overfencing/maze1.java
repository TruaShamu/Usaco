import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
TASK: maze1
LANG: JAVA
 */
public class maze1 {
    public static int[][] board;
    public static char[][] maze;
    public static int newHeight, newWidth;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
        PrintWriter pw = new PrintWriter("maze1.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        newHeight = 2 * height + 1;
        newWidth = 2 * width + 1;
        maze = new char[newHeight][newWidth];
        int[][] cnt = new int[newHeight][newWidth];
        int x1 = 0, y1 = 0; //Exit 1
        int x2 = 0, y2 = 0; //Exit 2
        for (int i = 0; i < newHeight; i++) { //row
            String input = br.readLine();
            for (int j = 0; j < newWidth; j++) { //column
                char curChar = input.charAt(j);
                //System.out.println(curChar);
                maze[i][j] = curChar;
                cnt[i][j] = Integer.MAX_VALUE;
                if (curChar == ' ') {

                    //This cell is a room, or else it is one of the exits?
                    int tempX = 0;
                    int tempY = 0;
                    if (i == 0) {
                        tempX = i + 1;
                        tempY = j;
                    }
                    if (i == 2 * height) {
                        tempX = i - 1;
                        tempY = j;
                    }
                    if (j == 0) {
                        tempX = i;
                        tempY = j + 1;
                    }
                    if (j == 2 * width) {
                        tempX = i;
                        tempY = j - 1;
                    }
                    if (tempX != 0 || tempY != 0) {
                        if (x1 == 0 && y1 == 0) {
                            x1 = tempX;
                            y1 = tempY;
                        } else {
                            x2 = tempX;
                            y2 = tempY;
                        }
                    }
                }
            }
        }
        //printMatrix(cnt);
        System.out.println("Exit 1:    ROW: " + x1 + " COLUMN: " + y1);
        System.out.println("Exit 2:    ROW: " + x2 + " COLUMN: " + y2);
        //System.exit(0);
        cnt[x1][y1] = 1;
        flood(x1, y1, cnt, maze, width, height); //Flood fill from exit 1
        //printMatrix(cnt);
        cnt[x2][y2] = 1;
        flood(x2, y2, cnt, maze, width, height); //Flood fill from exit2
        int max = 0;
        for (int i = 1; i < newHeight; i += 2) {
            for (int j = 1; j < newWidth; j += 2) {
                //Find largest value
                if (cnt[i][j] != Integer.MAX_VALUE) {
                    max = Integer.max(cnt[i][j], max);
                }
            }
        }
        pw.println(max);
        pw.close();


    }

    public static void printMatrix(int[][] matrix) {
        Arrays.stream(matrix).forEach(
                (row) -> {
                    System.out.print("[");
                    Arrays.stream(row)
                            .forEach((el) -> System.out.print(" " + el + " "));
                    System.out.println("]");
                });
    }

    public static void flood(int x, int y, int[][] cnt, char[][] maze, int width, int height) {
        if (cnt[x][y] != 0) {
            if (x + 2 < newHeight && maze[x + 1][y] == ' ') {
                if (cnt[x + 2][y] > cnt[x][y] + 1) {
                    cnt[x + 2][y] = cnt[x][y] + 1;
                    //Update the new value
                    flood(x + 2, y, cnt, maze, width, height);
                }

            }
            if (x - 2 >= 0 && maze[x - 1][y] == ' ') {
                if (cnt[x - 2][y] > cnt[x][y] + 1) {
                    cnt[x - 2][y] = cnt[x][y] + 1;
                    flood(x - 2, y, cnt, maze, width, height);
                }

            }
            if (y + 2 < newWidth && maze[x][y + 1] == ' ') {
                if (cnt[x][y + 2] > cnt[x][y] + 1) {
                    cnt[x][y + 2] = cnt[x][y] + 1;
                    flood(x, y + 2, cnt, maze, width, height);
                }

            }
            if (y - 2 >= 0 && maze[x][y - 1] == ' ') {
                if (cnt[x][y - 2] > cnt[x][y] + 1) {
                    cnt[x][y - 2] = cnt[x][y] + 1;
                    flood(x, y - 2, cnt, maze, width, height);
                }
            }
        }
    }
}
