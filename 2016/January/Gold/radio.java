import java.io.*;
import java.util.StringTokenizer;

public class radio {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("radio.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] john = new int[N + 1][2]; //fj's locs.
        st = new StringTokenizer(br.readLine());
        john[0][0] = Integer.parseInt(st.nextToken());
        john[0][1] = Integer.parseInt(st.nextToken());

        int[][] bessie = new int[M + 1][2]; //bessie's locs
        st = new StringTokenizer(br.readLine());
        bessie[0][0] = Integer.parseInt(st.nextToken());
        bessie[0][1] = Integer.parseInt(st.nextToken());

        String johnDir = br.readLine();
        String bessieDir = br.readLine();

        fill(john, johnDir);
        fill(bessie, bessieDir);
        print(bessie);

        int[][] dp = new int[N + 1][M + 1]; // energy

        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + cost(john[i], bessie[0]);
        }
        for (int j = 1; j <= M; j++) {
            dp[0][j] = dp[0][j - 1] + cost(john[0], bessie[j]);
        }

        // Run through all possible states.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int thisCost = cost(john[i], bessie[j]);
                int bothMove = dp[i - 1][j - 1] + thisCost;
                int johnMove = dp[i - 1][j] + thisCost;
                int bessieMove = dp[i][j - 1] + thisCost;
                dp[i][j] = Math.min(Math.min(bothMove, johnMove), bessieMove);
            }
        }


        pw.println(dp[N][M]);
        pw.close();

    }

    public static void fill(int[][] loc, String dir) {
        for (int i = 1; i <= dir.length(); i++) {
            // Copy old loc.
            loc[i][0] = loc[i - 1][0];
            loc[i][1] = loc[i - 1][1];
            char c = dir.charAt(i - 1);
            if (c == 'N') loc[i][1]++;
            else if (c == 'E') loc[i][0]++;
            else if (c == 'S') loc[i][1]--;
            else if (c == 'W') loc[i][0]--;
        }
    }

    public static int cost(int[] a, int[] b) {
        //Find distance cost
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }

    public static void print(int[][] array) {
        for (int[] x : array) {
            for (int y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
}
