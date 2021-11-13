import java.io.*;
import java.util.StringTokenizer;

public class teamwork {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("teamwork.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("teamwork.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N];
        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            int mx = arr[i];
            for (int prev = i; prev >= 0 && (i + 1 - prev <= K); prev--) {
                mx = Integer.max(mx, arr[prev]); //MAX in A[prev -> i]
                int len = i + 1 - prev;

                if (prev == 0) {
                    dp[i] = Integer.max(dp[i], mx * (len));
                } else {
                    //Cut off the last segment at prev-1 and make  a NEW Segment from A[prev -> i]
                    dp[i] = Integer.max(dp[i], dp[prev - 1] + mx * (len));
                }
            }
        }

        pw.println(dp[N - 1]);
        pw.close();


    }
}
