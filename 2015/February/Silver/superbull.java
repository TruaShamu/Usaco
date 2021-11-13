import java.io.*;
import java.util.Arrays;

public class superbull {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("superbull.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        long result = 0;
        boolean[] used = new boolean[N];
        Arrays.fill(used, false);
        int[] D = new int[N];
        for (int i = 0; i < N; i++) {
            /* Find the index, j, of the 'furthest' node from the current spanning tree. */
            int j = -1;
            for (int k = 0; k < N; k++) {
                if (used[k]) continue;
                if (j == -1 || D[k] > D[j]) {
                    j = k;
                }
            }

            /* Update the result and 'relax' the edges out of vertex j. */
            result += D[j];
            used[j] = true;
            for (int k = 0; k < N; k++) {
                D[k] = Integer.max(D[k], A[j] ^ A[k]);
            }
        }

        System.out.println(result);
        pw.println(result);
        pw.close();
    }
}
