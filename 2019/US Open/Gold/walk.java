import java.util.*;
import java.io.*;
public class walk {
    public static int N, K;
    public static long a = 2019201913L;
    public static long b = 2019201949L;
    public static long mod = 2019201997L;
    public static long[][] adjMat;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("walk.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        adjMat = new long[N][N];
        //Initialize
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                adjMat[j][i] = adjMat[i][j] = (a * (i + 1) + b * (j + 1)) % mod; //

            }
        }
        long[] distances = prim();
        Arrays.sort(distances);
        System.out.println(distances[N + 1 - K]);
        pw.println(distances[N + 1 - K]);
        pw.close();
        //2019201997 - 48*n - 84*(k-1);


    }

    static long[] prim() {
        long key[] = new long[N]; //Distance
        Arrays.fill(key, Long.MAX_VALUE);

        boolean inSet[] = new boolean[N]; //Is it in the tree
        key[0] = 0;

        for (int count = 0; count < N - 1; count++) {
            long min = Long.MAX_VALUE;
            int minIndex = -1;

            //Find closest vertex
            for (int vertex = 0; vertex < N; vertex++) {
                if (!inSet[vertex] && key[vertex] < min) {
                    min = key[vertex];
                    minIndex = vertex;
                }
            }
            //Add the vertex to the tree
            int u = minIndex;
            inSet[u] = true;
            //Find closest
            for (int v = 0; v < N; v++) {
                if (adjMat[u][v] != 0 && !inSet[v] && adjMat[u][v] < key[v]) {
                    key[v] = adjMat[u][v];
                }
            }
        }
        return key;
    }
}
