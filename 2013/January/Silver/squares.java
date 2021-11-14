import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class squaresNew {
    //Squares Optimized
    public static int MAXN = 50010;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("squares.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("squares.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Edge[] edges = new Edge[MAXN];
        boolean have = false;
        for (int i = 0; i < MAXN; i++) {
            edges[i] = new Edge(0, 0);
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            edges[i].x = x1;
            edges[i].y = y1;
        }
        int ans = 0;
        Arrays.sort(edges, 1, N + 1);

        for (int i = 2; i <= N; i++) {
            int tmp = 0;
            for (int j = i - 1; j >= 1; j--) {
                tmp += edges[j + 1].x - edges[j].x;
                if (tmp >= K) {
                    break;
                }
                if (Math.abs(edges[i].y - edges[j].y) < K) {
                    if (have) {
                        pw.println(-1);
                        pw.close();
                        System.exit(0);

                    }
                    have = true;
                    ans = (K - tmp) * (K - Math.abs(edges[i].y - edges[j].y));

                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}

class Edge implements Comparable<Edge> {
    public int x, y;

    public Edge(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Edge oEdges) {
        return Integer.compare(this.x, oEdges.x);

    }
}



