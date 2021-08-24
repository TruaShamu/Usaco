import java.io.*;
import java.util.StringTokenizer;

public class taming {
    public static int[][][] dp;
    public static int INF = 1000;

    public static void main(String[] args) throws IOException {
        dp = new int[100][100][101];
        BufferedReader br = new BufferedReader(new FileReader("taming.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= N; k++) {
                    dp[i][j][k] = INF;

                }
            }
        }
        if (A[0] == 0)
            dp[0][0][1] = 0;
        else
            dp[0][0][1] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++)
                for (int k = 1; k <= i + 1; k++) {
                    if (j < i)
                        dp[i][j][k] = dp[i - 1][j][k];
                    else
                        for (int j1 = 0; j1 < i; j1++)
                            dp[i][j][k] = Integer.min(dp[i][j][k], dp[i - 1][j1][k - 1]);
                    if (A[i] != i - j)
                        dp[i][j][k]++;
                }
        }
        for (int k = 1; k <= N; k++) {
            int low = INF;
            for (int j = 0; j < N; j++)
                low = Integer.min(low, dp[N - 1][j][k]);
            pw.println(low);
        }
        pw.close();
    }

}
