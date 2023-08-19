import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class time {
    public static ArrayList<Integer>[] adj;
    public static int MAXT = 1005;
    public static int MAXN = 1001;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("time.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("time.out"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        //@todo Read mooney array.
        int[] mooney = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            mooney[i] = Integer.parseInt(st.nextToken());
        }

        //@todo Initialize edges
        adj = new ArrayList[MAXN];
        for (int i = 0; i < MAXN; i++) {
            adj[i] = new ArrayList<>();
        }

        //@todo Read in edges.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
        }

        //@todo DP[time][node] max revenue.
        long[][] dp = new long[MAXT][MAXN];
        for (int i = 0; i < MAXT; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][1] = 0;

        long ans = 0;
        for (int i = 0; i < MAXT - 1; i++) {
            for (int j = 1; j <= N; j++) {
                if (dp[i][j] == -1) {
                    continue;
                }
                //Back to original point.
                if (j == 1) {
                    ans = Long.max(ans, dp[i][j] - (C * i * i));
                }

                for (int next : adj[j]) {
                    dp[i + 1][next] = Long.max(dp[i + 1][next], dp[i][j] + mooney[next]);
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}

