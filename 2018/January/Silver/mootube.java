import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        LinkedList<Edge>[] edges = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new LinkedList<Edge>();
        }
        for (int a = 1; a < n; a++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            edges[x].add(new Edge(y, w));
            edges[y].add(new Edge(x, w));
        }
        for (int query = 0; query < q; query++) {
            st = new StringTokenizer(br.readLine());
            int threshold = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int ret = 0;
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(start);
            boolean[] seen = new boolean[n];
            seen[start] = true;
            while (!queue.isEmpty()) {
                int curr = queue.removeFirst();
                for (Edge out : edges[curr]) {
                    if (!seen[out.d] && out.w >= threshold) {
                        seen[out.d] = true;
                        queue.add(out.d);
                        ret++;
                    }
                }
            }
            pw.println(ret);
        }
        pw.close();
    }

    static class Edge {
        public int d, w;

        public Edge(int a, int b) {
            d = a;
            w = b;
        }
    }

}
