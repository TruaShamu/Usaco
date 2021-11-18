import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int rows;
    final public static int[] DX = {-1, 0, 0, 1};
    final public static int[] DY = {0, -1, 1, 0};
    public static boolean[][][] grid;
    public static int[][] cows;
    public static boolean[][] cowLocGrid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
        PrintWriter pw = new PrintWriter("countcross.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken()); //n
        int numCows = Integer.parseInt(st.nextToken()); //k
        int roads = Integer.parseInt(st.nextToken()); //r
        grid = new boolean[rows][rows][4];
        cowLocGrid = new boolean[rows][rows];
        cows = new int[numCows][2];
        initialize();

        for (int i = 0; i < roads; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;
            int deltaX = x2 - x1;
            int deltaY = y2 - y1;
            for (int j = 0; j < 4; j++) {
                if (deltaX == DX[j] && deltaY == DY[j])
                    grid[x1][y1][j] = false;
                if (-deltaX == DX[j] && -deltaY == DY[j])
                    grid[x2][y2][j] = false;
            }
        }
        for (int i = 0; i < numCows; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            cows[i][0] = x;
            cows[i][1] = y;
            cowLocGrid[x][y] = true;
        }
        int res = 0;

        for (int i = 0; i < numCows; i++) {
            int total = bfs(cows[i][0], cows[i][1]);
            res += (numCows - total);
        }

        pw.println(res / 2);
        pw.close();

    }

    public static void initialize() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < rows; j++)
                for (int k = 0; k < DX.length; k++)
                    grid[i][j][k] = onGrid(i + DX[k], j + DY[k]);
    }

    public static boolean onGrid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < rows;
    }

    public static int bfs(int x, int y) {
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add((x * rows) + y); //The ith cell
        boolean[][] visited = new boolean[rows][rows];
        visited[x][y] = true;

        while (list.size() > 0) {

            int cur = list.remove(0);
            int curX = cur / rows;
            int curY = cur % rows;

            if (cowLocGrid[curX][curY]) count++; //Is there a cow in this loc?

            for (int i = 0; i < 4; i++) {
                if (grid[curX][curY][i] && !visited[curX + DX[i]][curY + DY[i]]) {
                    list.add(rows * (curX + DX[i]) + curY + DY[i]);
                    visited[curX + DX[i]][curY + DY[i]] = true;
                }
            }
        }

        return count;
    }

}
