import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class snakes {

    public static void main(String[] args) throws Exception {

        // Read in the grid.
        BufferedReader br = new BufferedReader(new FileReader("snakes.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("snakes.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] vals = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            vals[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 0;

        // Set up first level DP for k = 0
        int max = vals[0], sum = vals[0];
        for (int i = 1; i < n; i++) {
            sum += vals[i];
            max = Math.max(max, vals[i]);
            dp[0][i] = (i + 1) * max - sum;
        }

        // Fill in next DP row.
        for (int i = 1; i <= k; i++) {

            // Calc dp[i][j], min score with i changes for vals[0..j].
            for (int j = i + 1; j < n; j++) {

                // Default value, use segment of size one.
                dp[i][j] = dp[i - 1][j - 1];
                max = vals[j];
                sum = vals[j];
                int cnt = 1;

                // Try last segment of increasing length.
                for (int end = j - 1; end >= i; end--) {
                    sum += vals[end];
                    max = Math.max(max, vals[end]);
                    cnt++;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][end - 1] + cnt * max - sum);
                }
            }
        }

        // Solve and print out the result.

        pw.println(dp[k][n - 1]);
        pw.close();

    }
}
