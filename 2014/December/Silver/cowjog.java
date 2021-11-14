import java.io.*;
import java.util.StringTokenizer;

public class cowjog {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjog.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long T = Integer.parseInt(st.nextToken());
        long[] cows = new long[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long pos = Integer.parseInt(st.nextToken());
            long speed = Integer.parseInt(st.nextToken());
            cows[i] = pos + (speed * T);
        }
        long res = 1;
        long slow = cows[N - 1];
        for (int i = N - 1; i >= 0; i--) {
            if (cows[i] < slow) {
                res++;
            }
            slow = Long.min(slow, cows[i]);
        }
        System.out.println("ANSWER: " + res);
        pw.println(res);
        pw.close();

    }
}
