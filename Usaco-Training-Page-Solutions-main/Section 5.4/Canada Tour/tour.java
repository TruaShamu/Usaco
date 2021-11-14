/*
LANG: JAVA
TASK: tour
 */

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class tour {
    public static int nodes, edges;
    public static HashMap<String, Integer> map = new HashMap<>();
    public static int graph[][];
    public static int arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tour.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("tour.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodes = Integer.parseInt(st.nextToken());
        edges = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= nodes; i++) {
            String name = br.readLine();
            map.put(name, i);
        }
        graph = new int[nodes + 1][nodes + 1];
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = map.get(st.nextToken());
            int node2 = map.get(st.nextToken());
            graph[node1][node2] = 1;
            graph[node2][node1] = 1;
        }
        arr = new int[nodes + 1][nodes + 1];
        pw.println(dp());
        pw.close();
    }


    public static int dp() {
        arr[1][1] = 1;
        for (int i = 1; i <= nodes; i++) {
            for (int j = i + 1; j <= nodes; j++) {
                for (int k = 1; k < j; k++) {
                    if (graph[k][j] == 1 && arr[i][k] > 0) {
                        arr[i][j] = arr[j][i] = Math.max(arr[i][j], arr[i][k] + 1);
                    }
                }
            }
        }
        int result = 1;
        for (int i = 1; i <= nodes; i++) {
            if (arr[i][nodes] > result && graph[i][nodes] == 1) result = arr[i][nodes];
        }
        //System.out.println(result);
        return result;
    }
}


