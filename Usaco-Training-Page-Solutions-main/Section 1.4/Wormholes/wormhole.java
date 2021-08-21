import java.io.*;
import java.util.StringTokenizer;

public class wormhole {
    public static int N;
    public static int[] X, Y;
    public static int[] nextRight;
    public static int[] partner; //the wormhole's partner

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
        N = Integer.parseInt(br.readLine());
        X = new int[N + 1];
        Y = new int[N + 1];
        partner = new int[N + 1];
        nextRight = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            X[i] = Integer.parseInt(st.nextToken());
            Y[i] = Integer.parseInt(st.nextToken());
        }

        //Find closest points to right
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (X[j] > X[i] && Y[i] == Y[j]) {
                    if (nextRight[i] == 0 || (X[j] - X[i]) < (X[nextRight[i]] - X[i])) {
                        nextRight[i] = j;
                    }
                }
            }
        }

        pw.println(solve());
        pw.close();
    }

    public static int solve() {
        int total = 0;
        int i;
        for (i = 1; i <= N; i++) {
            if (partner[i] == 0) {
                break;
            }
        }

        if (i > N) {
            if (isCycle()) {
                return 1;
            } else return 0;
        }

        for (int j = i + 1; j <= N; j++) {
            if (partner[j] == 0) {
                //Make i and j partners and then recurse.
                partner[i] = j;
                partner[j] = i;
                total += solve();
                partner[i] = partner[j] = 0;
            }
        }
        return total;

    }


    public static boolean isCycle() {
        for (int start = 1; start <= N; start++) {
            int pos = start;
            for (int count = 0; count < N; count++) {
                pos = nextRight[partner[pos]];
            }
            if (pos != 0) {
                return true;
            }
        }
        return false;
    }
}

