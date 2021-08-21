
/*
LANG: JAVA
TASK: ditch
*/

import java.io.*;
import java.util.StringTokenizer;

public class ditch {
    public static int[][] map = new int[220][220];
    public static boolean visited[];

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        BufferedReader reader = new BufferedReader(new FileReader("ditch.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int edges = Integer.valueOf(st.nextToken());
        int nodes = Integer.valueOf(st.nextToken());
        for (int i = 0; i < edges; i++) {
            //Make edges
            st = new StringTokenizer(reader.readLine());
            int from = Integer.valueOf(st.nextToken());
            int to = Integer.valueOf(st.nextToken());
            int weight = Integer.valueOf(st.nextToken());
            map[from][to] += weight;
        }

        int flow = 0;
        while (true) {
            visited = new boolean[nodes + 1];
            int f = dfs(1, nodes, Integer.MAX_VALUE);
            if (f == 0) break;
            flow += f;
        }
        pw.println(flow);
        pw.close();
        System.out.println(flow);
        System.out.println("TIME: " + (System.currentTimeMillis() - startTime));
        System.exit(0);
    }

    public static int dfs(int node, int end, int weight) {
        if (node == end) {
            return weight;
        }
        visited[node] = true;
        for (int i = 1; i <= end; i++) {
            if (visited[i] || map[node][i] <= 0) {
                continue;
            }
            int tmp = dfs(i, end, Math.min(weight, map[node][i]));
            if (tmp > 0) {
                map[node][i] -= tmp;
                map[i][node] += tmp;
                return tmp;
            }
        }
        return 0;
    }
}




