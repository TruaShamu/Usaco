import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int nodes, edges;
    public static flowedge[] edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pump.in"));
        PrintWriter pw = new PrintWriter("pump.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodes = Integer.parseInt(st.nextToken()); //N
        edges = Integer.parseInt(st.nextToken()); //M
        edgeList = new flowedge[edges];
        for (int i = 0; i < edges; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            int flow = Integer.parseInt(st.nextToken());
            edgeList[i] = new flowedge(node1, node2, cost, flow);
        }
        Arrays.sort(edgeList);
        ArrayList[] g = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            g[i] = new ArrayList<edge>();
        }
        double res = -1;
        for (int i = 0; i < edges; i++) {
            // Add each edge
            g[edgeList[i].u].add(new edge(edgeList[i].u, edgeList[i].v, edgeList[i].w));
            g[edgeList[i].v].add(new edge(edgeList[i].v, edgeList[i].u, edgeList[i].w));

            // Get the distances.
            int[] dist = dijkstras(g, 0);


            if (dist[nodes - 1] != -1) {
                double newRes = 1.0 * edgeList[i].f / dist[nodes - 1];
                res = Math.max(res, newRes);
            }
        }

        int intres = (int) (res * 1000000 + 1e-9);

        // Output to file.
        PrintWriter out = new PrintWriter(new FileWriter("pump.out"));
        out.println(intres);
        out.close();


    }

    public static int[] dijkstras(ArrayList[] g, int source) {
        int n = g.length;
        int[] dist = new int[n]; // distance from the source node
        Arrays.fill(dist, -1);
        dist[source] = 0;

        // Add in all edges from source.
        PriorityQueue<edge> pq = new PriorityQueue<>();
        for (edge e : (ArrayList<edge>) g[source]) {
            pq.add(e);
        }

        while (pq.size() > 0) {
            edge cur = pq.poll();
            if (dist[cur.v] != -1) {
                //node has been visited
                continue;
            }
            // If we get here this is a shortest distance.
            dist[cur.v] = cur.w;

            // Enqueue all new paths not to known places.
            for (edge e : (ArrayList<edge>) g[cur.v]) {
                if (dist[e.v] == -1) {
                    pq.add(new edge(e.u, e.v, cur.w + e.w));
                }
            }
        }

        // This stores all the distances.
        return dist;
    }
}

class edge implements Comparable<edge> {

    public int u;
    public int v;
    public int w;

    public edge(int from, int to, int weight) {
        u = from;
        v = to;
        w = weight;
    }

    public int compareTo(edge other) {
        return w - other.w;
    }
}

// For ordering how we add the edges into the graph.
class flowedge implements Comparable<flowedge> {

    public int u;
    public int v;
    public int w;
    public int f;

    public flowedge(int from, int to, int weight, int flow) {
        u = from;
        v = to;
        w = weight;
        f = flow;
    }

    public int compareTo(flowedge other) {
        return other.f - f;
    }
}
