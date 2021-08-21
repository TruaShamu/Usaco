import java.io.*;
import java.util.StringTokenizer;

/*
LANG: JAVA
TASK: humble
*/
public class humble {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("humble.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] subset = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            subset[i] = Integer.parseInt(st.nextToken());
        }
        //Finished reading in input
        int[] humbleNumbers = new int[N + 1];
        int[] next = new int[K];
        humbleNumbers[0] = 1;
        for (int i = 1; i <= N; i++) {
            int best = Integer.MAX_VALUE;
            for (int j = 0; j < K; j++) {
                while (next[j] < i && subset[j] * humbleNumbers[next[j]] <= humbleNumbers[i - 1]) {
                    next[j]++;
                }
                best = Math.min(best, subset[j] * humbleNumbers[next[j]]);
            }
            humbleNumbers[i] = best;
        }
        pw.println(humbleNumbers[N]);
        pw.close();

    }
}
