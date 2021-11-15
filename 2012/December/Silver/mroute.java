import java.io.*;
import java.util.*;

public class Main {
    public static int MAX = 500;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mroute.in"));
        PrintWriter pw = new PrintWriter(new File("mroute.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int AMT = Integer.parseInt(st.nextToken());

        List<List<Edge>> E = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            E.add(new ArrayList<>());
        }
        int[] capacity = new int[M];
        Set<Integer> U = new HashSet<>();

        //Read edges
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (node1 == node2) {
                pw.println(1000 * 1000 * 1000);
                pw.close();
                System.exit(0);
            }
            if (U.contains(node1 * MAX + node2)) {
                pw.println(1000 * 1000 * 100);
                pw.close();
                return;
            }
            U.add(node1 * MAX + node2);
            capacity[i] = c;
            E.get(node1).add(new Edge(node2, l, c));
            E.get(node2).add(new Edge(node1, l, c));
        }
        Arrays.sort(capacity);


        int ans = 1000 * 1000 * 1000;
        for (int c = 0; c < M; c++) {
            Queue<Pair> pq = new PriorityQueue<>(10);
            pq.add(new Pair(0, 0));
            boolean[] S = new boolean[N];
            int dist = 1000 * 1000 * 1000;
            while (!pq.isEmpty()) {
                Pair X = pq.poll();
                //int v = X[0];
                //int d = X[1];
                if (S[X.a]) {
                    continue;
                }

                S[X.a] = true;
                if (X.a == N - 1) {
                    dist = X.b;
                    break;
                }

                for (Edge e : E.get(X.a)) {
                    if (e.c < capacity[c]) {
                        continue;
                    }
                    pq.offer(new Pair(e.node, X.b + e.l));
                }
            }
            // L + (X/C)
            ans = Integer.min(ans, dist + (AMT / capacity[c]));
        }
        pw.println(ans);
        pw.close();


    }
}

class Edge implements Comparable<Edge> {
    public int node, l, c;


    public Edge(int node, int l, int c) {
        this.node = node;
        this.l = l;
        this.c = c;
    }

    public int compareTo(Edge other) {
        return this.l - other.l;
    }

}

class Pair implements Comparable<Pair> {
    public int a, b;


    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int compareTo(Pair other) {
        return this.b - other.b;
    }


}

