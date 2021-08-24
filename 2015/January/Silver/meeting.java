import java.io.*;
import java.util.StringTokenizer;

public class meeting {
    public static int[][] bessieGrid;
    public static int[][] elsieGrid;
    public static int nodes, edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("meeting.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodes = Integer.parseInt(st.nextToken());
        edges = Integer.parseInt(st.nextToken());
        bessieGrid = new int[nodes][nodes];
        elsieGrid = new int[nodes][nodes];
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int bCost = Integer.parseInt(st.nextToken());
            int eCost = Integer.parseInt(st.nextToken());
            bessieGrid[from][to] = bCost;
            elsieGrid[from][to] = eCost;
        }
        boolean[] bessieCan = solve(bessieGrid);
        boolean[] elsieCan = solve(elsieGrid);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < bessieCan.length; i++) {
            if (bessieCan[i] && elsieCan[i]) {
                res = i;
                break;
            }
        }
        if (res == Integer.MAX_VALUE) {
            pw.println("IMPOSSIBLE");
        } else {
            pw.println(res);
        }
        pw.close();
    }

    public static boolean[] solve(int[][] dist) {
        //dp[i][j] means reach node i after spending cost j.
        boolean[][] dp = new boolean[nodes][100 * nodes + 1];
        dp[0][0] = true;
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (!dp[i][j]) continue;
                for (int k = i + 1; k < nodes; k++) {
                    if (dist[i][k] > 0) {
                        dp[k][j + dist[i][k]] = true;
                    }
                }
            }
        }
        return dp[nodes - 1];
    }
}
