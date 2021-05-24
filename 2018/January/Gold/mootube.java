import java.io.*;
import java.util.*;

public class mootube {
    public static int N, K;
    public static int[] par, size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //@todo Read edges.
        edge[] edges = new edge[N - 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new edge(a, b, c);
        }
        Arrays.sort(edges);

        //@todo Read queries.
        query[] queries = new query[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int rel = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken())-1;
            queries[i] = new query(node, rel, i);
        }
        Arrays.sort(queries);

        par = new int[N];
        size = new int[N];
        int[] ans = new int[K];
        for (int i = 0; i < N; i++) {
            par[i] = i;
            size[i] = 1;
        }

        int idx = 0;
        for (query oQuery : queries) {
            while ((idx < (N - 1)) && (edges[idx].c >= oQuery.k)) {
                merge(edges[idx].a, edges[idx].b);
                idx++;

            }
            ans[oQuery.idx] = sizeOf(oQuery.node)- 1;
        }
        for (int i : ans) {
            pw.println(i);
        }

        pw.close();

    }

    //@todo Normal DSU Code
    public static void merge(int node1, int node2) {
        int cc1 = find(node1);
        int cc2 = find(node2);
        size[cc2] += size[cc1];
        par[cc1] = cc2;


    }

    public static int find(int node) {
        if (par[node] == node) {
            return node;
        }
        par[node] = find(par[node]);
        return par[node];
    }

    public static int sizeOf(int node) {
        return size[find(node)];
    }

    //@todo Edge, (node1, node2, relevance)
    static class edge implements Comparable<edge> {
        public int a, b, c;

        public edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int compareTo(edge oEdge) {
            return Integer.compare(oEdge.c, this.c);
        }
    }

    //@todo Query(Node, Relevance, Index)
    static class query implements Comparable<query> {
        public int node, k, idx;

        public query(int node, int k, int idx) {
            this.node = node;
            this.k = k;
            this.idx = idx;
        }

        public int compareTo(query oQuery) {
            return Integer.compare(oQuery.k, this.k);
        }
    }
}
