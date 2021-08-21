import java.io.*;
import java.util.StringTokenizer;

/*
TASK: rockers
LANG: JAVA
 */
public class rockers {
    public static int N, T, M, best;
    public static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rockers.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // Number of songs
        T = Integer.parseInt(st.nextToken()); // Minutes/ disk
        M = Integer.parseInt(st.nextToken()); // Number of disks
        array = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        solve(0, 0, 0, 0);
        pw.println(best);
        pw.close();


    }

    public static void solve(int i, int n, int t, int m) {
        /*
        i is the current song
        n is the number of songs used so far
        t is the t-th minute on this disk
        m is the current disk
         */
        if (i == N) {
            best = Math.max(best, n);
            return;
        }
        solve(i + 1, n, t, m);
        if (T >= t + array[i]) {
            solve(i + 1, n + 1, t + array[i], m);
        } else if (m + 1 < M && T >= array[i]) {
            solve(i + 1, n + 1, array[i], m + 1);
        }
    }


}
