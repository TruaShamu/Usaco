import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<Edge>[] edgeList;
    public static int nodes;
    public static int[] dist; //Dist from source
    public static int[] path;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rblock.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("rblock.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodes = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            edgeList[i] = new ArrayList<>();
        }
        int edges = Integer.parseInt(st.nextToken());
        //Read in each edge.
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            edgeList[node1].add(new Edge(node1, node2, cost));
            edgeList[node2].add(new Edge(node2, node1, cost));
        }

        dijkstras(0, true); //Shortest distance before lengthening.
        System.out.println("PATH: " + Arrays.toString(path));
        int best = dist[nodes - 1]; //Distance between farm and FJ.
        int res = 0; //Answer

        // Peel back path.
        int last = nodes - 1;
        while (path[last] != last) {
            // Double this edge weight.
            int prev = path[last];
            lengthen(prev, last);

            // Rerun Dijkstra's getting the new best weight.
            dijkstras(0, false);

            // See how much worse this makes it.
            res = Math.max(res, dist[nodes - 1] - best);

            // Put this back to the original (div by 2).
            shorten(prev, last);

            // Update to previous edge.
            last = path[last];
        }
        System.out.println("ANSWERS: " + res);
        pw.println(res);
        pw.close();


    }


    public static void lengthen(int start, int end) {
        //Double the path
        for (Edge oEdge : edgeList[start]) {
            if (oEdge.end == end) {
                oEdge.weight = 2 * oEdge.weight;
            }
        }
    }

    public static void shorten(int start, int end) {
        //Reset the path to the original path
        for (Edge oEdge : edgeList[start]) {
            if (oEdge.end == end) {
                oEdge.weight = oEdge.weight / 2;
            }
        }
    }

    public static void dijkstras(int source, boolean doPath) {
        // Set up
        dist = new int[nodes];
        Arrays.fill(dist, -1);
        dist[source] = 0;
        if (doPath) {
            path = new int[nodes];
            Arrays.fill(path, -1);
            path[source] = source;
        }
        // Add in all edges from source.
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for (Edge e : edgeList[source]) {
            queue.add(e);
        }

        while (queue.size() > 0) {
            Edge cur = queue.remove(); //Edge
            if (dist[cur.end] != -1) {
                //This node has been found
                continue;
            }
            // If we get here this is a shortest distance.
            dist[cur.end] = cur.weight;
            if (doPath) {
                //Its root
                path[cur.end] = cur.start;
            }
            // Enqueue all new paths not to known places.
            for (Edge oEdge : edgeList[cur.end]) {
                if (dist[oEdge.end] == -1) {
                    queue.add(new Edge(oEdge.start, oEdge.end, cur.weight + oEdge.weight));
                }
            }
        }
    }
}


class Edge implements Comparable<Edge> {

    public int start;
    public int end;
    public int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int compareTo(Edge other) {
        return weight - other.weight;
    }
}
