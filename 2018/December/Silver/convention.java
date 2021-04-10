import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class convention {
    public static int N, M, C;
    public static int[] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("convention.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("convention.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cows = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cows);

        //Binary search for the waiting time
        long lower = 0;
        long upper = (long) 10e9;
        while (lower != upper) {
            long mid = (lower + upper) / 2;
            if (works(mid)) {
                System.out.println("WORKS");
                upper = mid;
            } else {
                System.out.println("NOT WORK");
                lower = mid + 1;
            }
        }
        System.out.println(lower);
        pw.println(lower);
        pw.close();

    }

    public static boolean works(long time) {
        int firstCow = 0;
        for (int i = 0; i < M; i++) {
            int lastCow = firstCow;
            while (lastCow < N && (cows[lastCow] - cows[firstCow] <= time) && (lastCow - firstCow < C)) {
                lastCow++;
            }
            if (lastCow == N) {
                return true;
            }
            firstCow = lastCow;
        }
        return false;
    }
}
