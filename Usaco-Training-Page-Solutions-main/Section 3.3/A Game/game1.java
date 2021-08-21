import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
LANG: JAVA
PROG: game1
*/

public class game1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("game1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
        int N = Integer.parseInt(br.readLine());

        int[][] sums = new int[N + 1][N + 1];
        int[][] dp = new int[N + 1][N + 1];
        for (int t = 0; t < N; ) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                sums[++t][t] = Integer.parseInt(st.nextToken());
                dp[t][t] = sums[t][t];
            }
        }
        System.out.println(Arrays.deepToString(sums).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        System.out.println("=====================================================");
        System.out.println(Arrays.deepToString(dp).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                sums[i][j] = sums[i][j - 1] + dp[j][j];
            }
        }

        for (int n = 1; n < N; n++) {
            for (int i = 1; i + n <= N; i++) {
                dp[i][i + n] = sums[i][i + n] - Math.min(dp[i][i + n - 1], dp[i + 1][i + n]);
            }
        }
        int player1 = dp[1][N];
        int player2 = (sums[1][N] - dp[1][N]);
        pw.println(player1 + " " + player2);
        pw.close();
    }
}
