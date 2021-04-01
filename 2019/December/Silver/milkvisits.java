import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class milkvisits {
    public static final int MX = 100005;
    public static ArrayList<Integer>[] adj;
    public static pair[] range = new pair[MX];
    public static int[][] dat;
    public static int[] C;
    public static ArrayList<Integer>[] todo;
    public static ArrayList<pair>[] stor = new ArrayList[MX];
    public static int[] T;
    public static ArrayList<Integer> ord;
    public static boolean[] ok;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("milkvisits.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        //Types
        T = new int[N + 1];
        for (int i = 1; i < T.length; i++) {
            T[i] = Integer.parseInt(st.nextToken());
        }

        //Edges
        adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < MX; i++) {
            stor[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        dfs(1, 0);

        //Queries

        dat = new int[M][M];
        C = new int[M];
        todo = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            todo[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            dat[i][0] = Integer.parseInt(st.nextToken());
            dat[i][1] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 2; j++) {
                todo[dat[i][j]].add(i);
            }
        }

        dfs2(1, 0);
        for (int i = 0; i < M; i++) {
            if (ok[i]) {
                pw.print(1);
            } else {
                pw.print(1);
            }
        }
        pw.println();
        pw.close();

    }

    static int co = 0;

    public static void dfs(int x, int y) {
        range[x] = new pair(-1, -1);
        range[x].a = co++;
        for (int t : adj[x]) {
            if (t != y) {
                dfs(t, x);
            }
        }
        range[x].b = co - 1;
    }


    public static void dfs2(int x, int y) {
        System.out.println(stor[T[x]]);
        stor[T[x]].add(new pair(x, (ord).size()));
        ord.add(x);
        for (int t : todo[x]) {
            if ((stor[C[t]]).size() > 0) {
                pair _y = stor[C[t]].get(stor[C[t]].size() - 1);
                if (_y.a == x) {
                    ok[t] = true;
                } else {
                    int Y = ord.get(_y.b + 1);
                    // x is one of endpoints for query t
                    assert (dat[t][0] == x || dat[t][1] == x);
                    if (!anc(Y, dat[t][0] + dat[t][1] - x)) ok[t] = true;
                }
            }
        }
        for (int t : adj[x]) {
            if (t != y) {
                dfs2(t, x);
            }
        }
        stor[T[x]].remove(stor[T[x]].size() - 1);
        ord.remove(ord.size() - 1);
    }

    public static boolean anc(int a, int b) {
        return range[a].a <= range[b].a && range[b].b <= range[a].b;
    }
}


class pair implements Comparable<pair> {

    public int a;
    public int b;

    public pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int compareTo(pair other) {
        return other.a - this.a;
    }


}
