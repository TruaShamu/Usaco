import java.io.*;
import java.util.Arrays;

/*
TASK: sort3
LANG: JAVA
LINK: https://train.usaco.org/usacoprob2?a=dbdWEvRd1vd&S=sort3
 */
public class sort3 {
    public static BufferedReader br;
    public static int swaps = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
        //Read input.
        int N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        int[] sorted = new int[N];

        for (int i = 0; i < N; i++) {
            sorted[i] = input[i] = Integer.parseInt(br.readLine()) - 1;
        }
        Arrays.sort(sorted);


        int[][] misplaced = new int[3][3]; //We have i, and need j.
        for (int i = 0; i < N; i++) {
            if (input[i] != sorted[i]) {
                misplaced[input[i]][sorted[i]]++;
            }
        }


        //Swap all the misplaced elements.
        int swap;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (misplaced[i][j] != 0 && misplaced[j][i] != 0) {
                    swap = Math.min(misplaced[i][j], misplaced[j][i]);
                    misplaced[i][j] = misplaced[i][j] - swap;
                    misplaced[j][i] = misplaced[j][i] - swap;
                    swaps += swap;
                }
            }

        }

        //The rest are not directly misplaced, so we have to spend 2 moves to correct each swap.
        int leftovers = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                leftovers += misplaced[i][j];
            }
        }
        swaps += 2 * (leftovers / 3);


        pw.println(swaps);
        pw.close();

    }

}
