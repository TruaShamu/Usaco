import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mooyomooyo {
    public static int N, K;
    public static int[][] grid;
    public static int[][] region;
    public static int[] regsizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new int[N][10];
        region = new int[N][10];
        regsizes = new int[1001];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < 10; j++) {
                grid[i][j] = s.charAt(j) - '0';
            }
        }

        while (iterate()) ;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(grid[i][j]);
                pw.print(grid[i][j]);
            }
            System.out.println();
            pw.println();
        }
        pw.close();
    }


    public static void visit(int i, int j, int reg, int color) {
        if (i < 0 || i >= N || j < 0 || j > 9 || grid[i][j] != color || region[i][j] != 0) {
            return;
        }
        region[i][j] = reg;
        regsizes[reg]++;
        visit(i - 1, j, reg, color);
        visit(i + 1, j, reg, color);
        visit(i, j - 1, reg, color);
        visit(i, j + 1, reg, color);
    }


    public static void gravity() {
        for (int column = 0; column < 10; column++) {
            int top = N - 1, bottom = N - 1;
            while (top >= 0) {
                //All of this is just empty spaces
                while (top >= 0 && grid[top][column] == 0) {
                    top--;
                }
                //This is the place where we start to have filled cells
                if (top >= 0) {
                    grid[bottom--][column] = grid[top--][column];
                }
            }
            while (bottom >= 0) {
                grid[bottom--][column] = 0;
            }
        }
    }

    public static boolean iterate() {
        int regions = 1;
        for (int i = 0; i < N; i++) {
            Arrays.fill(region[i], 0);
        }

        //Visit cells
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] != 0 && region[i][j] == 0) {
                    visit(i, j, regions++, grid[i][j]);
                }
            }
        }

        //Remove stuff
        boolean progress = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] != 0 && regsizes[region[i][j]] >= K) {
                    grid[i][j] = 0;
                    progress = true;
                }
            }
        }
        gravity();
        while (regions > 0) {
            regsizes[regions--] = 0;
        }

        return progress;
    }
}
