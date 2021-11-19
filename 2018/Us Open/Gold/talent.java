import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class talent {

    final public static int NONE = 2000000000;

    public static int N;
    public static int maxW;
    public static int[] weights;
    public static int[] talents;

    public static void main(String[] args) throws Exception {

        // Get basic input.
        BufferedReader br = new BufferedReader(new FileReader("talent.in"));
        PrintWriter pw = new PrintWriter(new File("talent.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        maxW = Integer.parseInt(st.nextToken());
        weights = new int[N];
        talents = new int[N];

        // Read in weights, talents.
        int sumT = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            talents[i] = Integer.parseInt(st.nextToken());
            sumT += talents[i];
        }

        //Min weight to get talent i.
        int[] dp = new int[sumT + 1];
        Arrays.fill(dp, NONE);
        dp[0] = 0;

        //Basically knapsack
        for (int i = 0; i < N; i++) {
            for (int j = sumT; j >= talents[i]; j--) {
                dp[j] = Math.min(dp[j], dp[j - talents[i]] + weights[i]);
            }
        }
        // Find highest ratio.

        int res = 0;
        for (int i = 1; i <= sumT; i++) {
            if (dp[i] < maxW) {
                continue;
            }
            res = Math.max(res, (1000 * i) / dp[i]);
        }


        pw.println(res);
        pw.close();

    }
}
