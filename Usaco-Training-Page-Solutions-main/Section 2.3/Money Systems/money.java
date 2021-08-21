import java.io.*;
import java.util.StringTokenizer;

/*
TASK: money
LANG: JAVA
 */
public class money {
    public static BufferedReader br;
    public static long[] dp;
    public static StringTokenizer st1;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        br = new BufferedReader(new FileReader("money.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int coins = Integer.parseInt(st.nextToken());
        int amount = Integer.parseInt(st.nextToken());
        dp = new long[amount + 1];
        dp[0] = 1;
        st1 = new StringTokenizer(br.readLine());

        for (int i = 1; i <= coins; i++) {
            int t = readNext();
            //System.out.println(t);
            for (int j = t; j <= amount; j++) {
                dp[j] += dp[j - t];
            }
        }
        pw.println(dp[amount]);
        pw.close();

    }

    public static int readNext() throws IOException {
        if (st1.hasMoreTokens()) {
            return Integer.parseInt(st1.nextToken());
        } else {
            st1 = new StringTokenizer(br.readLine());
            return Integer.parseInt(st1.nextToken());
        }

    }
}
/*
do - doot -
 */
