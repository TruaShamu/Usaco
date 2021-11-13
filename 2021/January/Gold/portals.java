import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class portals {
    public static int[] disjoint;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        disjoint = new int[(2 * N) + 1];
        for (int p = 1; p <= 2 * N; p++) {
            disjoint[p] = p;
        }

        List<Edge> edges = new ArrayList<>();
        for (int a = 1; a <= N; a++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int[] portals = new int[4];
            for (int j = 0; j < 4; j++) {
                portals[j] = Integer.parseInt(st.nextToken());
            }
            edges.add(new Edge(portals[0], portals[1], 0));
            edges.add(new Edge(portals[2], portals[3], 0));
            edges.add(new Edge(portals[3], portals[0], cost));
        }
        edges.sort(Comparator.comparingInt(edge -> edge.cost));
        int answer = 0;
        for (Edge edge : edges) {
            int u = find(edge.a);
            int v = find(edge.b);
            if (u != v) {
                answer += edge.cost;
                disjoint[u] = v;
            }
        }
        System.out.println(answer);


    }
    static class Edge {
        final int a;
        final int b;
        final int cost;

        Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }
    static int find(int u) {
        if (disjoint[disjoint[u]] != disjoint[u]) {
            disjoint[u] = find(disjoint[u]);
        }
        return disjoint[u];
    }



}
