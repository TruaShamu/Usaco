import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int MOD = 1000000007;
    public static long[] s = new long[10000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("spainting.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
        s[0] = 0;
        int N, M, K;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i < K; i++) {
            s[i] = (M * ((long) s[i - 1]) + M) % MOD;
        }
        for (int i = K; i <= N; i++) {
            s[i] = (M * ((long) s[i - 1]) + MOD - ((M - 1) * ((long) s[i - K])) % MOD) % MOD;
        }
        long ans = 1;
        for (int i = 1; i <= N; i++) {
            ans = (M * ((long) ans)) % MOD;
        }
        pw.println((((long) ans) + MOD - ((long) s[N]) + s[N - 1]) % MOD);
        pw.close();
    }
}
