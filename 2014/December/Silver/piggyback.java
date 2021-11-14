import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

//Piggyback
public class piggyback {
    public static ArrayList[] edgeList;
    public static int nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("piggyback.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("piggyback.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int bCost = Integer.parseInt(st.nextToken());
        int eCost = Integer.parseInt(st.nextToken());
        int pCost = Integer.parseInt(st.nextToken());
        nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            edgeList[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            edgeList[node1].add(node2);
            edgeList[node2].add(node1);
        }


        int[] bDist = bfs(0);
        int[] eDist = bfs(1);
        int[] dDist = bfs(nodes - 1);

        int res = bCost * bDist[nodes - 1] + eCost * eDist[nodes - 1]; //If both cows don't converge

        for (int mid = 0; mid < nodes - 1; mid++) {

            if (bDist[mid] == -1 || eDist[mid] == -1 || dDist[mid] == -1) continue;

            // See if meeting at vertex mid helps.
            res = Math.min(res, bCost * bDist[mid] + eCost * eDist[mid] + pCost * dDist[mid]);
        }
        pw.println(res);
        pw.close();


    }


    public static int[] bfs(int node) {

        int[] dist = new int[nodes];
        Arrays.fill(dist, -1);
        dist[node] = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(node);

        // Run BFS.
        while (queue.size() > 0) {

            // Get next item.
            int cur = queue.remove();

            // Enqueue all of its unvisited neighbors, marking distance.
            for (Integer next : (ArrayList<Integer>) edgeList[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    queue.add(next);
                }
            }
        }
        return dist;

    }
}
