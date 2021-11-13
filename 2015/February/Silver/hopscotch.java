import java.io.*;
import java.util.StringTokenizer;

public class hopscotch {
    public static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hopscotch.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] grid = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int[][] dp = new int[R][C];
        dp[0][0] = 1;
        for (int curR = 0; curR < R; curR++) {
            for (int curC = 0; curC < C; curC++) {
                for (int newR = curR + 1; newR < R; newR++) {
                    for (int newC = curC + 1; newC < C; newC++) {
                        if (grid[curR][curC] != grid[newR][newC]) {
                            dp[newR][newC] += dp[curR][curC];
                            dp[newR][newC] %= MOD;
                        }
                    }
                }
            }
        }
        pw.println(dp[R - 1][C - 1]);
        pw.close();
    }
}
