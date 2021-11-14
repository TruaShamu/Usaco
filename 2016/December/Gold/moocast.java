import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class moocast {
    public static int N;
    public static int[] parent;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("moocast.out"));
        N = Integer.parseInt(br.readLine());
        int[] x = new int[N];
        int[] y = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        parent = new int[N];
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            for (int j = 0; j < i; j++) {
                int distance = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
                edges.add(new Edge(i, j, distance));
            }
        }

        Collections.sort(edges);
        int lastWeight = 0;
        int numComponents = N;
        for (Edge curr : edges) {
            if (find(curr.node1) != find(curr.node2)) {
                merge(curr.node1, curr.node2);
                lastWeight = curr.weight;
                if (--numComponents == 1) {
                    break;
                }
            }
        }

        System.out.println("ANSWER: " + lastWeight);
        pw.println(lastWeight);
        pw.close();
    }

    public static int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]);
    }

    public static void merge(int x, int y) {
        parent[find(x)] = find(y);
    }

}

class Edge implements Comparable<Edge> {
    public int node1, node2, weight;

    public Edge(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public int compareTo(Edge e) {
        return weight - e.weight;
    }
}


