import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        final int inf = (int) 1e9;
        int qh = 0, qt = 0;
        BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[] bValues = new int[2 * N];
        int[] eValues = new int[2 * N];
        int[] dist = new int[2 * N]; //Answer array
        int[] qu = new int[2 * N];
        TreeSet<Pair> unvisited1 = new TreeSet<>();
        TreeSet<Pair> unvisited2 = new TreeSet<>();
        ArrayList<Integer> sink = new ArrayList<>();

        for (int i = 0; i < 2 * N; i++) {
            //Read input
            st = new StringTokenizer(br.readLine());
            bValues[i] = Integer.parseInt(st.nextToken());
            eValues[i] = Integer.parseInt(st.nextToken());
            if (eValues[i] == 0 && i < N || bValues[i] == 0 && i >= N) {
                sink.add(i);
            } else if (i < N) {
                unvisited1.add(new Pair(eValues[i], i));
            } else {
                unvisited2.add(new Pair(bValues[i], i));
            }
        }
        //Initialize output array
        Arrays.fill(dist, inf);
        for (int source : sink) {
            dist[source] = 1;
            qu[qt++] = source;
        }

        while (qt > qh) {
            int u = qu[qh++];
            if (u < N) {
                Pair upper = new Pair(bValues[u], inf);
                Pair p = unvisited2.lower(upper);
                while (p != null && bValues[u] - p.a <= D) {
                    dist[p.b] = dist[u] + 1;
                    qu[qt++] = p.b;
                    unvisited2.remove(p);
                    p = unvisited2.lower(upper);
                }
            } else {
                Pair upper = new Pair(eValues[u], inf);
                Pair p = unvisited1.lower(upper);
                while (p != null && eValues[u] - p.a <= D) {
                    dist[p.b] = dist[u] + 1;
                    qu[qt++] = p.b;
                    unvisited1.remove(p);
                    p = unvisited1.lower(upper);
                }
            }
        }
        //trua is the best orca
        //Output
        for (int i = 0; i < N; ++i) {
            if (dist[i] >= inf) {
                pw.println(-1);
                System.out.println(-1);
            } else {
                pw.println(dist[i]);
                System.out.println(dist[i]);
            }
        }
        pw.close();

    }
}

class Pair implements Comparable<Pair> {
    int a, b;

    Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int compareTo(Pair o) {
        return a == o.a ? b - o.b : a - o.a;
    }
    
}

