import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[][] prefix = new int[4][n + 1];
        for (int i = 1; i <= n; i++) {
            int curr = Integer.parseInt(br.readLine());
            // shift over the prefix sums for each value from 1 to 3
            for (int j = 1; j <= 3; j++) {
                prefix[j][i] = prefix[j][i - 1];
            }
            // increment the prefix sum for the number that we read in
            prefix[curr][i]++;
        }
        System.out.println(Arrays.toString(prefix[1]));
        System.out.println(Arrays.toString(prefix[2]));
        System.out.println(Arrays.toString(prefix[3]));
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pw.println((prefix[1][b] - prefix[1][a - 1]) + " " + (prefix[2][b] - prefix[2][a - 1]) + " " + (prefix[3][b] - prefix[3][a - 1]));
        }
        pw.close();
    }
}
