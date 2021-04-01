import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class paintbarn {
    public static int MAX = 1005;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
        PrintWriter pw = new PrintWriter("paintbarn.out");
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] dp = new int[MAX + 1][MAX + 1];
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            dp[x1][y1]++;
            dp[x1][y2]--;
            dp[x2][y2]++;
            dp[x2][y1]--;
        }

        int ans = 0;
        for (int r = 0; r < MAX; r++) {
            for (int c = 0; c < MAX; c++) {
                if (r != 0) {
                    dp[r][c] += dp[r - 1][c];
                }
                if (c != 0) {
                    dp[r][c] += dp[r][c - 1];
                }
                if (r != 0 && c != 0) {
                    dp[r][c] -= dp[r - 1][c - 1];
                }
                if (dp[r][c] == K) {
                    ans++;
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}
