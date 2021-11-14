import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class marathon {
    public static int N, K;
    public static int[] xVals, yVals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("marathon.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        xVals = new int[N];
        yVals = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xVals[i] = x;
            yVals[i] = y;
        }
        int[][] dp = new int[K + 1][N]; //dp[i][j] = Skip i points, distance to get to point j.
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], 1 << 30);
        }

        dp[0][0] = 0;
        for (int i = 0; i < K + 1; i++) {
            for (int j = 0; j < N; j++) {
                for (int l = j + 1; l < N && i + l - j - 1 <= K; l++) {
                    int nextI = i + (l - j - 1);
                    int nextJ = l;
                    dp[nextI][nextJ] = Math.min(dp[nextI][nextJ], dp[i][j] + distBetween(j, l));
                }
            }
        }
        System.out.println("ANSWER: " + dp[K][N - 1]);
        pw.println(dp[K][N - 1]);
        pw.close();
    }

    public static int distBetween(int i, int j) {
        return dist(xVals[i], yVals[i], xVals[j], yVals[j]);
    }

    public static int dist(int x1, int y1, int x2, int y2) {
        int x = x1 - x2;
        int y = y1 - y2;
        return Math.abs(x) + Math.abs(y);
    }
}
