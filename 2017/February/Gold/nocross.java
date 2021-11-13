import java.io.*;

public class nocross {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("nocross.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("nocross.out"));
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        int[] T = new int[N];
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[N][N];
        // (i,0)
        for (int i = 1; i < N; i++) {
            dp[i][0] = Integer.max(dp[i - 1][0], ((Math.abs(S[i] - T[0]) <= 4)) ? 1 : 0);
        }

        //(0, j)
        for (int j = 1; j < N; j++) {
            dp[0][j] = Integer.max(dp[0][j - 1], ((Math.abs(S[0] - T[j]) <= 4)) ? 1 : 0);
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                dp[i][j] = Integer.max(Integer.max(dp[i - 1][j], dp[i][j - 1]), (dp[i - 1][j - 1]) + (Math.abs(S[i] - T[j]) <= 4 ? 1 : 0));

            }
        }

        pw.println(dp[N - 1][N - 1]);
        pw.close();


    }
}
