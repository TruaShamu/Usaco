import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
    public static int MOD = (int) (2e+9 + 11);
    public static int inf = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] cows = new int[2 * N][M]; //Input matrix
        //Hashing
        int[] values = new int[1000];
        values['A'] = 1;
        values['C'] = 2;
        values['G'] = 4;
        values['T'] = 8;
        //Hashing
        int[] hash = new int[M];
        for (int i = 0; i < M; i++) {
            Random rand = new Random();
            hash[i] = rand.nextInt(inf) % MOD;
        }
        //Read matrix
        for (int i = 0; i < 2 * N; i++) {
            int sum = 0;
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                sum += values[c] * hash[j];
                cows[i][j] = sum;
            }
        }

        HashSet<Integer> seen = new HashSet<>();
        int best = inf;
        int i = 0, j = 0;

        while (j < M) {
            seen.clear();
            boolean flag = false;
            for (int k = 0; k < N; k++) {
                int val = cows[k][j] - cows[k][i]; //Substring key
                seen.add(val);
            }
            for (int k = 0; k < N; k++) {
                int val = cows[N + k][j] - cows[N + k][i]; //Searching for key
                if (seen.contains(val)) {
                    flag = true;
                    j++;
                    break;
                }
            }
            if (flag) {
                continue;
            }
            best = Integer.min(best, j - i);
            i++;
        }

        pw.println(best);
        pw.close();
    }
}
