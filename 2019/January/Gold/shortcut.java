import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class shortcut {
    public static ArrayList<edge>[] adj;
    public static int N, M, T;
    public static int[] dist, distOrder, par;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        int[] cows = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(st.nextToken());
        }

        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        //@todo read edges
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adj[a].add(new edge(b, w));
            adj[b].add(new edge(a, w));
        }

        dijkstras(0);
        for (int i = N - 1; i >= 0; i--) {
            int curNode = distOrder[i];
            cows[par[curNode]] += cows[curNode];
        }
        //Find answers
        
        long res = 0;
        for (int node = 0; node < N; node++) {
            if (dist[node] > T) {
                res = Math.max(res, ((long) cows[node]) * (dist[node] - T)); // numcows * savedtime
            }
        }

        System.out.println("ANSWER: " + res);
        pw.println(res);
        pw.close();


    }

    public static void dijkstras(int source) {
        // Allocate and set everything up.
        dist = new int[N];
        par = new int[N];
        distOrder = new int[N];
        Arrays.fill(par, -1);
        Arrays.fill(dist, 1000000000);
        dist[source] = 0;
        par[source] = source;

        // Set up PQ for Dijkstra's.
        PriorityQueue<edge> pq = new PriorityQueue<>();
        pq.offer(new edge(source, 0));
        boolean[] visited = new boolean[N];
        int numVisit = 0;


        while (numVisit < N) {
            //@todo make visited.
            edge cur = pq.poll();
            if (visited[cur.vertex]) {
                continue;
            }
            visited[cur.vertex] = true;
            distOrder[numVisit++] = cur.vertex;

            //@todo visit neighbors
            for (edge next : adj[cur.vertex]) {
                int newDist = cur.cost + next.cost;
                if ((newDist < dist[next.vertex]) || ((newDist == dist[next.vertex]) && (cur.vertex < par[next.vertex]))) {
                    dist[next.vertex] = newDist;
                    par[next.vertex] = cur.vertex;
                    pq.add(new edge(next.vertex, newDist));
                }
            }
        }
    }

    static class edge implements Comparable<edge> {

        public int vertex;
        public int cost;

        public edge(int vertex, int cost) {
            this.cost = cost;
            this.vertex = vertex;
        }

        public int compareTo(edge other) {
            return this.cost - other.cost;
        }
    }

}




