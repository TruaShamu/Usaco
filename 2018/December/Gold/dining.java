import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.io.*;
public class dining {
    public static ArrayList[] edges;
    final public static int OFFSET = 1000000001;
    public static int N, M, K;
    public static int[] enjoy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("dining.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edges = new ArrayList[N];
        //Initialize
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<Integer>();
        }
        enjoy = new int[N];


        //Read edges
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, t));
            edges[b].add(new Edge(a, t));
        }

        //Haybales
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int loc = Integer.parseInt(st.nextToken()) - 1;
            enjoy[loc] = Integer.parseInt(st.nextToken());
        }
        //Shortest distance from final node
        int[] shortest = dijkstras(edges, N - 1);
        ArrayList[] hayGraph = makeHayGraph();
        int[] treat = dijkstras(hayGraph, N - 1);

        //Answer
        for (int i = 0; i < N - 1; i++) {
            if (treat[N + i] - OFFSET <= treat[i]) {
                pw.println(1);
            } else {
                pw.println(0);
            }
        }
        pw.close();
        for (int i : treat) {
            System.out.println(i);
        }

        for (int i : shortest) {
            System.out.println(i);
        }


    }


    public static ArrayList[] makeHayGraph() {
        //Initialize array
        ArrayList[] g = new ArrayList[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            g[i] = new ArrayList<Edge>();
        }

        // Add two levels of edges vertices 0 to n-1 are original vertices w/o treat.
        // vertices n to 2n-1 are mirrored vertices AFTER you've taken a treat.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N; j += N) {
                for (Edge e : (ArrayList<Edge>) edges[i]) {
                    g[i + j].add(new Edge(e.node + j, e.cost));
                    g[e.node + j].add(new Edge(i + j, e.cost));
                }
            }
        }

        // Now add edges for taking treats. Edge weight is -yummy, but we offset it
        // by OFFSET so there are no negative edge weights. We can get away with this
        // because all of these paths will have EXACTLY one edge with a treat taken.
        // So, I can recover my original shortest path accordingly.
        for (int i = 0; i < N; i++) {
            if (enjoy[i] > 0) {
                g[i].add(new Edge(i + N, OFFSET - enjoy[i]));
            }
        }

        return g;
    }


    public static int[] dijkstras(ArrayList[] g, int source) {

        // Set up distance array for Dijkstra's.
        int[] dist = new int[g.length]; //Dist from node
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        boolean[] done = new boolean[dist.length]; //Visited
        done[source] = true;

        // Set up the priority queue.
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e : (ArrayList<Edge>) g[source]) {
            pq.add(e);
        }

        // Go through each estimate.
        while (pq.size() > 0) {

            // Pull next estimate, skip if we already have best distance.
            Edge cur = pq.poll();
            if (done[cur.node]) {
                continue;
            }

            // Update best distance.
            dist[cur.node] = cur.cost;
            done[cur.node] = true;

            // Now, update distances to edges leaving cur.to, if necessary.
            for (Edge e : (ArrayList<Edge>) g[cur.node]) {
                if (!done[e.node] && cur.cost + e.cost < dist[e.node]) {
                    pq.add(new Edge(e.node, cur.cost + e.cost));
                }
            }
        }

        // Here are the distances.
        return dist;
    }
}

class Edge implements Comparable<Edge> {
    public int node, cost;


    public Edge(int node1, int cost) {
        this.node = node1;
        this.cost = cost;
    }

    public int compareTo(Edge other) {
        return this.cost - other.cost;
    }

}
