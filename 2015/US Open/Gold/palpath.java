import java.io.*;

public class palpath {
    public static int N;
    public static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("palpath.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));
        //Read input
        N = Integer.parseInt(br.readLine());
        char[][] grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = s.charAt(j);
            }
        }

        long[][] dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }

        for (int num = N - 1; num >= 1; num--) {
            long[][] next = new long[N][N];
            for (int a = 0; a < N; a++) {
                int rowA = a;
                int colA = (num - 1 - a);
                if (colA < 0) {
                    continue;
                }
                for (int b = 0; b < N; b++) {
                    int rowB = b;
                    int colB = 2 * N - num - rowB - 1;
                    if (colB >= N) continue;
                    if (grid[rowA][colA] != grid[rowB][colB]) {
                        continue;
                    }
                    next[rowA][rowB] += dp[rowA][rowB];
                    if (rowA + 1 < N) {
                        next[rowA][rowB] += dp[rowA + 1][rowB];
                    }
                    if (rowB - 1 >= 0) {
                        next[rowA][rowB] += dp[rowA][rowB - 1];
                    }
                    if (rowA + 1 < N && rowB - 1 >= 0) {
                        next[rowA][rowB] += dp[rowA + 1][rowB - 1];
                    }
                    next[rowA][rowB] %= MOD;
                }
            }
            dp = next;
        }

        pw.println(dp[0][N - 1]);
        pw.close();
    }
}
