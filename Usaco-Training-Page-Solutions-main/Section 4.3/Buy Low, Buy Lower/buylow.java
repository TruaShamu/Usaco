import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
TASK: buylow
LANG: JAVA
 */
public class buylow {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("buylow.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
        int days = Integer.parseInt(br.readLine());
        //ArrayList<Integer> list = new ArrayList<>();
        int[] array = new int[days + 2];
        int[] len = new int[days + 2];
        BigInteger[] dp = new BigInteger[days + 2];
        String line = br.readLine();
        int count = 1;
        while (line != null) {
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                array[count] = Integer.parseInt(st.nextToken());
                len[count] = 1;
                dp[count] = BigInteger.ONE;
                count++;

            }
            line = br.readLine();
        }
        System.out.println(Arrays.toString(array));
        //Finish read input
        dp[days + 1] = BigInteger.ONE;
        ++days;
        int ans = 0;
        for (int i = 2; i <= days; ++i) {
            TreeSet<Integer> S = new TreeSet<Integer>();
            for (int j = i - 1; j > 0; --j) {
                if (array[i] < array[j] && len[i] <= len[j] + 1) {
                    if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        dp[i] = dp[j];
                        if (len[i] > len[ans]) {
                            ans = i;
                        }
                    } else if (!S.contains(array[j])) {
                        dp[i] = dp[i].add(dp[j]);
                    }
                    S.add(array[j]);
                }
            }
        }
        pw.println(len[ans] - 1 + " " + dp[ans]);
        pw.close();
    }
    public static void printBMatrix(boolean[][] matrix) {
        for (boolean[] x : matrix) {
            for (boolean y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
}
