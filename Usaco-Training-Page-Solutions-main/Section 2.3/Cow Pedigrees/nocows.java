import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
TASK: nocows
LANG: JAVA
 */
public class nocows {
    public static long[][] results;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        StringTokenizer line = new StringTokenizer(br.readLine());
        int nodes = Integer.parseInt(line.nextToken());
        int height = Integer.parseInt(line.nextToken());
        results = new long[nodes + 1][height + 1];
        for (int i = 0; i < nodes + 1; i++) {
            Arrays.fill(results[i], -1);
        }
        pw.println(solve(nodes, height));
        for (int row = 0; row < nodes + 1; row++) {
            System.out.println(Arrays.toString(results[row]));
        }
        pw.close();

    }

    public static long solve(int n, int k) {
        if (results[n][k] != -1) {
            return results[n][k];
        }
        if (n < 1 || k < 1 || 2 * k - 1 > n || n % 2 == 0) {
            return results[n][k] = 0;
        }
        if (n == 1) {
            if (k == 1) {
                return results[n][k] = 1;
            }
            return results[n][k] = 0;
        }
        results[n][k] = 0;
        for (int i = 1; i < n - 1; i += 2) {
            int temp = n - i - 1;
            for (int j = 0; j < k - 1; j++) {
                results[n][k] += solve(i, j) * solve(temp, k - 1);
                results[n][k] += solve(i, k - 1) * solve(temp, j);
            }
            results[n][k] += solve(i, k - 1) * solve(temp, k - 1);
        }
        results[n][k] %= 9901;
        return results[n][k];
    }
}
