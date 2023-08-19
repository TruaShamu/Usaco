import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class delegation {
    public static ArrayList<Integer>[] adj;
    public static ArrayList<Integer>[] num; //@todo ALL THE SUBTREES OF THE NODE.
    public static int[] sub; //@todo How many nodes are in the subtree.
    public static int[] cur;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("deleg.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("deleg.out")));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N + 1];
        num = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
            num[i] = new ArrayList<>();
        }
        //READ EDGES
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        sub = new int[N + 1];
        cur = new int[N + 1];
        dfs(1, 0);

        for (int i = 1; i < N; ++i) {
            if (ok(i)) {
                pw.print(1);
            } else {
                pw.print(0);
            }
        }
        pw.close();


    }


    public static boolean ok(int K) {
        //@TODO NOT DIVISIBLE.
        if ((N - 1) % K != 0) {
            return false;
        }
        for (int i = 0; i < K; ++i) {
            cur[i] = 0;
        }
        for (int i = 1; i <= N; ++i) {
            int cnt = 0;
            for (int subtree : num[i]) {
                //@todo remaining path
                int remainder = subtree % K;
                if (remainder == 0) {
                    continue;
                }
                //Pair length Remainder path with K-Remainder Path
                if (cur[K - remainder] > 0) {
                    cur[K - remainder]--;
                    cnt--;
                } else {
                    cur[remainder]++;
                    cnt++;
                }
            }
            if (cnt > 0) {
                return false;
            }
        }
        return true;
    }

    public static void dfs(int node, int par) {
        sub[node] = 1;
        for (int next : adj[node]) {
            if (next != par) {
                dfs(next, node);
                sub[node] += sub[next];
                num[node].add(sub[next]);
            }
        }
        if (sub[node] != N) {
            num[node].add(N - sub[node]);
        }
    }

}

